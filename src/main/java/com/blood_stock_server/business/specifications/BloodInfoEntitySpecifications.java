package com.blood_stock_server.business.specifications;

import com.blood_stock_server.business.repository.model.BloodGroupEntity;
import com.blood_stock_server.business.repository.model.BloodInfoEntity;
import com.blood_stock_server.business.repository.model.BloodStorageEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import java.time.LocalDate;

public class BloodInfoEntitySpecifications {

    public static Specification<BloodInfoEntity> expiryDateCheck() {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("expireDate"), LocalDate.now());
    }

    public static Specification<BloodInfoEntity> findByBloodGroupId(Long bloodGroupId) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("bloodGroupId"), bloodGroupId);
    }

    public static Specification<BloodInfoEntity> presenceBloodByGroup(String bloodGroup) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            Join<BloodGroupEntity, BloodInfoEntity> presenceBlood = root.join("bloodGroupId");
            return criteriaBuilder.equal(presenceBlood.get("group"), bloodGroup);
        };
    }

    public static Specification<BloodInfoEntity> presenceBloodByBloodStorageAddress(String bloodStorageAddress) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            Join<BloodStorageEntity, BloodInfoEntity> presenceBlood = root.join("bloodStorageId");
            return criteriaBuilder.like(presenceBlood.get("address"), "%" + bloodStorageAddress + "%");
        };
    }
}
