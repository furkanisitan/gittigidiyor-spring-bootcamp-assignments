package dev.patika.schoolmanagementsystem.business.validation.exceptions;

import dev.patika.schoolmanagementsystem.core.exceptions.CustomValidationException;

public class StudentAgeNotValidException extends CustomValidationException {

    public StudentAgeNotValidException(String message) {
        super(message);
    }
}
