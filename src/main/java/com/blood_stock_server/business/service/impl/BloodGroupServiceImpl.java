package com.blood_stock_server.business.service.impl;

import com.blood_stock_server.business.mappers.BloodGroupMapper;
import com.blood_stock_server.business.repository.BloodGroupRepository;
import com.blood_stock_server.business.repository.model.BloodGroupEntity;
import com.blood_stock_server.business.service.BloodGroupService;
import com.blood_stock_server.model.BloodGroup;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class BloodGroupServiceImpl implements BloodGroupService {
    private final BloodGroupRepository repository;
    private final BloodGroupMapper mapper;

    @Override
    public List<BloodGroup> findAllBloodGroups() {
        List<BloodGroupEntity> bloodGroups = repository.findAll();
        log.info("Get blood group list. Size is: {}", bloodGroups::size);
        return bloodGroups.stream().map(mapper::bloodGroupEntityToBloodGroup).toList();
    }

    @Override
    public BloodGroup findBloodGroupById(Long id) {
        Optional<BloodGroup> bloodGroupById = repository.findById(id)
                .flatMap(bloodGroup -> Optional.ofNullable(mapper.bloodGroupEntityToBloodGroup(bloodGroup)));
        if (bloodGroupById.isPresent()) {
            log.info("Blood group with id {} is {}", id, bloodGroupById.get());
            return bloodGroupById.get();
        }
        log.warn("Blood group with id {} is not found.", id);
        throw new NoSuchElementException();
    }
}
