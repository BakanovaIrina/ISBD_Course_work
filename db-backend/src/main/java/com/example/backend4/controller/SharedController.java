package com.example.backend4.controller;

import com.example.backend4.model.db_entity.*;
import com.example.backend4.services.ChildService;
import com.example.backend4.services.LocationService;
import com.example.backend4.services.ProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/shared")
public class SharedController {
    @Autowired
    ChildService childService;

    @Autowired
    LocationService locationService;

    @Autowired
    ProductionService productionService;

    @GetMapping("/gifts")
    public List<Gift> getGifts(){
        return childService.getGifts();
    }

    @GetMapping("/gift_status")
    public List<GiftStatus> getGiftStatus(){
        return childService.getGiftStatus();
    }

    @GetMapping("/elves")
    public List<Elf> getElves(){
        return productionService.getElves();
    }

    @GetMapping("/elf_production")
    public List<Elf_production> getElfProduction(){
        return productionService.getElfProductions();
    }

    @GetMapping("/elf_status")
    public List<ElfStatus> getElfStatus(){
        return productionService.getElfStatus();
    }

    @GetMapping("/productions")
    public List<Production> getProductions(){
        return productionService.getProductions();
    }

    @GetMapping("/deliveries")
    public List<Delivery> getDeliveries(){
        return locationService.getDeliveries();
    }

    @GetMapping("/storages")
    public List<Storage> getStorages(){
        return locationService.getStorages();
    }
}
