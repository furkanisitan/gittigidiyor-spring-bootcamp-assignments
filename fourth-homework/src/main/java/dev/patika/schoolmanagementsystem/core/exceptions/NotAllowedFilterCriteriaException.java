package dev.patika.schoolmanagementsystem.core.exceptions;

import dev.patika.schoolmanagementsystem.core.specifications.criteria.FilterCriteria;
import dev.patika.schoolmanagementsystem.core.specifications.criteria.OperationType;
import dev.patika.schoolmanagementsystem.core.utils.CriteriaPermissions;

import java.util.Map;

/**
 * Thrown to indicate that {@link FilterCriteria} parameter given to the
 * {@link CriteriaPermissions#checkFilterCriteria(FilterCriteria, Map)} method has not passed the relevant checks.
 */
public class NotAllowedFilterCriteriaException extends RuntimeException {

    public NotAllowedFilterCriteriaException(String field) {
        super(String.format("The '%s' field is invalid or not supported for querying.", field));
    }

    public NotAllowedFilterCriteriaException(String field, OperationType operationType) {
        super(String.format("The '%s' operator is invalid or not supported for '%s' field.", operationType.getShortcut(), field));
    }
}
