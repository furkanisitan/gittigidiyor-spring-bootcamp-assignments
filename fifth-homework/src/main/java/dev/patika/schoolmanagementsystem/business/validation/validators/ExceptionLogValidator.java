package dev.patika.schoolmanagementsystem.business.validation.validators;

import dev.patika.schoolmanagementsystem.core.specifications.criteria.OperationType;
import dev.patika.schoolmanagementsystem.entities.ExceptionLog_;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExceptionLogValidator {

    public static final Map<String, List<OperationType>> filterCriteriaPermissions =
            new HashMap<String, List<OperationType>>() {{
                put(ExceptionLog_.EXCEPTION_TYPE, Collections.singletonList(OperationType.EQUAL));
                put(ExceptionLog_.DATE, Collections.singletonList(OperationType.EQUAL));
            }};
}
