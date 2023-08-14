package com.blood_stock_server.business.service.impl;

import com.blood_stock_server.business.exceptions.ExistInDataBaseException;
import com.blood_stock_server.business.mappers.BloodStorageMapper;
import com.blood_stock_server.business.repository.BloodStorageRepository;
import com.blood_stock_server.business.repository.model.BloodStorageEntity;
import com.blood_stock_server.model.BloodStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class BloodStorageServiceImplTest {
    @Mock
    BloodStorageMapper mapper;
    @Mock
    BloodStorageRepository repository;
    @InjectMocks
    BloodStorageServiceImpl service;
    private List<BloodStorageEntity> bloodStorageEntities;
    private List<BloodStorage> bloodStorages;
    private BloodStorageEntity bloodStorageEntity;
    private BloodStorage bloodStorage;
    private BloodStorage notExistBloodStorage;
    private BloodStorage updatedBloodStorage;

    @BeforeEach
    void init() {
        bloodStorageEntities = createListOfBloodStorageEntities();
        bloodStorages = createListOfBloodStorages();
        bloodStorageEntity = createBloodStorageEntity();
        bloodStorage = createBloodStorage();
        notExistBloodStorage = createBloodStorage();
        updatedBloodStorage = createUpdatedBloodStorage();
    }

    @Test
    void findAll_Success() {
        when(repository.findAll()).thenReturn(bloodStorageEntities);
        when(mapper.bloodStorageEntityToBloodStorage(bloodStorageEntity)).thenReturn(bloodStorage);
        List<BloodStorage> expected = service.findAllBloodStorages();
        assertEquals(repository.findAll(), bloodStorageEntities);
        assertEquals(expected, bloodStorages);
        verify(repository, times(2)).findAll();
    }

    @Test
    void getBloodStorageById_Success() {
        when(repository.findById(1L)).thenReturn(Optional.of(bloodStorageEntity));
        when(mapper.bloodStorageEntityToBloodStorage(bloodStorageEntity)).thenReturn(bloodStorage);
        final BloodStorage expected = service.findBloodStorageById(1L);
        assertEquals(expected, bloodStorage);
    }

    @Test
    void getBloodStorageById_NotFound() {
        when(repository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> service.findBloodStorageById(2L));
    }

    @Test
    void saveBloodStorage_Success() {
        when(repository.save(bloodStorageEntity)).thenReturn(bloodStorageEntity);
        when(mapper.bloodStorageEntityToBloodStorage(bloodStorageEntity)).thenReturn(bloodStorage);
        when(mapper.bloodStorageToBloodEntity(bloodStorage)).thenReturn(bloodStorageEntity);
        BloodStorage savedBloodStorage = service.saveBloodStorage(bloodStorage);
        assertEquals(1L, savedBloodStorage.getId());
        assertEquals("email1@gmail.com", savedBloodStorage.getEmail());
        assertEquals("12345678", savedBloodStorage.getPhoneNumber());
        assertEquals("address1", savedBloodStorage.getAddress());
        verify(repository, times(1)).save(bloodStorageEntity);
    }
    @Test
    void saveBloodStorage_ExistInDataBaseException() {
        when(repository.existsByEmailAndPhoneNumberAndAddress(notExistBloodStorage.getEmail(),
                notExistBloodStorage.getPhoneNumber(), notExistBloodStorage.getAddress()))
                .thenReturn(true);
        assertTrue(repository.existsByEmailAndPhoneNumberAndAddress
                (bloodStorage.getEmail(), bloodStorage.getPhoneNumber(), bloodStorage.getAddress()));
        assertThrows(ExistInDataBaseException.class, () -> service.saveBloodStorage(notExistBloodStorage));
        verify(repository, times(0))
                .save(mapper.bloodStorageToBloodEntity(notExistBloodStorage));
    }
    @Test
    void updateBloodStorage_Success() {
        when(repository.existsById(bloodStorage.getId()))
                .thenReturn(true);
        assertTrue(repository.existsById(bloodStorage.getId()));
        service.updateBloodStorage(updatedBloodStorage, 1L);
        verify(repository, times(1))
                .save(mapper.bloodStorageToBloodEntity(updatedBloodStorage));
    }
    @Test
    void updateBloodStorage_NoSuchElementException() {
        when(repository.existsById(notExistBloodStorage.getId()))
                .thenReturn(false);
        assertFalse(repository.existsById((bloodStorage.getId())));
        assertThrows(NoSuchElementException.class, () -> service.updateBloodStorage(notExistBloodStorage, 999L));
        verify(repository, times(0))
                .save(mapper.bloodStorageToBloodEntity(notExistBloodStorage));
    }

    private List<BloodStorageEntity> createListOfBloodStorageEntities(){
        List<BloodStorageEntity> bloodStorageEntities = new ArrayList<>();
        bloodStorageEntities.add(createBloodStorageEntity());
        return bloodStorageEntities;
    }
    private List<BloodStorage> createListOfBloodStorages(){
        List<BloodStorage> bloodStorages = new ArrayList<>();
        bloodStorages.add(createBloodStorage());
        return bloodStorages;
    }


    private BloodStorageEntity createBloodStorageEntity(){
        BloodStorageEntity bloodStorageEntity = new BloodStorageEntity();
        bloodStorageEntity.setId(1L);
        bloodStorageEntity.setEmail("email1@gmail.com");
        bloodStorageEntity.setPhoneNumber("12345678");
        bloodStorageEntity.setAddress("address1");

        return bloodStorageEntity;
    }

    private BloodStorage createBloodStorage(){
        BloodStorage bloodStorage = new BloodStorage();
        bloodStorage.setId(1L);
        bloodStorage.setEmail("email1@gmail.com");
        bloodStorage.setPhoneNumber("12345678");
        bloodStorage.setAddress("address1");

        return bloodStorage;
    }
    private BloodStorage createUpdatedBloodStorage(){
        BloodStorage bloodStorage = new BloodStorage();
        bloodStorage.setId(1L);
        bloodStorage.setEmail("email111@gmail.com");
        bloodStorage.setPhoneNumber("11111111");
        bloodStorage.setAddress("address111");

        return bloodStorage;
    }
}
