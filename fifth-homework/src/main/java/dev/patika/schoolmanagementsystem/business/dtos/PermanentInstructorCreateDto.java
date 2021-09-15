package dev.patika.schoolmanagementsystem.business.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class PermanentInstructorCreateDto extends InstructorCreateDto {
    private BigDecimal fixedSalary;
}
