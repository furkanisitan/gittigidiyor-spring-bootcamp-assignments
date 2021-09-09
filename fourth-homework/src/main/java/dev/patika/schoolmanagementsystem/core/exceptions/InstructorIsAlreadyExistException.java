package dev.patika.schoolmanagementsystem.core.exceptions;

public class InstructorIsAlreadyExistException extends UniqueConstraintViolationException {
    /**
     * @param name  the name of the unique field.
     * @param value rejected value
     */
    public InstructorIsAlreadyExistException(String name, Object value) {
        super(name, value);
    }
}
