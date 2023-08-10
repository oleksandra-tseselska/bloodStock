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
import java.util.List;

import static com.blood_stock_server.business.specifications.BloodInfoEntitySpecifications.expiryDateCheck;
import static com.blood_stock_server.business.specifications.BloodInfoEntitySpecifications.presenceBloodByGroup;

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

    @Override
    public List<BloodInfo> findAllBloodInfoByBloodGroup(String bloodGroup) {
        List<BloodInfoEntity> bloodInfoEntities = repository.findAll(presenceBloodByGroup(bloodGroup).and(expiryDateCheck()));
        log.info("Provided blood group is: {}", bloodGroup);
        log.info("Get blood info list by providing blood group. Size is: {}", bloodInfoEntities::size);
        return bloodInfoEntities.stream().map(mapper::bloodInfoEntityToBloodInfo).toList();
    }
}
