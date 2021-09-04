package dev.patika.schoolmanagementsystem.api.exceptionhandlers;

import com.fasterxml.jackson.databind.exc.InvalidTypeIdException;
import dev.patika.schoolmanagementsystem.core.constants.ResponseMessage;
import dev.patika.schoolmanagementsystem.core.exceptions.EntityNotExistsException;
import dev.patika.schoolmanagementsystem.core.exceptions.ForeignKeyConstraintViolationException;
import dev.patika.schoolmanagementsystem.core.exceptions.InvalidEntityTypeException;
import dev.patika.schoolmanagementsystem.core.exceptions.UniqueConstraintViolationException;
import dev.patika.schoolmanagementsystem.core.results.abstracts.Result;
import dev.patika.schoolmanagementsystem.core.results.helpers.ResultHelper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UniqueConstraintViolationException.class)
    @ResponseBody
    Result handleUniqueConstraintViolationException(UniqueConstraintViolationException e) {
        return ResultHelper.fail(ResponseMessage.UNIQUE_CONSTRAINT, e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ForeignKeyConstraintViolationException.class)
    @ResponseBody
    Result handleForeignKeyConstraintViolationException(ForeignKeyConstraintViolationException e) {
        return ResultHelper.fail(ResponseMessage.FOREIGN_KEY_CONSTRAINT, e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotExistsException.class)
    @ResponseBody
    Result handleEntityNotExistsException(EntityNotExistsException e) {
        return ResultHelper.fail(ResponseMessage.NOT_FOUND, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidEntityTypeException.class)
    @ResponseBody
    Result handleInvalidEntityTypeException(InvalidEntityTypeException e) {
        return ResultHelper.fail(ResponseMessage.INVALID_ENTITY_TYPE, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidTypeIdException.class)
    @ResponseBody
    Result handleInvalidEntityTypeException(InvalidTypeIdException e) {
        return ResultHelper.fail(ResponseMessage.INVALID_ENTITY_TYPE, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseBody
    Result handleHttpClientErrorException(HttpClientErrorException e) {
        return ResultHelper.fail("HttpClientErrorException", e.getMessage());
    }
}
