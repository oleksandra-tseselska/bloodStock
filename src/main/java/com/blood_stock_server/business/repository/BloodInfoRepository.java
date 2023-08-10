package com.blood_stock_server.business.repository;

import com.blood_stock_server.business.repository.model.BloodInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BloodInfoRepository extends JpaRepository<BloodInfoEntity, Long>, JpaSpecificationExecutor<BloodInfoEntity> {
}
