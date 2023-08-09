package com.blood_stock_server.business.mappers;

import com.blood_stock_server.business.repository.model.BloodGroupEntity;
import com.blood_stock_server.business.repository.model.BloodInfoEntity;
import com.blood_stock_server.business.repository.model.BloodStorageEntity;
import com.blood_stock_server.model.BloodInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {
        DateTimeMapStructMapper.class,
        BloodGroupMapper.class,
        BloodStorageMapper.class})
public interface BloodInfoMapper {
    BloodInfoEntity bloodInfoToBloodInfoEntity(BloodInfo bloodInfo);

    BloodInfo bloodInfoEntityToBloodInfo(BloodInfoEntity bloodInfoEntity);

    default BloodGroupEntity bloodGroupIdToBloodGroupEntity(Long bloodGroupId) {
        if (bloodGroupId == null) {
            return null;
        }
        return new BloodGroupEntity(bloodGroupId);
    }

    default Long bloodGroupEntityToBloodGroupId(BloodGroupEntity bloodGroupEntity) {
        if (bloodGroupEntity == null) {
            return null;
        }
        return bloodGroupEntity.getId();
    }

    default BloodStorageEntity bloodStorageIdToBloodStorageEntity(Long bloodStorageId) {
        if (bloodStorageId == null) {
            return null;
        }
        return new BloodStorageEntity(bloodStorageId);
    }

    default Long bloodStorageEntityIdToBloodStorageId(BloodStorageEntity bloodStorageEntity) {
        if (bloodStorageEntity == null) {
            return null;
        }
        return bloodStorageEntity.getId();
    }
}
