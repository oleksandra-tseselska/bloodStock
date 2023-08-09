package com.blood_stock_server.business.mappers;

import com.blood_stock_server.business.repository.model.BloodGroupEntity;
import com.blood_stock_server.business.repository.model.BloodInfoEntity;
import com.blood_stock_server.model.BloodGroup;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.collections.CollectionUtils.isNotEmpty;

@Mapper(componentModel = "spring", uses = {
        BloodInfoMapper.class})
public interface BloodGroupMapper {
    BloodGroupEntity bloodGroupToBloodGroupEntity(BloodGroup bloodGroup);

    BloodGroup bloodGroupEntityToBloodGroup(BloodGroupEntity bloodGroupEntity);

    default List<BloodInfoEntity> bloodInfoIdsToBloodInfoEntities(List<Long> bloodInfoIds) {
        List<BloodInfoEntity> bloodInfoEntities = new ArrayList<>();
        if (isNotEmpty(bloodInfoIds)) {
            bloodInfoIds.forEach(bloodInfoId -> bloodInfoEntities.add(new BloodInfoEntity(bloodInfoId)));
        }
        return bloodInfoEntities;
    }

    default List<Long> bloodInfoEntitiesToBloodInfoIds(List<BloodInfoEntity> bloodInfoEntities) {
        List<Long> bloodInfoIds = new ArrayList<>();
        if (isNotEmpty(bloodInfoEntities)) {
            bloodInfoEntities.forEach(bloodInfoEntity -> bloodInfoIds.add(bloodInfoEntity.getId()));
        }
        return bloodInfoIds;
    }
}
