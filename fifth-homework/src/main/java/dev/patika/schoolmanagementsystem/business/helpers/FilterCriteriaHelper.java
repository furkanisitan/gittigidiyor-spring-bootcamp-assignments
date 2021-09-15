package dev.patika.schoolmanagementsystem.business.helpers;

import dev.patika.schoolmanagementsystem.core.specifications.criteria.FilterCriteria;

import java.util.List;

public class FilterCriteriaHelper {

    private final static String regex = "(\\w+)<(\\w+)>([^,]*)($|,)";

    /**
     * Processes {@code filter} parameter according to {@link #regex} and converts it to {@link FilterCriteria} list.
     *
     * @param filter a text containing filter parameters.
     * @return a list of {@link FilterCriteria}.
     */
    public static List<FilterCriteria> from(String filter) {
        return FilterCriteria.valueOf(filter, regex);
    }
}
