package com.blood_stock_server.business.service;

import com.blood_stock_server.model.BloodInfo;

import java.util.List;

public interface BloodInfoService {
    BloodInfo saveBloodInfo(BloodInfo bloodInfo);

    List<BloodInfo> findAllBloodInfoByBloodGroup(String bloodGroup);

    List<BloodInfo> findAllBloodInfoByBloodIdAndStorageAddress(Long bloodGroupId, String bloodStorageAddress);
}
