package dev.patika.schoolmanagementsystem.dataaccess.specifications;

import dev.patika.schoolmanagementsystem.core.specifications.BaseSpecification;
import dev.patika.schoolmanagementsystem.core.specifications.criteria.FilterCriteria;
import dev.patika.schoolmanagementsystem.entities.Course;

public class CourseSpecification extends BaseSpecification<Course> {

    public CourseSpecification(FilterCriteria criteria) {
        super(criteria);
    }
}
