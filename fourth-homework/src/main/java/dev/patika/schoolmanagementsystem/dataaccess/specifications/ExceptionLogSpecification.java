package dev.patika.schoolmanagementsystem.dataaccess.specifications;

import dev.patika.schoolmanagementsystem.core.specifications.BaseSpecification;
import dev.patika.schoolmanagementsystem.core.specifications.criteria.FilterCriteria;
import dev.patika.schoolmanagementsystem.entities.ExceptionLog;

public class ExceptionLogSpecification extends BaseSpecification<ExceptionLog> {

    public ExceptionLogSpecification(FilterCriteria criteria) {
        super(criteria);
    }
}
