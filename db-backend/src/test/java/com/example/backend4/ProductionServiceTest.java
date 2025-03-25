package com.example.backend4;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.backend4.model.db_entity.Elf;
import com.example.backend4.model.db_entity.ElfStatus;
import com.example.backend4.model.db_entity.Elf_production;
import com.example.backend4.model.db_entity.Production;
import com.example.backend4.repository.ElfProductionRepository;
import com.example.backend4.repository.ElfRepository;
import com.example.backend4.repository.ElfStatusRepository;
import com.example.backend4.repository.ProductionRepository;
import com.example.backend4.services.ProductionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ProductionServiceTest {

    @Mock
    private ElfRepository elfRepository;

    @Mock
    private ElfProductionRepository elfProductionRepository;

    @Mock
    private ElfStatusRepository elfStatusRepository;

    @Mock
    private ProductionRepository productionRepository;

    @InjectMocks
    private ProductionService productionService;

    private Elf elf;
    private Elf_production elfProduction;
    private ElfStatus elfStatus;
    private Production production;

    @BeforeEach
    void setUp() {
        elf = new Elf(1, "Тестовый эльф");
        elfProduction = new Elf_production(1, 1, 1);
        elfStatus = new ElfStatus(1, 1, Date.valueOf("2025-01-01"), true);
        production = new Production(1, 1, "in production");
    }

    @Test
    void testGetElves() {
        when(elfRepository.findAll()).thenReturn(Arrays.asList(elf));

        List<Elf> elves = productionService.getElves();

        assertNotNull(elves);
        assertEquals(1, elves.size());
        verify(elfRepository, times(1)).findAll();
    }

    @Test
    void testGetElfProductions() {
        when(elfProductionRepository.findAll()).thenReturn(Arrays.asList(elfProduction));

        List<Elf_production> elfProductions = productionService.getElfProductions();

        assertNotNull(elfProductions);
        assertEquals(1, elfProductions.size());
        verify(elfProductionRepository, times(1)).findAll();
    }

    @Test
    void testGetElfStatus() {
        when(elfStatusRepository.findAll()).thenReturn(Arrays.asList(elfStatus));

        List<ElfStatus> elfStatuses = productionService.getElfStatus();

        assertNotNull(elfStatuses);
        assertEquals(1, elfStatuses.size());
        verify(elfStatusRepository, times(1)).findAll();
    }

    @Test
    void testGetProductions() {
        when(productionRepository.findAll()).thenReturn(Arrays.asList(production));

        List<Production> productions = productionService.getProductions();

        assertNotNull(productions);
        assertEquals(1, productions.size());
        verify(productionRepository, times(1)).findAll();
    }

    @Test
    void testAddGiftToProduction_Success() {
        doNothing().when(productionRepository).addGiftToProduction(1);

        boolean result = productionService.addGiftToProduction(1);

        assertTrue(result);
        verify(productionRepository, times(1)).addGiftToProduction(1);
    }

    @Test
    void testAddIncorrectGiftToProduction() {
        doNothing().when(productionRepository).addGiftToProduction(333);
        boolean result = productionService.addGiftToProduction(334);
        assertFalse(result);
        verify(productionRepository, times(1)).addGiftToProduction(333);
    }

    @Test
    void testAddGiftToProduction_Failure() {
        doThrow(new RuntimeException("Error")).when(productionRepository).addGiftToProduction(1);

        boolean result = productionService.addGiftToProduction(1);

        assertFalse(result);
        verify(productionRepository, times(1)).addGiftToProduction(1);
    }

    @Test
    void testAssignElfToProduction_Success() {
        doNothing().when(elfRepository).assignElfsToProduction(1, 1);

        boolean result = productionService.assignElfToProduction(1, 1);

        assertTrue(result);
        verify(elfRepository, times(1)).assignElfsToProduction(1, 1);
    }

    @Test
    void testAssignElfToProduction_Failure() {
        doThrow(new RuntimeException("Error")).when(elfRepository).assignElfsToProduction(1, 1);

        boolean result = productionService.assignElfToProduction(1, 1);

        assertFalse(result);
        verify(elfRepository, times(1)).assignElfsToProduction(1, 1);
    }

    @Test
    void testCompleteProduction_Success() {
        doNothing().when(productionRepository).completeProduction(1);

        boolean result = productionService.completeProduction(1);

        assertTrue(result);
        verify(productionRepository, times(1)).completeProduction(1);
    }

    @Test
    void testCompleteProduction_Failure() {
        doThrow(new RuntimeException("Error")).when(productionRepository).completeProduction(1);

        boolean result = productionService.completeProduction(1);

        assertFalse(result);
        verify(productionRepository, times(1)).completeProduction(1);
    }
}

