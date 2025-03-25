package com.example.backend4.services;

import com.example.backend4.model.db_entity.*;
import com.example.backend4.model.request.AddLetterRequest;
import com.example.backend4.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChildService {

    @Autowired
    ChildRepository childRepository;

    @Autowired
    GiftRepository giftRepository;

    @Autowired
    GiftStatusRepository giftStatusRepository;

    @Autowired
    LetterRepository letterRepository;

    @Autowired
    ActionRepository actionRepository;

    public List<Child> getChildren(){return childRepository.findAll();}

    public List<Gift> getGifts(){return giftRepository.findAll();}

    public List<GiftStatus> getGiftStatus(){return giftStatusRepository.findAll();}

    public List<Letter> getLetters(){return letterRepository.findAll();}

    public List<Actions> getActions(){
        return actionRepository.findAll();
    }

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
}
