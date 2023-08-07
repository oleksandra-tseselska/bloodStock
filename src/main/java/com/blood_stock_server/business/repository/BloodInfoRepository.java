package com.blood_stock_server.business.repository;

import com.blood_stock_server.business.repository.model.BloodInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BloodInfoRepository extends JpaRepository<BloodInfoEntity, Long> {
}
