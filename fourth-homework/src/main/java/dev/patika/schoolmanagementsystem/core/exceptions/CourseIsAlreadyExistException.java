package dev.patika.schoolmanagementsystem.core.exceptions;

public class CourseIsAlreadyExistException extends UniqueConstraintViolationException {

    /**
     * @param name  the name of the unique field.
     * @param value rejected value
     */
    public CourseIsAlreadyExistException(String name, Object value) {
        super(name, value);
    }
}
