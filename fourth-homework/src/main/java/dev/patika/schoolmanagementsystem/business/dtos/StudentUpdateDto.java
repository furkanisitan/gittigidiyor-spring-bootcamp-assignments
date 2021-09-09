package dev.patika.schoolmanagementsystem.business.dtos;

import dev.patika.schoolmanagementsystem.entities.enums.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentUpdateDto {

    private Long id;
    private String name;
    private String address;
    private LocalDate birthDate;
    private Gender gender;
}
