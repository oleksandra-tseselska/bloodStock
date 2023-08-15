package com.blood_stock_server.web.controller;

import com.blood_stock_server.business.service.impl.BloodInfoServiceImpl;
import com.blood_stock_server.model.BloodInfo;
import com.blood_stock_server.swagger.DescriptionVariables;
import com.blood_stock_server.swagger.HTMLResponseMessages;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Api(tags = {DescriptionVariables.BLOOD_INFO})
@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/blood/info")
public class BloodInfoController {
    private final BloodInfoServiceImpl service;

    @ApiOperation(value = "Save new blood info",
            response = BloodInfo.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HTMLResponseMessages.HTTP_200),
            @ApiResponse(code = 400, message = HTMLResponseMessages.HTTP_400),
            @ApiResponse(code = 404, message = HTMLResponseMessages.HTTP_404),
            @ApiResponse(code = 500, message = HTMLResponseMessages.HTTP_500)
    })
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BloodInfo> saveBloodStockStorage(
            @RequestBody @Valid BloodInfo bloodInfo
    ) {
        BloodInfo bloodInfoSaved = service.saveBloodInfo(bloodInfo);
        log.info("New blood info is created with id {}", bloodInfoSaved.getId());
        return ResponseEntity.ok(bloodInfoSaved);
    }

    @GetMapping("/{bloodGroup}")
    @ApiOperation(value = "Finds blood info by providing blood group",
            notes = "Returns the entire list of blood info",
            response = BloodInfo.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded", response = BloodInfo.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = "The server has not found anything matching the Request-URI"),
            @ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<List<BloodInfo>> findAllBloodInfoByBloodGroup(@ApiParam(value = "id of the blood group", required = true)
                                                                        @PathVariable String bloodGroup) {
        log.info("Retrieve list of Blood info by providing blood group");
        List<BloodInfo> bloodInfo = service.findAllBloodInfoByBloodGroup(bloodGroup);
        log.debug("Blood info by providing blood group is found. Size: {}", bloodInfo::size);
        return ResponseEntity.ok(bloodInfo);
    }

    @GetMapping()
    @ApiOperation(value = "Finds blood info by providing blood group",
            notes = "Returns the entire list of blood info",
            response = BloodInfo.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded", response = BloodInfo.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = "The server has not found anything matching the Request-URI"),
            @ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<List<BloodInfo>> findAllBloodInfoByBloodIdAndStorageAddress(
            @ApiParam(value = "name of the blood group") @RequestParam(required = false) Long bloodGroupId,
            @ApiParam(value = "address of the storage") @RequestParam(required = false) String bloodStorageAddress) {
        log.info("Retrieve list of Blood info by providing blood group");
        List<BloodInfo> bloodInfo = service.findAllBloodInfoByBloodIdAndStorageAddress(bloodGroupId, bloodStorageAddress);
        log.debug("Blood info by providing blood group is found. Size: {}", bloodInfo::size);
        return ResponseEntity.ok(bloodInfo);
    }
}
