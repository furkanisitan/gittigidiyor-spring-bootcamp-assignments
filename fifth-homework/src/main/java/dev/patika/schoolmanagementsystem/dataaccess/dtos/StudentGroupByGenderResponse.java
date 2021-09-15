package dev.patika.schoolmanagementsystem.dataaccess.dtos;

import dev.patika.schoolmanagementsystem.entities.enums.Gender;

public interface StudentGroupByGenderResponse {

    Gender getGender();

    Long getCount();
}
