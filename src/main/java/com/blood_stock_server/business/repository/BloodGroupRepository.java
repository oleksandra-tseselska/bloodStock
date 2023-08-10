package com.blood_stock_server.business.repository;

import com.blood_stock_server.business.repository.model.BloodGroupEntity;
import com.blood_stock_server.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BloodGroupRepository extends JpaRepository<BloodGroupEntity, Long> {
    //    @Query(value = "SELECT blood_group.blood_group, blood_info.expire_date, blood_storage_address.city, blood_storage_address.street, " +
//            "blood_storage_address.building_number, blood_storage_address.office, blood_storage.email, blood_storage.phone_number " +
//            "FROM blood_group " +
//            "INNER JOIN blood_info ON blood_group.blood_group_id = blood_info.blood_group_id " +
//            "INNER JOIN blood_storage ON blood_info.blood_storage_id = blood_storage.blood_storage_id " +
//            "INNER JOIN blood_storage_address ON blood_storage.blood_storage_id = blood_storage_address.blood_storage_id " +
//            "WHERE blood_info.blood_group_id = ?1 AND blood_storage_address.city = ?2;")
//    List<BloodLocationInfo> findBloodLocationByBloodGroupAndLocation(Long bloodGroupId, String city);
//    @Query(value = "SELECT i.blood_group, i.expire_date, a.city, a.street, " +
//            "a.building_number, a.office " +
//            "FROM BloodGroupEntity g " +
//            "INNER JOIN BloodInfoEntity i ON g.id = i.bloodGroupId " +
//            "INNER JOIN BloodStorageEntity s ON i.bloodStorageId = s.id " +
//            "INNER JOIN AddressEntity a ON s.id = a.bloodStorageId " +
//            "WHERE i.bloodGroupId = ?1 AND a.city = ?2;", nativeQuery=true)
//    List<BloodLocationInfo> findBloodLocationByBloodGroupAndLocation(Long bloodGroupId, String city);
//    @Query(value = "SELECT a.city, a.street, " +
//            "a.buildingNumber, a.office " +
//            "FROM BloodGroupEntity g " +
//            "INNER JOIN BloodInfoEntity i ON g.id = i.bloodGroupId " +
//            "INNER JOIN BloodStorageEntity s ON i.bloodStorageId = s.id " +
//            "INNER JOIN AddressEntity a ON s.id = a.bloodStorageId " +
//            "WHERE i.bloodGroupId = ?1 AND a.city = ?2;", nativeQuery=true)
//    List<Address> findBloodLocationByBloodGroupAndLocation(Long bloodGroupId, String city);
//    @Query(value = "SELECT a.city, a.street, " +
//            "a.buildingNumber, a.office " +
//            "FROM Address a " +
//            "INNER JOIN BloodStorage s ON a.id = s.addressIds " +
//            "INNER JOIN BloodInfo i ON s.id = s.bloodStorageId " +
//            "INNER JOIN BloodGroup g ON i.id = g.bloodInfoIds " +
//            "WHERE i.bloodGroupId = ?1 AND a.city = ?2;", nativeQuery=true)
//    List<Address> findBloodLocationByBloodGroupAndLocation(Long bloodGroupId, String city);
    @Query(value = "SELECT a.city, a.street, " +
            "a.buildingNumber, a.office " +
            "from Address a " +
            "inner join BloodStorage s ON a.id = s.addressIds " +
            "inner join BloodInfo i ON s.id = s.bloodStorageId " +
            "inner join BloodGroup g ON i.id = g.bloodInfoIds " +
            "where i.bloodGroupId = ?1 AND a.city = ?2;", nativeQuery = true)
    List<Address> findBloodLocationByBloodGroupAndLocation(Long bloodGroupId, String city);
}
