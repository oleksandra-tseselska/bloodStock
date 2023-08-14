package com.blood_stock_server.web.controller;

import com.blood_stock_server.business.service.impl.AddressServiceImpl;
import com.blood_stock_server.model.Address;
import com.blood_stock_server.swagger.DescriptionVariables;
import com.blood_stock_server.swagger.HTMLResponseMessages;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Api(tags = {DescriptionVariables.BLOOD_STORAGE_ADDRESS})
@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/blood/storage/address")
public class AddressController {

    private final AddressServiceImpl service;

    @GetMapping
    @ApiOperation(value = "Finds all addresses",
            notes = "Returns the entire list of addresses",
            response = Address.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded", response = Address.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = "The server has not found anything matching the Request-URI"),
            @ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<List<Address>> findAllBloodGroups() {
        log.info("Retrieve list of Addresses");
        List<Address> addresses = service.findAllAddresses();
        log.debug("Addresses is found. Size: {}", addresses::size);
        return ResponseEntity.ok(addresses);
    }

    @ApiOperation(value = "Save new blood storage address",
            response = Address.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HTMLResponseMessages.HTTP_200),
            @ApiResponse(code = 400, message = HTMLResponseMessages.HTTP_400),
            @ApiResponse(code = 404, message = HTMLResponseMessages.HTTP_404),
            @ApiResponse(code = 500, message = HTMLResponseMessages.HTTP_500)
    })
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Address> saveBloodStorageAddress(
            @RequestBody @Valid Address address
    ) {
        Address addressSaved = service.saveAddress(address);
        log.info("New blood storage address is created with id {}", addressSaved.getId());
        return ResponseEntity.ok(addressSaved);
    }
}
