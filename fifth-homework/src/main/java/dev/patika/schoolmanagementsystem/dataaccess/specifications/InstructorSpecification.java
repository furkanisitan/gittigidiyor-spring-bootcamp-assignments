package dev.patika.schoolmanagementsystem.dataaccess.specifications;

import dev.patika.schoolmanagementsystem.core.specifications.BaseSpecification;
import dev.patika.schoolmanagementsystem.core.specifications.criteria.FilterCriteria;
import dev.patika.schoolmanagementsystem.entities.Instructor;

public class InstructorSpecification extends BaseSpecification<Instructor> {

    public InstructorSpecification(FilterCriteria criteria) {
        super(criteria);
    }
}
