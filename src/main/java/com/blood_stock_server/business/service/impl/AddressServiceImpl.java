package com.blood_stock_server.business.service.impl;

import com.blood_stock_server.business.exceptions.ExistInDataBaseException;
import com.blood_stock_server.business.mappers.AddressMapper;
import com.blood_stock_server.business.repository.AddressRepository;
import com.blood_stock_server.business.repository.model.AddressEntity;
import com.blood_stock_server.business.service.AddressService;
import com.blood_stock_server.model.Address;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class AddressServiceImpl implements AddressService{
    private final AddressRepository repository;
    private final AddressMapper mapper;

    @Override
    public Address saveAddress(Address address) {
        if(repository.existsByCityAndStreetAndBuildingNumber(address.getCity(), address.getStreet(), address.getBuildingNumber())){
            log.warn("This address already exist");
            throw new ExistInDataBaseException("This address already exist");
        }
        AddressEntity addressEntity = repository.save(mapper.addressToAddressEntity(address));
        log.info("New blood stock service with id {} is saved", addressEntity.getId());
        return mapper.addressEntityToAddress(addressEntity);
    }
}
