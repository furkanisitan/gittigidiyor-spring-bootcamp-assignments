package dev.patika.schoolmanagementsystem.business.validation.validators;

import dev.patika.schoolmanagementsystem.business.validation.rules.CourseValidationRules;
import dev.patika.schoolmanagementsystem.core.exceptions.StudentNumberForOneCourseExceededException;
import dev.patika.schoolmanagementsystem.core.specifications.criteria.OperationType;
import dev.patika.schoolmanagementsystem.entities.Course_;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseValidator {

    public static final Map<String, List<OperationType>> filterCriteriaPermissions =
            new HashMap<String, List<OperationType>>() {{
                put(Course_.NAME, Collections.singletonList(OperationType.CONTAINS));
            }};

    /**
     * Checks the number of students enrolled in the course.
     *
     * @param studentCount number of students enrolled in the course.
     * @throws StudentNumberForOneCourseExceededException If {@literal studentCount} is greater than or equal to {@link CourseValidationRules#MAX_STUDENT_COUNT}.
     */
    public static void validateStudentCount(int studentCount) {

        if (studentCount < CourseValidationRules.MAX_STUDENT_COUNT)
            return;

        throw new StudentNumberForOneCourseExceededException(String.format("The maximum number of students has been reached. (%s)", CourseValidationRules.MAX_STUDENT_COUNT));
    }

}
