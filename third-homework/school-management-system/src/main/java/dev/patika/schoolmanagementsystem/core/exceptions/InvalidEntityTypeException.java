package dev.patika.schoolmanagementsystem.core.exceptions;

public class InvalidEntityTypeException extends RuntimeException {

    private final String type;

    /**
     * @param type the name of the valid type.
     */
    public InvalidEntityTypeException(String type) {
        super(String.format("The type of the submitted model is not of type '%s'.", type));
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
