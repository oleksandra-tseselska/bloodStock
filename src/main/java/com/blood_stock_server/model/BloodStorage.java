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
import java.util.ArrayList;
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
    @NotBlank(message = "Email is mandatory")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE
            , message = "Example of valid email \"name@gmail.com\"")
    private String email;
    @ApiModelProperty(name = "Blood storage's phone number")
    @NotBlank(message = "Phone number is mandatory")
    @Pattern(regexp = "\\d{8,15}", message = "Phone numbers must contain from 8 to 15 digits.")
    private String phoneNumber;
    @ApiModelProperty(name = "Blood storage's address")
    private List<Long> addressIds;
    @ApiModelProperty(name = "Stored blood in a blood storage")
    private List<Long> bloodInfoIds;
    public void add(BloodInfo bloodInfo){
        if (bloodInfoIds == null) {
            bloodInfoIds = new ArrayList<>();
        }
        bloodInfoIds.add(bloodInfo.getId());
        bloodInfo.setBloodStorageId(this.getId());
    }
}
