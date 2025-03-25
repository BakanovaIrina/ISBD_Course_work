package com.example.backend4.services;

import com.example.backend4.model.db_entity.Elf;
import com.example.backend4.model.db_entity.ElfStatus;
import com.example.backend4.model.db_entity.Elf_production;
import com.example.backend4.model.db_entity.Production;
import com.example.backend4.repository.ElfProductionRepository;
import com.example.backend4.repository.ElfRepository;
import com.example.backend4.repository.ElfStatusRepository;
import com.example.backend4.repository.ProductionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductionService {

    @Autowired
    ElfRepository elfRepository;

    @Autowired
    ElfProductionRepository elfProductionRepository;

    @Autowired
    ElfStatusRepository elfStatusRepository;

    @Autowired
    ProductionRepository productionRepository;

    public List<Elf> getElves() {return elfRepository.findAll();}

    public List<Elf_production> getElfProductions(){return elfProductionRepository.findAll();}

    public List<ElfStatus> getElfStatus(){return elfStatusRepository.findAll();}

    public List<Production> getProductions(){return productionRepository.findAll();}

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
