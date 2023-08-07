package com.blood_stock_server.business.service.impl;

import com.blood_stock_server.business.mappers.BloodStorageMapper;
import com.blood_stock_server.business.repository.BloodStorageRepository;
import com.blood_stock_server.business.repository.model.BloodStorageEntity;
import com.blood_stock_server.business.service.BloodStorageService;
import com.blood_stock_server.model.BloodStorage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class BloodStorageServiceImpl implements BloodStorageService {
    private final BloodStorageRepository repository;
    private final BloodStorageMapper mapper;

    @Override
    public BloodStorage saveBloodStorage(BloodStorage bloodStorage) {
        if(repository.existsByEmailAndPhoneNumber(bloodStorage.getEmail(), bloodStorage.getPhoneNumber())){
            log.error("Blood service with same fields already exist");
            throw new IllegalArgumentException("Blood service with same fields already exist");
        }
        BloodStorageEntity bloodStorageEntity = repository.save(mapper.bloodStorageToBloodEntity(bloodStorage));
        return mapper.bloodStorageEntityToBloodStorage(bloodStorageEntity);
    }

}
