package dev.patika.schoolmanagementsystem.business.dtos;

import dev.patika.schoolmanagementsystem.entities.enums.Gender;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;

@Data
public class StudentDto {

    private Long id;
    private Instant createdDate;
    private Instant lastModifiedDate;
    private String name;
    private String address;
    private LocalDate birthDate;
    private Gender gender;
}
