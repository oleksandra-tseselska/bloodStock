package com.blood_stock_server.business.mappers;

import com.blood_stock_server.business.repository.model.BloodGroupEntity;
import com.blood_stock_server.business.repository.model.BloodInfoEntity;
import com.blood_stock_server.model.BloodGroup;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.collections.CollectionUtils.isNotEmpty;

@Mapper(componentModel = "spring", uses = {
//        DateTimeMapStructMapper.class,
        BloodInfoMapper.class
})
public interface BloodGroupMapper {
    BloodGroupEntity bloodGroupToBloodGroupEntity(BloodGroup bloodGroup);

    BloodGroup bloodGroupEntityToBloodGroup(BloodGroupEntity bloodGroupEntity);

    default List<BloodInfoEntity> bloodInfoIdsToBloodInfoEntities(List<Long> bloodInfoIds) {
        List<BloodInfoEntity> BloodInfoEntities = new ArrayList<>();
        if (isNotEmpty(bloodInfoIds)) {
            bloodInfoIds.forEach(bloodInfoId -> BloodInfoEntities.add(new BloodInfoEntity(bloodInfoId)));
        }
        return BloodInfoEntities;
    }

    default List<Long> bloodInfoEntitiesToBloodInfoIds(List<BloodInfoEntity> BloodInfoEntities) {
        List<Long> BloodInfoIds = new ArrayList<>();
        if (isNotEmpty(BloodInfoEntities)) {
            BloodInfoEntities.forEach(bloodInfoEntity -> BloodInfoIds.add(bloodInfoEntity.getId()));
        }
        return BloodInfoIds;
    }
}
