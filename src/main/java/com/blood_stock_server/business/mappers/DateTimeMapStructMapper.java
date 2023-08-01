package com.blood_stock_server.business.mappers;

import org.mapstruct.Mapper;

import java.time.LocalDate;

@Mapper(componentModel = "spring")
public interface DateTimeMapStructMapper {

    default LocalDate endDateStringToEndDateLocalDate(String endDateString) {
        if (endDateString.isEmpty()) {
            return null;
        } return LocalDate.parse(endDateString);
    }

    default String endDateDateTimeToEndDateString(LocalDate endDateLocalDate) {
        if (endDateLocalDate == null) {
            return "";
        } return String.valueOf(endDateLocalDate);
    }
}