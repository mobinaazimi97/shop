package com.mftplus.shop.mapper;

import com.mftplus.shop.enums.Gender;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class DataMapper {
    @Named("toStringDate")
    public String toStringDate(LocalDate localDate) {
        return localDate != null ? localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : null;
    }

    @Named("toLocalDate")
    public LocalDate toLocalDate(String date) {
        return date != null ? LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd")) : null;
    }

    @Named("genderToString")
    public String genderToString(Gender gender) {
        return gender != null ? gender.toString() : null;
    }

    @Named("stringToGender")
    public Gender stringToGender(String gender) {
        return gender != null ? Gender.valueOf(gender) : null;
    }
}
