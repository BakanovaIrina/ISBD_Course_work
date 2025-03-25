package com.example.backend4.services;

import com.example.backend4.model.db_entity.Address;
import com.example.backend4.model.db_entity.Delivery;
import com.example.backend4.model.db_entity.Storage;
import com.example.backend4.repository.AddressRepository;
import com.example.backend4.repository.DeliveryRepository;
import com.example.backend4.repository.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    StorageRepository storageRepository;

    public List<Address> getAddresses(){
        return addressRepository.findAll();
    }

    public List<Delivery> getDeliveries(){return deliveryRepository.findAll();}

    public List<Storage> getStorages(){return storageRepository.findAll();}


    public boolean moveGiftToDelivery(){
        try {
            deliveryRepository.moveGiftToDelivery();
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
