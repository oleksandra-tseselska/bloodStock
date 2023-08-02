package com.blood_stock_server.business.mappers;

import com.blood_stock_server.business.repository.model.BloodGroupEntity;
import com.blood_stock_server.business.repository.model.BloodInfoEntity;
import com.blood_stock_server.business.repository.model.BloodStorageEntity;
import com.blood_stock_server.model.BloodInfo;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.collections.CollectionUtils.isNotEmpty;

@Mapper(componentModel = "spring", uses = {
//        DateTimeMapStructMapper.class,
        BloodGroupMapper.class,
        BloodStorageMapper.class
})
public interface BloodInfoMapper {
    BloodInfoEntity bloodInfoToBloodInfoEntity(BloodInfo bloodInfo);

    BloodInfo bloodInfoEntityToBloodInfo(BloodInfoEntity bloodInfoEntity);

    default BloodGroupEntity bloodGroupIdToBloodGroupEntity(Long bloodGroupId) {
        if (bloodGroupId == null) {
            return null;
        }
        return new BloodGroupEntity(bloodGroupId);
    }

    default Long bloodGroupIdToBloodGroup(BloodGroupEntity bloodGroupEntity) {
        if (bloodGroupEntity == null) {
            return null;
        }
        return bloodGroupEntity.getId();
    }

    default List<BloodStorageEntity> bloodStorageEntityTpBloodStorageIds(List<Long> bloodStorageIds) {
        List<BloodStorageEntity> BloodStorageEntities = new ArrayList<>();
        if (isNotEmpty(bloodStorageIds)) {
            bloodStorageIds.forEach(bloodStorageId -> BloodStorageEntities.add(new BloodStorageEntity(bloodStorageId)));
        }
        return BloodStorageEntities;
    }

    default List<Long> bloodStorageIdsToBloodStorageEntity(List<BloodStorageEntity> BloodStorageEntities) {
        List<Long> BloodStorageIds = new ArrayList<>();
        if (isNotEmpty(BloodStorageEntities)) {
            BloodStorageEntities.forEach(bloodStorageEntity -> BloodStorageIds.add(bloodStorageEntity.getId()));
        }
        return BloodStorageIds;
    }
}
