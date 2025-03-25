package com.example.backend4.controller;

import com.example.backend4.model.db_entity.Actions;
import com.example.backend4.model.db_entity.Address;
import com.example.backend4.model.db_entity.Child;
import com.example.backend4.model.db_entity.Letter;
import com.example.backend4.model.request.AddLetterRequest;
import com.example.backend4.model.request.AssignElfRequest;
import com.example.backend4.services.ChildService;
import com.example.backend4.services.LocationService;
import com.example.backend4.services.ProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/santa")
public class SantaController {
    @Autowired
    ChildService childService;

    @Autowired
    LocationService locationService;

    @Autowired
    ProductionService productionService;

    @GetMapping("/actions")
    public List<Actions> getAction(){
        return childService.getActions();
    }

    @GetMapping("/address")
    public List<Address> getAddress(){
        return locationService.getAddresses();
    }

    @GetMapping("/children")
    public List<Child> getChildren(){
        return childService.getChildren();
    }

    @GetMapping("/letters")
    public List<Letter> getLetters(){
        return childService.getLetters();
    }

    @PostMapping("/letter")
    public ResponseEntity<Object> addLetter(@RequestBody AddLetterRequest request){
        if(childService.addLetter(request)){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/production/{id}")
    public ResponseEntity<Object> addProduction(@PathVariable("id") Integer giftId){
        if(productionService.addGiftToProduction(giftId)){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/assign_elf")
    public ResponseEntity<Object> assignElf(@RequestBody AssignElfRequest request){
        if(productionService.assignElfToProduction(request.idElf, request.idProduction)){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/gifts/deliver")
    public ResponseEntity<Object> giftsToDelivery(){
        if(locationService.moveGiftToDelivery()){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
