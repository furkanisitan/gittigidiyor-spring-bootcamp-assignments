package dev.patika.schoolmanagementsystem.business.validation.validators;

import dev.patika.schoolmanagementsystem.core.exceptions.NotAllowedFilterCriteriaException;
import dev.patika.schoolmanagementsystem.core.specifications.criteria.FilterCriteria;
import dev.patika.schoolmanagementsystem.core.specifications.criteria.OperationType;
import dev.patika.schoolmanagementsystem.core.utils.CriteriaPermissions;

import java.util.List;
import java.util.Map;

public class FilterCriteriaValidator {

    /**
     * Checks whether querying is allowed based on parameters in the {@code criteria} object.
     *
     * @param criteria    a {@link FilterCriteria} object containing query parameters.
     * @param permissions it holds which operation types are allowed on which field.
     * @return {@code criteria} parameter.
     * @throws NotAllowedFilterCriteriaException if criteria is invalid.
     */
    public static FilterCriteria validateFilterCriteria(FilterCriteria criteria, Map<String, List<OperationType>> permissions) {
        CriteriaPermissions.checkFilterCriteria(criteria, permissions);
        return criteria;
    }
}
