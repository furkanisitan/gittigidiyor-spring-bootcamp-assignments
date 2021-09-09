package dev.patika.schoolmanagementsystem.business.dtos;

import lombok.Data;

@Data
public class CourseCreateDto {

    private String code;
    private String name;
    private int creditScore;
    private Long instructorId;
}
