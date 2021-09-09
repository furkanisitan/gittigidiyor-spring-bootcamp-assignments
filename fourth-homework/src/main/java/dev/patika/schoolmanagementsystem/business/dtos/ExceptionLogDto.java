package dev.patika.schoolmanagementsystem.business.dtos;

import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
public class ExceptionLogDto {

    private Long id;
    private String exceptionType;
    private LocalDateTime date;
    private String message;
}
