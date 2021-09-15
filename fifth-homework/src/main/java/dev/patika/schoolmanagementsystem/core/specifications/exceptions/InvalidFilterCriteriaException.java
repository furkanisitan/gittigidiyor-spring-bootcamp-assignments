package dev.patika.schoolmanagementsystem.core.specifications.exceptions;

import dev.patika.schoolmanagementsystem.core.specifications.BaseSpecification;
import dev.patika.schoolmanagementsystem.core.specifications.criteria.FilterCriteria;
import dev.patika.schoolmanagementsystem.core.specifications.criteria.OperationType;
import lombok.Getter;

/**
 * Thrown to indicate that the {@link FilterCriteria} parameter passed to the
 * {@link BaseSpecification} constructor is invalid.
 */

@Getter
public class InvalidFilterCriteriaException extends InvalidFilterException {

    private String field;
    private String value;
    private OperationType operationType;

    public InvalidFilterCriteriaException(String field) {
        super(String.format("The '%s' field is invalid.", field));
        this.field = field;
    }

    public InvalidFilterCriteriaException(OperationType operationType) {
        super(String.format("The '%s' operator is an unsupported operation type.", operationType.getShortcut()));
        this.operationType = operationType;
    }

    public InvalidFilterCriteriaException(String field, String value) {
        super(String.format("The value '%s' for the '%s' field is invalid.", value, field));
        this.field = field;
        this.value = value;
    }
}
