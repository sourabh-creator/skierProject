package com.skier.skiersproject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skier.skiersproject.Skier.Skier;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class MultiThreadClient {
    FileWriter writer;
    Random random = new Random();


    @Test
    public void testDoPostConcurrentConnections() throws Exception {

        try {
            writer = new FileWriter("./latencies.csv");
            writer.append("Latency, Status Code\n");
            writer.flush();

            AtomicInteger successfulRequests = new AtomicInteger();

            PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
            connManager.setMaxTotal(10000);

            CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connManager).build();

            ExecutorService executor = Executors.newFixedThreadPool(32);
            for (int i = 0; i < 10000; i++) {
                executor.execute(() -> {
                    HttpPost httpPost = new HttpPost("http://localhost:8080/skier/addSkier");
                    try {
                        long startTime = System.currentTimeMillis();
                        Skier skier = new Skier(random.nextInt(10000-1) + 1, random.nextInt(10-1) + 1, random.nextInt(40-1) + 1, 2022, 1, random.nextInt(360-1) + 1);
                        ObjectMapper objectMapper = new ObjectMapper();
                        String json = objectMapper.writeValueAsString(skier);
                        StringEntity requestEntity = new StringEntity(json);
                        httpPost.setEntity(requestEntity);
                        httpPost.setHeader("Content-type", "application/json");

                        CloseableHttpResponse response = httpClient.execute(httpPost);
                        long endTime = System.currentTimeMillis();
                        long timeTaken = endTime - startTime;
                        writer.append(timeTaken + ", " + response.getStatusLine().getStatusCode() + "\n");
                        writer.flush();
                        if (response.getStatusLine().getStatusCode() == 200) {
                            successfulRequests.getAndIncrement();
                        }
                        response.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }

            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.MINUTES);

            assertEquals(10000, successfulRequests.get());
            assertTrue(successfulRequests.get() > 100);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                System.out.println("Error closing writer for file");
                e.printStackTrace();
            }
        }
    }
}
