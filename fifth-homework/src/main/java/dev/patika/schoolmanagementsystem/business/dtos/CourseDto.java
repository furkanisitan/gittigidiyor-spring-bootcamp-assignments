package dev.patika.schoolmanagementsystem.business.dtos;

import lombok.Data;

import java.time.Instant;

@Data
public class CourseDto {

    private Long id;
    private Instant createdDate;
    private Instant lastModifiedDate;
    private String code;
    private String name;
    private int creditScore;
    private Long instructorId;
}
