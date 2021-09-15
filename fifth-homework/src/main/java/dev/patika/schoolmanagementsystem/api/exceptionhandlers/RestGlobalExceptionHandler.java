package dev.patika.schoolmanagementsystem.api.exceptionhandlers;

import dev.patika.schoolmanagementsystem.business.ExceptionLogService;
import dev.patika.schoolmanagementsystem.business.dtos.ExceptionLogCreateDto;
import dev.patika.schoolmanagementsystem.core.constants.ResponseMessages;
import dev.patika.schoolmanagementsystem.core.exceptions.*;
import dev.patika.schoolmanagementsystem.core.results.Result;
import dev.patika.schoolmanagementsystem.core.specifications.exceptions.InvalidFilterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestGlobalExceptionHandler {

    private final ExceptionLogService exceptionLogService;

    @Autowired
    public RestGlobalExceptionHandler(ExceptionLogService exceptionLogService) {
        this.exceptionLogService = exceptionLogService;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotExistsException.class)
    @ResponseBody
    Result handleEntityNotExistsException(EntityNotExistsException e) {
        return Result.fail(ResponseMessages.ERR_NOT_FOUND, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({NotAllowedFilterCriteriaException.class, InvalidFilterException.class})
    @ResponseBody
    Result handleInvalidFilterException(Exception e) {
        return Result.fail(ResponseMessages.ERR_INVALID_FILTER_CRITERIA, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomValidationException.class)
    @ResponseBody
    Result handleCustomValidationException(CustomValidationException e) {
        exceptionLogService.create(new ExceptionLogCreateDto(e.getClass().getSimpleName(), e.getMessage()));
        return Result.fail(ResponseMessages.ERR_VALIDATION, e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UniqueConstraintViolationException.class)
    @ResponseBody
    Result handleUniqueConstraintViolationException(UniqueConstraintViolationException e) {

        exceptionLogService.create(new ExceptionLogCreateDto(e.getClass().getSimpleName(), e.getMessage()));
        return Result.fail(ResponseMessages.ERR_UNIQUE_CONSTRAINT, e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ForeignKeyConstraintViolationException.class)
    @ResponseBody
    Result handleForeignKeyConstraintViolationException(ForeignKeyConstraintViolationException e) {
        return Result.fail(ResponseMessages.ERR_FOREIGN_KEY_CONSTRAINT, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidEntityTypeException.class)
    @ResponseBody
    Result handleInvalidEntityTypeException(InvalidEntityTypeException e) {
        return Result.fail(ResponseMessages.ERR_INVALID_ENTITY_TYPE, e.getMessage());
    }

}
