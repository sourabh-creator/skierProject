package com.skier.skiersproject;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skier.skiersproject.Skier.Skier;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class ClientTest {

    @Test
    public final void doGetLatencies() throws IOException {
        long[] latencies = new long[10];
        long[] test = new long[10];
        for (int i = 0; i < 10; i++) {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet("http://localhost:8080/skier/findAll");

            try {
                long startTime = System.currentTimeMillis();
                CloseableHttpResponse response = httpclient.execute(httpGet);
                long endTime = System.currentTimeMillis();
                long timeTaken = endTime - startTime;
                latencies[i] = timeTaken;
                response.close();
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        assertEquals(10, latencies.length);
        assertNotEquals(test, latencies);
    }

    @Test
    public final void doPostLatancies() throws IOException {
        try {
            List<Skier> skierList = new ArrayList<>();
            List<String> jsonList = new ArrayList<>();
            ObjectMapper objectMapper = new ObjectMapper();
            long[] latencies = new long[10];
            long[] test = new long[10];

            skierList.add(new Skier(1, 1, 1, 1, 1, 1));
            skierList.add(new Skier(2, 2, 2, 1, 1, 1));
            skierList.add(new Skier(3, 3, 3, 1, 1, 1));
            skierList.add(new Skier(4, 4, 4, 1, 1, 1));
            skierList.add(new Skier(5, 5, 5, 1, 1, 1));
            skierList.add(new Skier(6, 6, 6, 1, 1, 1));
            skierList.add(new Skier(7, 7, 7, 1, 1, 1));
            skierList.add(new Skier(8, 8, 8, 1, 1, 1));
            skierList.add(new Skier(9, 9, 9, 1, 1, 1));
            skierList.add(new Skier(10, 10, 10, 1, 1, 1));

            for (int i = 0; i < 10; i = i + 1) {
                long startTime = System.currentTimeMillis();
                String json = objectMapper.writeValueAsString(skierList.get(i));
                jsonList.add(json);

                CloseableHttpClient httpClient = HttpClients.createDefault();

                // Create the HttpPost request with the JSON object as the request entity
                HttpPost httpPost = new HttpPost("http://localhost:8080/skier/addSkier");
                StringEntity requestEntity = new StringEntity(jsonList.get(i));
                httpPost.setEntity(requestEntity);
                httpPost.setHeader("Content-type", "application/json");

                // Execute the request and get the response
                HttpResponse httpResponse = httpClient.execute(httpPost);
                int statusCode = httpResponse.getStatusLine().getStatusCode();

                // Get the response body as a String
                HttpEntity entity = httpResponse.getEntity();
                String responseBody = EntityUtils.toString(entity);

                CloseableHttpResponse response = httpClient.execute(httpPost);

                // Print the response status code and body
                assertEquals(HttpStatus.SC_OK, statusCode);
                if (responseBody != null) {
                    Skier responseSkier = objectMapper.readValue(responseBody, Skier.class);
                    assertEquals(skierList.get(i).getSkierId(), responseSkier.getSkierId());
                }
                long endTime = System.currentTimeMillis();
                long timeTaken = endTime - startTime;
                latencies[i] = timeTaken;
                response.close();
                httpClient.close();
            }
            assertEquals(10, latencies.length);
            assertNotEquals(test, latencies);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
