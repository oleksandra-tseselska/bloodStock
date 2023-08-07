package com.blood_stock_server.business.service;

import com.blood_stock_server.model.BloodGroup;

import java.util.List;
import java.util.Optional;

public interface BloodGroupService {
    List<BloodGroup> findAllBloodGroups();

    Optional<BloodGroup> findBloodGroupById(Long id);
}
