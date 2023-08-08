package com.blood_stock_server.business.service.impl;

import com.blood_stock_server.business.mappers.BloodInfoMapper;
import com.blood_stock_server.business.repository.BloodInfoRepository;
import com.blood_stock_server.business.repository.model.BloodInfoEntity;
import com.blood_stock_server.business.service.BloodInfoService;
import com.blood_stock_server.model.BloodInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Log4j2
@RequiredArgsConstructor
public class BloodInfoServiceImpl implements BloodInfoService {
    private final BloodInfoRepository repository;
    private final BloodInfoMapper mapper;

    @Override
    public BloodInfo saveBloodInfo(BloodInfo bloodInfo) {
        LocalDate bloodTaken = LocalDate.now();
        LocalDate expireDate = bloodTaken.plusDays(3);
        bloodInfo.setBloodTakenDate(bloodTaken);
        bloodInfo.setExpireDate(expireDate);
        BloodInfoEntity bloodInfoEntity = repository.save(mapper.bloodInfoToBloodInfoEntity(bloodInfo));
        return mapper.bloodInfoEntityToBloodInfo(bloodInfoEntity);
    }
}
