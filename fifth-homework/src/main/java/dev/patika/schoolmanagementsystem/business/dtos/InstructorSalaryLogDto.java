package dev.patika.schoolmanagementsystem.business.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class InstructorSalaryLogDto {

    private Long id;
    private Long instructorId;
    private String clientIpAddress;
    private String clientUrl;
    private String sessionActivityId;
    private LocalDateTime date;
    private BigDecimal previousSalary;
    private BigDecimal currentSalary;
    private double percent;
}
