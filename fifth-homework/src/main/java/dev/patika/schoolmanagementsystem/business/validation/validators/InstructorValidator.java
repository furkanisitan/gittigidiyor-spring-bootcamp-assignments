package dev.patika.schoolmanagementsystem.business.validation.validators;

import dev.patika.schoolmanagementsystem.core.specifications.criteria.OperationType;
import dev.patika.schoolmanagementsystem.entities.Instructor_;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InstructorValidator {

    public static final Map<String, List<OperationType>> filterCriteriaPermissions =
            new HashMap<String, List<OperationType>>() {{
                put(Instructor_.NAME, Collections.singletonList(OperationType.CONTAINS));
            }};

}
