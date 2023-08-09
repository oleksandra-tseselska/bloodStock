package com.blood_stock_server.business.service;

import com.blood_stock_server.model.Address;
import com.blood_stock_server.model.BloodGroup;
import com.blood_stock_server.model.BloodInfo;
import com.blood_stock_server.model.BloodLocationInfo;

import java.util.List;
import java.util.Optional;

public interface BloodGroupService {
    List<BloodGroup> findAllBloodGroups();

    BloodGroup findBloodGroupById(Long id);

//    List<BloodLocationInfo> getBloodByBloodGroupAndLocation(Long bloodGroupId, String city);
    List<Address> getBloodByBloodGroupAndLocation(Long bloodGroupId, String city);
}
