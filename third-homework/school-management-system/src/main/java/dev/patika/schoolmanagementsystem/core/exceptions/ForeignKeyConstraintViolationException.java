package dev.patika.schoolmanagementsystem.core.exceptions;

public class ForeignKeyConstraintViolationException extends RuntimeException {

    private final String name;
    private final Object value;

    /**
     * @param name  the name of foreign key.
     * @param value the value of foreign key.
     */
    public ForeignKeyConstraintViolationException(String name, Object value) {
        super(String.format("A foreign key with the '%s: %s' does not exist.", name, value));
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
