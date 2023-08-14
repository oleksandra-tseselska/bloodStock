package com.blood_stock_server.business.service;

import com.blood_stock_server.model.Address;

import java.util.List;

public interface AddressService {
    Address saveAddress(Address address);

    List<Address> findAllAddresses();
}
