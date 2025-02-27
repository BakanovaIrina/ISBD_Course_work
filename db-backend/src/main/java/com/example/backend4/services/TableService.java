package com.example.backend4.services;

import com.example.backend4.model.db_entity.*;
import com.example.backend4.model.request.AddLetterRequest;
import com.example.backend4.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableService {

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

    public List<Actions> getActions(){
        return actionRepository.findAll();
    }

    public List<Address> getAddresses(){
        return addressRepository.findAll();
    }

    public List<Child> getChildren(){return childRepository.findAll();}

    public List<Delivery> getDeliveries(){return deliveryRepository.findAll();}

    public List<Elf> getElfs(){return elfRepository.findAll();}

    public List<Elf_production> getElfProductions(){return elfProductionRepository.findAll();}

    public List<ElfStatus> getElfStatus(){return elfStatusRepository.findAll();}

    public List<Gift> getGifts(){return giftRepository.findAll();}

    public List<GiftStatus> getGiftStatus(){return giftStatusRepository.findAll();}

    public List<Letter> getLetters(){return letterRepository.findAll();}

    public List<Production> getProductions(){return productionRepository.findAll();}

    public List<Storage> getStorages(){return storageRepository.findAll();}

    public boolean addLetter(AddLetterRequest request){
        try {
            letterRepository.callAddLetterFunction(
                    request.childName,
                    request.childSurname,
                    request.country,
                    request.region,
                    request.city,
                    request.street,
                    request.house,
                    request.room,
                    request.giftName,
                    request.actions,
                    request.descriptions,
                    request.truth,
                    request.approval,
                    request.positivities);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean addGiftToProduction(Integer id){
        try {
            productionRepository.addGiftToProduction(id);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public boolean assignElfToProduction(Integer idElf, Integer idProduction){
        try {
            elfRepository.assignElfsToProduction(idElf, idProduction);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public boolean moveGiftToDelivery(){
        try {
            deliveryRepository.moveGiftToDelivery();
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public boolean completeProduction(Integer id){
        try {
            productionRepository.completeProduction(id);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
