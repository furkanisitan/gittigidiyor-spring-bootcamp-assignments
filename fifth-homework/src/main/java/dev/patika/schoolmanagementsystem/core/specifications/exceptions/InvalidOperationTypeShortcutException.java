package dev.patika.schoolmanagementsystem.core.specifications.exceptions;

import lombok.Getter;

@Getter
public class InvalidOperationTypeShortcutException extends InvalidFilterException {

    private final String shortcut;

    public InvalidOperationTypeShortcutException(String shortcut) {
        super(String.format("The '%s' operator is an invalid operation type.", shortcut));
        this.shortcut = shortcut;
    }
}
