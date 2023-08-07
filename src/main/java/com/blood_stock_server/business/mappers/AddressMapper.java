package com.blood_stock_server.business.mappers;

import com.blood_stock_server.business.repository.model.AddressEntity;
import com.blood_stock_server.business.repository.model.BloodStorageEntity;
import com.blood_stock_server.model.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {
        BloodStorageMapper.class
})
public interface AddressMapper {
    AddressEntity addressToAddressEntity(Address address);
    Address addressEntityToAddress(AddressEntity addressEntity);

    default BloodStorageEntity bloodStorageIdToBloodStorageEntity(Long bloodStorageId){
        if(bloodStorageId == null){
            return null;
        }
        return new BloodStorageEntity(bloodStorageId);
    }
    default Long bloodStorageEntityToBloodStorageId(BloodStorageEntity bloodStorageEntity){
        if (bloodStorageEntity == null){
            return null;
        }
        return bloodStorageEntity.getId();
    }
}
