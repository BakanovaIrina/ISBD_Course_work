package com.example.backend4.controller;

import com.example.backend4.services.ProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/elf")
public class ElfController {
    @Autowired
    ProductionService productionService;

    @PostMapping("/productions/{id}/complete")
    public ResponseEntity<Object> completeProduction(@PathVariable("id") Integer giftId){
        if(productionService.completeProduction(giftId)){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
