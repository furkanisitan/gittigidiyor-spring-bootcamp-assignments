package dev.patika.schoolmanagementsystem.business.dtos;

import lombok.Data;

import java.time.Instant;

@Data
public class InstructorDto {

    private Long id;
    private Instant createdDate;
    private Instant lastModifiedDate;
    private String phoneNumber;
    private String name;
    private String address;
}
