package dev.patika.hw02.core.exceptions;

public class UniqueConstraintViolationException extends RuntimeException {

    private final String name;
    private final Object value;

    /**
     * @param name  the name of the unique field.
     * @param value rejected value
     */
    public UniqueConstraintViolationException(String name, Object value) {
        super(String.format("'%s' must be unique. {rejectedValue: %s}", name, value));
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
