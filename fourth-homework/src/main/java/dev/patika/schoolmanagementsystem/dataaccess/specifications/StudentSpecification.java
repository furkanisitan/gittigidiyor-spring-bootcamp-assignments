package dev.patika.schoolmanagementsystem.dataaccess.specifications;

import dev.patika.schoolmanagementsystem.core.specifications.BaseSpecification;
import dev.patika.schoolmanagementsystem.core.specifications.criteria.FilterCriteria;
import dev.patika.schoolmanagementsystem.entities.Student;

public class StudentSpecification extends BaseSpecification<Student> {

    public StudentSpecification(FilterCriteria criteria) {
        super(criteria);
    }
}
