package dev.patika.schoolmanagementsystem.core.specifications.exceptions;

/**
 * The base class of all exceptions under the {@link dev.patika.schoolmanagementsystem.core.specifications} package.
 */

public abstract class InvalidFilterException extends RuntimeException {

    InvalidFilterException(String message) {
        super(message);
    }
}
