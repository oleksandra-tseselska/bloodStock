package com.blood_stock_server.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@ApiModel(description = "Model of blood storage address data ")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Address {
    @ApiModelProperty(name = "The unique id of the blood storage address",
            value = "when saving a new object, it is itself generated in the database")
    private Long id;
    @ApiModelProperty(value = "The city in which the blood storage is located",
            required = true)
    @NotNull
    private String city;
    @ApiModelProperty(value = "The street in which the blood storage is located",
            required = true)
    @NotNull
    private String street;
    @ApiModelProperty(value = "The building number in which the blood storage is located",
            required = true)
    @NotNull
    private Integer buildingNumber;
    @ApiModelProperty(value = "The office number in which the blood storage is located")
    private Integer office;
    @ApiModelProperty(value = "The blood storage to which the address is attached")
    private Long bloodStorageId;
}
