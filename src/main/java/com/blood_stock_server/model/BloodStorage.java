package com.blood_stock_server.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@ApiModel(description = "Model of blood storage data ")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class BloodStorage {
    @ApiModelProperty(name = "The unique id of the blood storage",
            value = "when saving a new object, it is itself generated in the database")
    private Long id;
    @ApiModelProperty(name = "Blood storage's email")
    @NotBlank
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String email;
    @ApiModelProperty(name = "Blood storage's phone number")
    @Pattern(regexp = "\\d{8,15}")
    private String phoneNumber;
    @ApiModelProperty(name = "Blood storage's address")
    private List<Long> addressIds;
    @ApiModelProperty(name = "Stored blood in a blood storage")
    private List<Long> bloodInfoIds;
}
