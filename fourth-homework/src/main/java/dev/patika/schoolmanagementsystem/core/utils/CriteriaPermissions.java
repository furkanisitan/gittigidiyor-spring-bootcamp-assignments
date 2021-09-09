package dev.patika.schoolmanagementsystem.core.utils;

import dev.patika.schoolmanagementsystem.core.exceptions.NotAllowedFilterCriteriaException;
import dev.patika.schoolmanagementsystem.core.specifications.criteria.FilterCriteria;
import dev.patika.schoolmanagementsystem.core.specifications.criteria.OperationType;

import java.util.List;
import java.util.Map;

public class CriteriaPermissions {

    /**
     * This method checks for the following two cases.
     * 1. Are {getField()} and {getOperationType()} in the {criteria} parameter valid for entity?
     * 2. Is a query of type {OperationType()} allowed for {Field()}?
     *
     * @param criteria    a {@link FilterCriteria} object containing query parameters.
     * @param permissions it holds which operation types are allowed on which field.
     * @throws NotAllowedFilterCriteriaException if criteria is invalid.
     */
    public static void checkFilterCriteria(FilterCriteria criteria, Map<String, List<OperationType>> permissions) {
        List<OperationType> operationTypes = permissions.get(criteria.getField());

        if (operationTypes == null)
            throw new NotAllowedFilterCriteriaException(criteria.getField());

        if (!operationTypes.contains(criteria.getOperationType()))
            throw new NotAllowedFilterCriteriaException(criteria.getField(), criteria.getOperationType());
    }

}
