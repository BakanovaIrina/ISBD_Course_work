package com.example.backend4;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.backend4.model.db_entity.Address;
import com.example.backend4.model.db_entity.Delivery;
import com.example.backend4.model.db_entity.Storage;
import com.example.backend4.repository.AddressRepository;
import com.example.backend4.repository.DeliveryRepository;
import com.example.backend4.repository.StorageRepository;
import com.example.backend4.services.LocationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;

class LocationServiceTest {

    @InjectMocks
    private LocationService locationService;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private DeliveryRepository deliveryRepository;

    @Mock
    private StorageRepository storageRepository;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAddresses() {
        Address address1 = new Address(1, "Россия", "Ленинградская область", "Всеволожск", "ул. Добрая", "12", 22);
        Address address2 = new Address(2, "Россия", "Ленинградская область", "Гатчина", "ул. Красивая", "121", 12);
        List<Address> addressList = Arrays.asList(address1, address2);

        when(addressRepository.findAll()).thenReturn(addressList);

        List<Address> result = locationService.getAddresses();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(addressRepository, times(1)).findAll();
    }

    @Test
    void testGetDeliveries() {
        Delivery delivery1 = new Delivery(1, 1, 1, 1, "На подоконнике");
        Delivery delivery2 = new Delivery(2, 2, 2, 2, "Под елкой");
        List<Delivery> deliveryList = Arrays.asList(delivery1, delivery2);

        when(deliveryRepository.findAll()).thenReturn(deliveryList);
        List<Delivery> result = locationService.getDeliveries();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(deliveryRepository, times(1)).findAll();
    }

    @Test
    void testGetStorages() {
        Storage storage1 = new Storage(1, 1);
        Storage storage2 = new Storage(2, 2);
        List<Storage> storageList = Arrays.asList(storage1, storage2);

        when(storageRepository.findAll()).thenReturn(storageList);

        List<Storage> result = locationService.getStorages();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(storageRepository, times(1)).findAll();
    }

    @Test
    void testMoveGiftToDelivery_Failure1() {
        doThrow(new RuntimeException("Database error")).when(deliveryRepository).moveGiftToDelivery();

        boolean result = locationService.moveGiftToDelivery();

        assertFalse(result);
        verify(deliveryRepository, times(1)).moveGiftToDelivery();
    }

    @Test
    void testMoveGiftToDelivery_Success() {
        doNothing().when(deliveryRepository).moveGiftToDelivery();
        boolean result = locationService.moveGiftToDelivery();

        assertTrue(result);
        verify(deliveryRepository, times(1)).moveGiftToDelivery();
    }

    @Test
    void testMoveGiftToDelivery_Failure() {
        doThrow(new RuntimeException("Database error")).when(deliveryRepository).moveGiftToDelivery();
        boolean result = locationService.moveGiftToDelivery();

        assertFalse(result);
        verify(deliveryRepository, times(1)).moveGiftToDelivery();
    }
}

