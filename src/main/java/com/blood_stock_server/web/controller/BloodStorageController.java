package com.blood_stock_server.web.controller;

import com.blood_stock_server.business.service.JwtService;
import com.blood_stock_server.business.service.impl.BloodStorageServiceImpl;
import com.blood_stock_server.model.BloodStorage;
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
import org.springframework.lang.NonNull;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Api(tags = {DescriptionVariables.BLOOD_STORAGE})
@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/blood/storage")
public class BloodStorageController {

    private final BloodStorageServiceImpl service;
    private final JwtService jwtService;

    @ApiOperation(value = "Save new blood storage",
            response = BloodStorage.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HTMLResponseMessages.HTTP_200),
            @ApiResponse(code = 400, message = HTMLResponseMessages.HTTP_400),
            @ApiResponse(code = 404, message = HTMLResponseMessages.HTTP_404),
            @ApiResponse(code = 500, message = HTMLResponseMessages.HTTP_500)
    })
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BloodStorage> saveBloodStockStorage(
            @RequestBody @Valid BloodStorage bloodStorage
    ) {
        BloodStorage bloodStorageSaved = service.saveBloodStorage(bloodStorage);
        log.info("New blood stock blood storage is created with id {}", bloodStorageSaved.getId());
        return ResponseEntity.ok(bloodStorageSaved);
    }

    @GetMapping
    @ApiOperation(value = "Finds all blood storages",
            notes = "Returns the entire list of blood storages",
            response = BloodStorage.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded", response = BloodStorage.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = "The server has not found anything matching the Request-URI"),
            @ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<List<BloodStorage>> findAllBloodStorages() {
        log.info("Retrieve list of Blood storages");
        List<BloodStorage> bloodStorages = service.findAllBloodStorages();
        log.debug("Blood storages is found. Size: {}", bloodStorages::size);
        return ResponseEntity.ok(bloodStorages);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find the blood storage by id",
            notes = "Provide an id to search specific blood storage in database",
            response = BloodStorage.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded"),
            @ApiResponse(code = 404, message = "The server has not found anything matching the Request-URI"),
            @ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<BloodStorage> findBloodStorageById(@ApiParam(value = "id of the blood storage", required = true)
                                                             @NonNull @PathVariable Long id) {
        log.info("Find Blood storage by passing id:{} ", id);
        return ResponseEntity.ok(service.findBloodStorageById(id));
    }

    @ApiOperation(value = "Update the Blood storage",
            response = BloodStorage.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HTMLResponseMessages.HTTP_200),
            @ApiResponse(code = 400, message = HTMLResponseMessages.HTTP_400),
            @ApiResponse(code = 404, message = HTMLResponseMessages.HTTP_404),
            @ApiResponse(code = 500, message = HTMLResponseMessages.HTTP_500)
    })
    @PutMapping("/{id}")
    public ResponseEntity<BloodStorage> updateBloodStorage(
            @NotNull HttpServletRequest request,
            @ApiParam(value = "id of the blood storage", required = true)
            @PathVariable @NotNull Long id,
            @Valid @RequestBody BloodStorage bloodStorage,
            BindingResult bindingResult
    ) {
        final String jwt = request.getHeader("Authorization").substring(7);
        log.info("What is that: {}", jwt);
        String userEmail = jwtService.extractUsername(jwt);
        log.info("User name: {}", userEmail);
        if (bindingResult.hasErrors()) {
            log.warn("Blood storage is not updated: error {}", bindingResult);
            return ResponseEntity.badRequest().build();
        }
        BloodStorage updateBloodStorage = service.updateBloodStorage(bloodStorage, id);
        log.info("Blood storage with id {} is updated", updateBloodStorage.getId());
        return ResponseEntity.ok(updateBloodStorage);
    }
}
