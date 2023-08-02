package com.blood_stock_server.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.stereotype.Component;

import java.util.List;

@ApiModel(description = "Model of blood group data")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class BloodGroup {
    @ApiModelProperty(name = "The unique id of the blood group",
            value = "when saving a new object, it is itself generated in the database")
    private Long id;
    @ApiModelProperty(value = "The unique name of blood group",
            required = true)
    @NotNull
    @UniqueElements
    private String group;
    @ApiModelProperty(value = "List of available blood")
    private List<Long> bloodInfoIds;
}
