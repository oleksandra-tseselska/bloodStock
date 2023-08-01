package com.blood_stock_server.business.repository;

import com.blood_stock_server.business.repository.model.BloodGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BloodGroupRepository extends JpaRepository<BloodGroupEntity, Long> {
}
