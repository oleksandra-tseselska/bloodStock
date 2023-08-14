package com.blood_stock_server.business.service.impl;

import com.blood_stock_server.business.mappers.BloodGroupMapper;
import com.blood_stock_server.business.repository.BloodGroupRepository;
import com.blood_stock_server.business.repository.model.BloodGroupEntity;
import com.blood_stock_server.model.BloodGroup;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class BloodGroupServiceImplTest {
    @Mock
    BloodGroupMapper mapper;
    @Mock
    BloodGroupRepository repository;
    @InjectMocks
    BloodGroupServiceImpl service;
    private List<BloodGroupEntity> bloodGroupEntities;
    private List<BloodGroup> bloodGroups;
    private BloodGroupEntity bloodGroupEntity;
    private BloodGroup bloodGroup;

    @BeforeEach
    void init() {
        bloodGroupEntities = createListOfBloodGroupEntities();
        bloodGroups = createListOfBloodGroups();
        bloodGroupEntity = createBloodGroupEntity();
        bloodGroup = createBloodGroup();
    }

    @Test
    void findAll_Success() {
        when(repository.findAll()).thenReturn(bloodGroupEntities);
        when(mapper.bloodGroupEntityToBloodGroup(bloodGroupEntity)).thenReturn(bloodGroup);
        List<BloodGroup> expected = service.findAllBloodGroups();
        assertEquals(repository.findAll(), bloodGroupEntities);
        assertEquals(expected, bloodGroups);
        verify(repository, times(2)).findAll();
    }

    @Test
    void getBloodGroupById_Success() {
        when(repository.findById(1L)).thenReturn(Optional.of(bloodGroupEntity));
        when(mapper.bloodGroupEntityToBloodGroup(bloodGroupEntity)).thenReturn(bloodGroup);
        final BloodGroup expected = service.findBloodGroupById(1L);
        assertEquals(expected, bloodGroup);
    }

    @Test
    void getBloodGroupById_NotFound() {
        when(repository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> service.findBloodGroupById(2L));
    }
    private List<BloodGroupEntity> createListOfBloodGroupEntities(){
        List<BloodGroupEntity> bloodGroupEntities = new ArrayList<>();
        bloodGroupEntities.add(createBloodGroupEntity());
        return bloodGroupEntities;
    }
    private List<BloodGroup> createListOfBloodGroups(){
        List<BloodGroup> bloodGroups = new ArrayList<>();
        bloodGroups.add(createBloodGroup());
        return bloodGroups;
    }


    private BloodGroupEntity createBloodGroupEntity(){
        BloodGroupEntity bloodGroupEntity = new BloodGroupEntity();
        bloodGroupEntity.setId(1L);
        bloodGroupEntity.setGroup("0-");

        return bloodGroupEntity;
    }

    private BloodGroup createBloodGroup(){
        BloodGroup bloodGroup = new BloodGroup();
        bloodGroup.setId(1L);
        bloodGroup.setGroup("0-");

        return bloodGroup;
    }
}
