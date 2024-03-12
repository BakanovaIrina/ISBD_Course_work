package com.example.backend4.controller;

import com.example.backend4.model.db_entity.Actions;
import com.example.backend4.repository.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/action")
public class ActionController {

    @Autowired
    ActionRepository actionRepository;

    @GetMapping("/get")
    public List<Actions> getAction(){
        List<Actions> res = actionRepository.findAll();
        return res;
    }
}
