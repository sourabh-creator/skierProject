package com.skier.skiersproject.Skier;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping(value = "/skier")
@Api(value = "/skier", description = "Operations pertaining to skier")
public class SkierController {

    SkierService skierService = new SkierService();

    public SkierController() {

    }


    @GetMapping(value = "/findAll", produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> getSkiers() {
        return this.skierService.getSkiers();
    }

    @ApiOperation(value = "Get a skier by id")
    @GetMapping("/findById", produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> getSkierById(@ApiParam(value = "Skier ID", required = true) @PathVariable int id) {
        return this.skierService.getSkierById(id);
    }

    @ApiOperation(value = "Add a skiers")
    @PostMapping(path = "/addSkier", consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> addSkier(@ApiParam(value = "Skier Body", required = true) @RequestBody Skier skier) {
        return this.skierService.addSkier(skier);
    }


    @ApiOperation(value = "Delete a skier")
    @PostMapping("/deleteSkier")
    public ResponseEntity<String> deleteSkier(@ApiParam(value = "Skier ID", required = true) @PathVariable int id) {
        return this.skierService.deleteSkier(id);
    }

    @ApiOperation(value = "Update a skier")
    @PostMapping("/updateSkier")
    public ResponseEntity<String> updateSkier(@ApiParam(value = "Skier Body", required = true) @RequestBody Skier skier) {
        return this.skierService.updateSkier(skier);
    }

}
