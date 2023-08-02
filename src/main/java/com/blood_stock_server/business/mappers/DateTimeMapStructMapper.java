package com.blood_stock_server.business.mappers;

import org.mapstruct.Mapper;

import java.time.LocalDate;

@Mapper(componentModel = "spring")
public interface DateTimeMapStructMapper {

    default LocalDate endDateStringToEndDateLocalDate(String dateString) {
        if (dateString.isEmpty()) {
            return null;
        } return LocalDate.parse(dateString);
    }

    default String dateDateTimeToEndDateString(LocalDate dateLocalDate) {
        if (dateLocalDate == null) {
            return "";
        } return String.valueOf(dateLocalDate);
    }
}