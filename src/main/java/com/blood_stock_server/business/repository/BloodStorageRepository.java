package com.blood_stock_server.business.repository;

import com.blood_stock_server.business.repository.model.BloodStorageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BloodStorageRepository extends JpaRepository<BloodStorageEntity, Long> {
    boolean existsByEmailAndPhoneNumberAndAddress(String email, String phoneNumber, String address);

    boolean existsById(Long id);
}
