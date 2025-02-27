package com.example.backend4.controller;

import com.example.backend4.model.db_entity.*;
import com.example.backend4.model.request.AddLetterRequest;
import com.example.backend4.model.request.AssignElfRequest;
import com.example.backend4.services.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/tables")
public class TablesController {
    @Autowired
    TableService tableService;

    @GetMapping("/actions")
    public List<Actions> getAction(){
        return tableService.getActions();
    }

    @GetMapping("/address")
    public List<Address> getAddress(){
        return tableService.getAddresses();
    }

    @GetMapping("/child")
    public List<Child> getChild(){
        return tableService.getChildren();
    }

    @GetMapping("/delivery")
    public List<Delivery> getDelivery(){
        return tableService.getDeliveries();
    }

    @GetMapping("/elf")
    public List<Elf> getElf(){
        return tableService.getElfs();
    }

    @GetMapping("/elf_production")
    public List<Elf_production> getElfProduction(){
        return tableService.getElfProductions();
    }

    @GetMapping("/elf_status")
    public List<ElfStatus> getElfStatus(){
        return tableService.getElfStatus();
    }

    @GetMapping("/gift")
    public List<Gift> getGift(){
        return tableService.getGifts();
    }

    @GetMapping("/gift_status")
    public List<GiftStatus> getGiftStatus(){
        return tableService.getGiftStatus();
    }

    @GetMapping("/letter")
    public List<Letter> getLetter(){
        return tableService.getLetters();
    }

    @GetMapping("/production")
    public List<Production> getProduction(){
        return tableService.getProductions();
    }

    @GetMapping("/storage")
    public List<Storage> getStorage(){
        return tableService.getStorages();
    }

    @PostMapping("/add_letter")
    public ResponseEntity<Object> addLetter(@RequestBody AddLetterRequest request){
        if(tableService.addLetter(request)){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/add_prod/{id}")
    public ResponseEntity<Object> addProduction(@PathVariable("id") Integer giftId){
        if(tableService.addGiftToProduction(giftId)){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/assign_elf")
    public ResponseEntity<Object> assignElf(@RequestBody AssignElfRequest request){
        if(tableService.assignElfToProduction(request.idElf, request.idProduction)){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/gifts_to_delivery")
    public ResponseEntity<Object> giftsToDelivery(){
        if(tableService.moveGiftToDelivery()){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/complete_prod/{id}")
    public ResponseEntity<Object> completeProduction(@PathVariable("id") Integer giftId){
        if(tableService.completeProduction(giftId)){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
