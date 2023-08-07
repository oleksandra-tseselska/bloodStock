package com.blood_stock_server.business.repository;

import com.blood_stock_server.business.repository.model.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
    boolean existsByCityAndStreetAndBuildingNumber(String city, String street, Integer buildingNumber);
}
