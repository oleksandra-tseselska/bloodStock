package com.blood_stock_server.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class BloodInfo {
    @ApiModelProperty(name = "The unique id of the information about blood",
            value = "when saving a new object, it is itself generated in the database")
    private Long id;
    @ApiModelProperty(
            value = "Date when blood taken. Date format YYYY-MM-DD",
            required = true, example = "2000-12-31")
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate bloodTakenDate;
    @ApiModelProperty(
            value = "Date when blood expires. Date format YYYY-MM-DD",
            required = true, example = "2000-12-31")
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate expireDate;
    @ApiModelProperty(
            value = "Blood group",
            required = true)
    @NotNull(message = "Blood group id is mandatory")
    private Long bloodGroupId;
    @ApiModelProperty(
            value = "List of blood storages in which blood is stored")
    private Long bloodStorageId;
}
