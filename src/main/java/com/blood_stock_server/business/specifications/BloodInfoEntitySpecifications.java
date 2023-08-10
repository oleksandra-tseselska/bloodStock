package com.blood_stock_server.business.specifications;

import com.blood_stock_server.business.repository.model.BloodGroupEntity;
import com.blood_stock_server.business.repository.model.BloodInfoEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import java.time.LocalDate;

public class BloodInfoEntitySpecifications {
    public static Specification<BloodInfoEntity> presenceBloodByGroup(String bloodGroup) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            Join<BloodGroupEntity, BloodInfoEntity> presenceBlood = root.join("bloodGroupId");
            return criteriaBuilder.equal(presenceBlood.get("group"), bloodGroup);
        };
    }

    public static Specification<BloodInfoEntity> expiryDateCheck() {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("expireDate"), LocalDate.now());
    }
}
