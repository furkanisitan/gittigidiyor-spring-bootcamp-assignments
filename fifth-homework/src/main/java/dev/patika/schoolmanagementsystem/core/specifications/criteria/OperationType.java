package dev.patika.schoolmanagementsystem.core.specifications.criteria;

import dev.patika.schoolmanagementsystem.core.specifications.exceptions.InvalidOperationTypeShortcutException;

/**
 * The query types for the {@link org.springframework.data.jpa.domain.Specification}.
 */
public enum OperationType {
    EQUAL("eq"),
    NOT_EQUAL("ne"),
    GREATER_THAN("gt"),
    LESS_THAN("lt"),
    GREATER_THAN_OR_EQUAL("ge"),
    LESS_THAN_OR_EQUAL("le"),
    STARTS_WITH("st"),
    ENDS_WITH("en"),
    CONTAINS("ct");

    private final String shortcut;

    OperationType(String shortcut) {
        this.shortcut = shortcut;
    }

    public String getShortcut() {
        return shortcut;
    }

    public static OperationType valueOfShortcut(String shortcut) {
        switch (shortcut) {
            case "eq":
                return OperationType.EQUAL;
            case "ne":
                return OperationType.NOT_EQUAL;
            case "gt":
                return OperationType.GREATER_THAN;
            case "lt":
                return OperationType.LESS_THAN;
            case "ge":
                return OperationType.GREATER_THAN_OR_EQUAL;
            case "le":
                return OperationType.LESS_THAN_OR_EQUAL;
            case "st":
                return OperationType.STARTS_WITH;
            case "en":
                return OperationType.ENDS_WITH;
            case "ct":
                return OperationType.CONTAINS;
            default:
                throw new InvalidOperationTypeShortcutException(shortcut);
        }
    }
}
