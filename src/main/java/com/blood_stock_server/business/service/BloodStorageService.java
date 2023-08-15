package com.blood_stock_server.business.service;

import com.blood_stock_server.model.BloodStorage;

import java.util.List;

public interface BloodStorageService {
    BloodStorage saveBloodStorage(BloodStorage bloodStorage);

    List<BloodStorage> findAllBloodStorages();

    BloodStorage findBloodStorageById(Long id);

    BloodStorage updateBloodStorage(BloodStorage bloodStorage, Long id);
}
