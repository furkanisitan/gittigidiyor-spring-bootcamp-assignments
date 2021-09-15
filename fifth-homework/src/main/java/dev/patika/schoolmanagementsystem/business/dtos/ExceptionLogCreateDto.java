package dev.patika.schoolmanagementsystem.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionLogCreateDto {

    private String exceptionType;
    private String message;
}
