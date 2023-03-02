package com.skier.skiersproject;

import com.skier.skiersproject.Skier.Skier;
import com.skier.skiersproject.Skier.SkierController;
import com.skier.skiersproject.Skier.SkierService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SkiersProjectApplicationTests {
    SkierService skierService = new SkierService();

    @Test
    void contextLoads() {
    }

    @Test
    public void testFindAllService() throws Exception {
        ResponseEntity<String> result = this.skierService.getSkiers();
        assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    public void testFindById() throws Exception {
        ResponseEntity<String> result = this.skierService.getSkierById(1);
        assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    public void testAddSkier() throws Exception {
        Skier skier = new Skier(1, 1, 1, 1, 1, 1);
        ResponseEntity<String> result = this.skierService.addSkier(skier);
        assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    public void testDeleteSkier() throws Exception {
        ResponseEntity<String> result = this.skierService.deleteSkier(1);
        assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    public void testUpdateSkier() throws Exception {
        Skier skier = new Skier(1, 1, 1, 1, 1, 1);
        ResponseEntity<String> result = this.skierService.updateSkier(skier);
        assertEquals(200, result.getStatusCodeValue());
    }

}
