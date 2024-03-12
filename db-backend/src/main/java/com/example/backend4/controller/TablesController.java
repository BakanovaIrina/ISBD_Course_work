package com.example.backend4.controller;

import com.example.backend4.model.db_entity.*;
import com.example.backend4.model.request.AddLetterRequest;
import com.example.backend4.model.request.AddProductionRequest;
import com.example.backend4.model.request.AssignElfRequest;
import com.example.backend4.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/tables")
public class TablesController {

    @Autowired
    ActionRepository actionRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    ChildRepository childRepository;

    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    ElfRepository elfRepository;

    @Autowired
    ElfProductionRepository elfProductionRepository;

    @Autowired
    ElfStatusRepository elfStatusRepository;

    @Autowired
    GiftRepository giftRepository;

    @Autowired
    GiftStatusRepository giftStatusRepository;

    @Autowired
    LetterRepository letterRepository;

    @Autowired
    ProductionRepository productionRepository;

    @Autowired
    StorageRepository storageRepository;

    @GetMapping("/actions")
    public List<Actions> getAction(){
        return actionRepository.findAll();
    }

    @GetMapping("/address")
    public List<Address> getAddress(){
        return addressRepository.findAll();
    }

    @GetMapping("/child")
    public List<Child> getChild(){
        List<Child> r = childRepository.findAll();
        return childRepository.findAll();
    }

    @GetMapping("/delivery")
    public List<Delivery> getDelivery(){
        List<Delivery> r = deliveryRepository.findAll();
        return deliveryRepository.findAll();
    }

    @GetMapping("/elf")
    public List<Elf> getElf(){
        return elfRepository.findAll();
    }

    @GetMapping("/elf_production")
    public List<Elf_production> getElfProduction(){
        return elfProductionRepository.findAll();
    }

    @GetMapping("/elf_status")
    public List<ElfStatus> getElfStatus(){
        return elfStatusRepository.findAll();
    }

    @GetMapping("/gift")
    public List<Gift> getGift(){
        return giftRepository.findAll();
    }

    @GetMapping("/gift_status")
    public List<GiftStatus> getGiftStatus(){
        return giftStatusRepository.findAll();
    }

    @GetMapping("/letter")
    public List<Letter> getLetter(){
        List<Letter> res = letterRepository.findAll();
        return letterRepository.findAll();
    }

    @GetMapping("/production")
    public List<Production> getProduction(){
        return productionRepository.findAll();
    }

    @GetMapping("/storage")
    public List<Storage> getStorage(){
        List<Storage> res = storageRepository.findAll();
        return storageRepository.findAll();
    }

    @PostMapping("/add_letter")
    public ResponseEntity<Object> addLetter(@RequestBody AddLetterRequest request){
        letterRepository.callAddLetterFunction(
                request.childName,
                request.childSurname,
                request.country1,
                request.region1,
                request.city1,
                request.street1,
                request.house1,
                request.room1,
                request.giftName,
                request.actions,
                request.descriptions,
                request.truth1,
                request.approval1,
                request.positivities);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/add_prod")
    public ResponseEntity<Object> addProduction(@RequestBody AddProductionRequest request){
        productionRepository.addGiftToProduction(request.giftId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/assign_elf")
    public ResponseEntity<Object> assignElf(@RequestBody AssignElfRequest request){
        elfRepository.assignElfsToProduction(request.idElf, request.idProduction);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/gifts_to_delivery")
    public ResponseEntity<Object> giftsToDelivery(){
        deliveryRepository.moveGiftToDelivery();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/complete_prod")
    public ResponseEntity<Object> completeProduction(@RequestBody AddProductionRequest request){
        productionRepository.completeProduction(request.giftId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
