package com.skier.skiersproject.Skier;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;


public class SkierService {

    ArrayList<Skier> list = new ArrayList<Skier>();

    public SkierService() {

    }

    public ResponseEntity<String> getSkiers() {
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(this.list);
            return new ResponseEntity<>(json, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> getSkierById(int id) {
        try {
            for (Skier skier : this.list) {
                if (skier.getSkierId() == id) {
                    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
                    String json = ow.writeValueAsString(skier);
                    return new ResponseEntity<>(json, HttpStatus.OK);
                }
            }
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> addSkier(Skier skier) {
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(skier);
            this.list.add(skier);
            return new ResponseEntity<>(json, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> deleteSkier(int id) {
        try {
            for (Skier skier : this.list) {
                if (skier.getSkierId() == id) {
                    this.list.remove(skier);
                    return new ResponseEntity<>("Skier deleted", HttpStatus.OK);
                }
            }
            return new ResponseEntity<>("Skier not found", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> updateSkier(Skier skier) {
        try {
            for (Skier skier1 : this.list) {
                if (skier1.getSkierId() == skier.getSkierId()) {
                    skier1.setDayId(skier.getDayId());
                    skier1.setLiftId(skier.getLiftId());
                    skier1.setTime(skier.getTime());
                    skier1.setResortId(skier.getResortId());
                    skier1.setSeasonId(skier.getSeasonId());
                    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
                    String json = ow.writeValueAsString(skier1);
                    return new ResponseEntity<>(json, HttpStatus.OK);
                }
            }
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
