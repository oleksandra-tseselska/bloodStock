package com.blood_stock_server.business.service;

import com.blood_stock_server.model.BloodGroup;

import java.util.List;

public interface BloodGroupService {
    List<BloodGroup> findAllBloodGroups();

    BloodGroup findBloodGroupById(Long id);
}
