package com.example.backend4;

import com.example.backend4.model.db_entity.*;
import com.example.backend4.model.request.AddLetterRequest;
import com.example.backend4.repository.*;
import com.example.backend4.services.ChildService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ChildServiceTest {

    @InjectMocks
    private ChildService childService;

    @Mock
    private ChildRepository childRepository;

    @Mock
    private GiftRepository giftRepository;

    @Mock
    private GiftStatusRepository giftStatusRepository;

    @Mock
    private LetterRepository letterRepository;

    @Mock
    private ActionRepository actionRepository;

    @Test
    void testGetChildren() {
        List<Child> children = List.of(new Child(), new Child());
        when(childRepository.findAll()).thenReturn(children);

        List<Child> result = childService.getChildren();

        assertEquals(2, result.size());
        verify(childRepository, times(1)).findAll();
    }

    @Test
    void testGetGifts() {
        List<Gift> gifts = List.of(new Gift(), new Gift(), new Gift());
        when(giftRepository.findAll()).thenReturn(gifts);

        List<Gift> result = childService.getGifts();

        assertEquals(3, result.size());
        verify(giftRepository, times(1)).findAll();
    }

    @Test
    void testGetGiftStatus() {
        List<GiftStatus> statuses = List.of(new GiftStatus());
        when(giftStatusRepository.findAll()).thenReturn(statuses);

        List<GiftStatus> result = childService.getGiftStatus();

        assertEquals(1, result.size());
        verify(giftStatusRepository, times(1)).findAll();
    }

    @Test
    void testGetLetters() {
        List<Letter> letters = List.of(new Letter(), new Letter());
        when(letterRepository.findAll()).thenReturn(letters);

        List<Letter> result = childService.getLetters();

        assertEquals(2, result.size());
        verify(letterRepository, times(1)).findAll();
    }

    @Test
    void testGetActions() {
        List<Actions> actions = List.of(new Actions(), new Actions(), new Actions());
        when(actionRepository.findAll()).thenReturn(actions);

        List<Actions> result = childService.getActions();

        assertEquals(3, result.size());
        verify(actionRepository, times(1)).findAll();
    }

    @Test
    void testAddLetter_Success() {
        AddLetterRequest request = new AddLetterRequest();
        request.childName = "childName";
        request.childSurname = "childSurname";
        request.country = "country";
        request.region = "region";
        request.city = "city";
        request.street = "street";
        request.house = "house";
        request.room = 1;
        request.giftName = "giftName";
        request.actions = "actions";
        request.descriptions = "descriptions";
        request.truth = true;
        request.approval = false;
        request.positivities = true;

        doNothing().when(letterRepository).callAddLetterFunction(
                eq(request.childName), eq(request.childSurname), eq(request.country), eq(request.region),
                eq(request.city), eq(request.street), eq(request.house), eq(request.room),
                eq(request.giftName), eq(request.actions), eq(request.descriptions), eq(request.truth),
                eq(request.approval), eq(request.positivities));

        boolean result = childService.addLetter(request);

        assertTrue(result);

        verify(letterRepository, times(1)).callAddLetterFunction(
                eq(request.childName), eq(request.childSurname), eq(request.country), eq(request.region),
                eq(request.city), eq(request.street), eq(request.house), eq(request.room),
                eq(request.giftName), eq(request.actions), eq(request.descriptions), eq(request.truth),
                eq(request.approval), eq(request.positivities));
    }

    @Test
    void testAddLetter_Failure() {
        AddLetterRequest request = new AddLetterRequest();
        request.childName = "childName";
        request.childSurname = "childSurname";
        request.country = "country";
        request.region = "region";
        request.city = "city";
        request.street = "street";
        request.house = "house";
        request.room = 1;
        request.giftName = "giftName";
        request.actions = "actions";
        request.descriptions = "descriptions";
        request.truth = true;
        request.approval = false;
        request.positivities = true;

        doThrow(new RuntimeException("Database error")).when(letterRepository)
                .callAddLetterFunction(eq(request.childName), eq(request.childSurname), eq(request.country), eq(request.region),
                        eq(request.city), eq(request.street), eq(request.house), eq(request.room),
                        eq(request.giftName), eq(request.actions), eq(request.descriptions), eq(request.truth),
                        eq(request.approval), eq(request.positivities));

        boolean result = childService.addLetter(request);

        assertFalse(result);

        verify(letterRepository, times(1)).callAddLetterFunction(
                eq(request.childName), eq(request.childSurname), eq(request.country), eq(request.region),
                eq(request.city), eq(request.street), eq(request.house), eq(request.room),
                eq(request.giftName), eq(request.actions), eq(request.descriptions), eq(request.truth),
                eq(request.approval), eq(request.positivities));
    }

}

