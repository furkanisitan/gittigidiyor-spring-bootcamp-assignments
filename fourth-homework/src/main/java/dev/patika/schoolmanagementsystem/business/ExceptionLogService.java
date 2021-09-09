package dev.patika.schoolmanagementsystem.business;

import dev.patika.schoolmanagementsystem.business.dtos.ExceptionLogCreateDto;
import dev.patika.schoolmanagementsystem.business.dtos.ExceptionLogDto;

import java.util.List;

public interface ExceptionLogService {

    /**
     * Returns all exception logs as {@link List<ExceptionLogDto>} by {@literal filter}.
     *
     * @param filter a text containing filter parameters.
     * @return a {@link List<ExceptionLogDto>} by {@literal filter}.
     */
    List<ExceptionLogDto> findAll(String filter);

    /**
     * Creates a new exception log.
     *
     * @param exceptionLogCreateDto the dto object required to create a new exception log.
     */
    void create(ExceptionLogCreateDto exceptionLogCreateDto);
    
}
