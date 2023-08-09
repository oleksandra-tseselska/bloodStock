package com.blood_stock_server.business.service.impl;

import com.blood_stock_server.business.exceptions.ExistInDataBaseException;
import com.blood_stock_server.business.mappers.BloodStorageMapper;
import com.blood_stock_server.business.repository.BloodStorageRepository;
import com.blood_stock_server.business.repository.model.BloodStorageEntity;
import com.blood_stock_server.business.service.BloodStorageService;
import com.blood_stock_server.model.BloodStorage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

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
            throw new ExistInDataBaseException("Blood service with same fields already exist");
        }
        BloodStorageEntity bloodStorageEntity = repository.save(mapper.bloodStorageToBloodEntity(bloodStorage));
        return mapper.bloodStorageEntityToBloodStorage(bloodStorageEntity);
    }

    @Override
    public List<BloodStorage> findAllBloodStorages() {
        List<BloodStorageEntity> bloodStorages = repository.findAll();
        log.info("Get employee list. Size is: {}", bloodStorages::size);
        return bloodStorages.stream().map(mapper::bloodStorageEntityToBloodStorage).collect(Collectors.toList());
    }

    @Override
    public BloodStorage findBloodStorageById(Long id) {
        Optional<BloodStorage> bloodStorageById = repository.findById(id)
                .flatMap(bloodStorage -> Optional.ofNullable(mapper.bloodStorageEntityToBloodStorage(bloodStorage)));
        if (bloodStorageById.isPresent()) {
            log.info("Blood storage with id {} is {}", id, bloodStorageById.get());
            return bloodStorageById.get();
        }
        log.warn("Blood storage with id {} is not found.", id);
        throw new NoSuchElementException();
    }

}
