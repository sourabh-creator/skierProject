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
        long[] latencies = new long[100];
        long[] test = new long[100];
        for (int i = 0; i < 100; i++) {
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
        assertEquals(100, latencies.length);
        assertNotEquals(test, latencies);
        System.out.println("Latencies: ");// for loop to print out the array
        for (int i = 0; i < latencies.length; i++)
        {
            System.out.printf("No: %d = %d ms\n", i + 1, latencies[i]);
        }
    }

    @Test
    public final void doPostLatancies() throws IOException {
        try {
            List<Skier> skierList = new ArrayList<>();
            List<String> jsonList = new ArrayList<>();
            ObjectMapper objectMapper = new ObjectMapper();
            long[] latencies = new long[100];
            long[] test = new long[100];

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
            skierList.add(new Skier(11, 1, 1, 1, 1, 1));
            skierList.add(new Skier(12, 2, 2, 1, 1, 1));
            skierList.add(new Skier(13, 3, 3, 1, 1, 1));
            skierList.add(new Skier(14, 4, 4, 1, 1, 1));
            skierList.add(new Skier(15, 5, 5, 1, 1, 1));
            skierList.add(new Skier(16, 6, 6, 1, 1, 1));
            skierList.add(new Skier(17, 7, 7, 1, 1, 1));
            skierList.add(new Skier(18, 8, 8, 1, 1, 1));
            skierList.add(new Skier(19, 9, 9, 1, 1, 1));
            skierList.add(new Skier(20, 10, 10, 1, 1, 1));
            skierList.add(new Skier(21, 1, 1, 1, 1, 1));
            skierList.add(new Skier(22, 2, 2, 1, 1, 1));
            skierList.add(new Skier(23, 3, 3, 1, 1, 1));
            skierList.add(new Skier(24, 4, 4, 1, 1, 1));
            skierList.add(new Skier(25, 5, 5, 1, 1, 1));
            skierList.add(new Skier(26, 6, 6, 1, 1, 1));
            skierList.add(new Skier(27, 7, 7, 1, 1, 1));
            skierList.add(new Skier(28, 8, 8, 1, 1, 1));
            skierList.add(new Skier(29, 9, 9, 1, 1, 1));
            skierList.add(new Skier(30, 10, 10, 1, 1, 1));
            skierList.add(new Skier(31, 1, 1, 1, 1, 1));
            skierList.add(new Skier(32, 2, 2, 1, 1, 1));
            skierList.add(new Skier(33, 3, 3, 1, 1, 1));
            skierList.add(new Skier(34, 4, 4, 1, 1, 1));
            skierList.add(new Skier(35, 5, 5, 1, 1, 1));
            skierList.add(new Skier(36, 6, 6, 1, 1, 1));
            skierList.add(new Skier(37, 7, 7, 1, 1, 1));
            skierList.add(new Skier(38, 8, 8, 1, 1, 1));
            skierList.add(new Skier(39, 9, 9, 1, 1, 1));
            skierList.add(new Skier(40, 10, 10, 1, 1, 1));
            skierList.add(new Skier(41, 1, 1, 1, 1, 1));
            skierList.add(new Skier(42, 2, 2, 1, 1, 1));
            skierList.add(new Skier(43, 3, 3, 1, 1, 1));
            skierList.add(new Skier(44, 4, 4, 1, 1, 1));
            skierList.add(new Skier(45, 5, 5, 1, 1, 1));
            skierList.add(new Skier(46, 6, 6, 1, 1, 1));
            skierList.add(new Skier(47, 7, 7, 1, 1, 1));
            skierList.add(new Skier(48, 8, 8, 1, 1, 1));
            skierList.add(new Skier(49, 9, 9, 1, 1, 1));
            skierList.add(new Skier(50, 10, 10, 1, 1, 1));
            skierList.add(new Skier(51, 1, 1, 1, 1, 1));
            skierList.add(new Skier(52, 2, 2, 1, 1, 1));
            skierList.add(new Skier(53, 3, 3, 1, 1, 1));
            skierList.add(new Skier(54, 4, 4, 1, 1, 1));
            skierList.add(new Skier(55, 5, 5, 1, 1, 1));
            skierList.add(new Skier(56, 6, 6, 1, 1, 1));
            skierList.add(new Skier(57, 7, 7, 1, 1, 1));
            skierList.add(new Skier(58, 8, 8, 1, 1, 1));
            skierList.add(new Skier(59, 9, 9, 1, 1, 1));
            skierList.add(new Skier(60, 10, 10, 1, 1, 1));
            skierList.add(new Skier(61, 1, 1, 1, 1, 1));
            skierList.add(new Skier(62, 2, 2, 1, 1, 1));
            skierList.add(new Skier(63, 3, 3, 1, 1, 1));
            skierList.add(new Skier(64, 4, 4, 1, 1, 1));
            skierList.add(new Skier(65, 5, 5, 1, 1, 1));
            skierList.add(new Skier(66, 6, 6, 1, 1, 1));
            skierList.add(new Skier(67, 7, 7, 1, 1, 1));
            skierList.add(new Skier(68, 8, 8, 1, 1, 1));
            skierList.add(new Skier(69, 9, 9, 1, 1, 1));
            skierList.add(new Skier(70, 10, 10, 1, 1, 1));
            skierList.add(new Skier(71, 1, 1, 1, 1, 1));
            skierList.add(new Skier(72, 2, 2, 1, 1, 1));
            skierList.add(new Skier(73, 3, 3, 1, 1, 1));
            skierList.add(new Skier(74, 4, 4, 1, 1, 1));
            skierList.add(new Skier(75, 5, 5, 1, 1, 1));
            skierList.add(new Skier(76, 6, 6, 1, 1, 1));
            skierList.add(new Skier(77, 7, 7, 1, 1, 1));
            skierList.add(new Skier(78, 8, 8, 1, 1, 1));
            skierList.add(new Skier(79, 9, 9, 1, 1, 1));
            skierList.add(new Skier(80, 10, 10, 1, 1, 1));
            skierList.add(new Skier(81, 1, 1, 1, 1, 1));
            skierList.add(new Skier(82, 2, 2, 1, 1, 1));
            skierList.add(new Skier(83, 3, 3, 1, 1, 1));
            skierList.add(new Skier(84, 4, 4, 1, 1, 1));
            skierList.add(new Skier(85, 5, 5, 1, 1, 1));
            skierList.add(new Skier(86, 6, 6, 1, 1, 1));
            skierList.add(new Skier(87, 7, 7, 1, 1, 1));
            skierList.add(new Skier(88, 8, 8, 1, 1, 1));
            skierList.add(new Skier(89, 9, 9, 1, 1, 1));
            skierList.add(new Skier(90, 10, 10, 1, 1, 1));
            skierList.add(new Skier(91, 1, 1, 1, 1, 1));
            skierList.add(new Skier(92, 2, 2, 1, 1, 1));
            skierList.add(new Skier(93, 3, 3, 1, 1, 1));
            skierList.add(new Skier(94, 4, 4, 1, 1, 1));
            skierList.add(new Skier(95, 5, 5, 1, 1, 1));
            skierList.add(new Skier(96, 6, 6, 1, 1, 1));
            skierList.add(new Skier(97, 7, 7, 1, 1, 1));
            skierList.add(new Skier(98, 8, 8, 1, 1, 1));
            skierList.add(new Skier(99, 9, 9, 1, 1, 1));
            skierList.add(new Skier(100, 10, 10, 1, 1, 1));


            for (int i = 0; i < 100; i = i + 1) {
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
            assertEquals(100, latencies.length);
            assertNotEquals(test, latencies);
            System.out.println("Latencies: ");
            // for loop to print out the array
            for (int i = 0; i < latencies.length; i++) {
                System.out.printf("No: %d = %d ms\n", i + 1, latencies[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
