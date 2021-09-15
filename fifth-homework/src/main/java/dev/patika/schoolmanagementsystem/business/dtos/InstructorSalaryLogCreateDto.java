package dev.patika.schoolmanagementsystem.business.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class InstructorSalaryLogCreateDto {

    private Long instructorId;
    private BigDecimal previousSalary;
    private BigDecimal currentSalary;
    private double percent;

}
