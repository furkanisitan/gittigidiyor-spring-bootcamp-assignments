package dev.patika.schoolmanagementsystem.core.utils;

import org.springframework.data.util.Pair;

/**
 * This class contains helper methods for generating error messages.
 */
public class ApiErrors {

    /**
     * Returns a message stating that the entity not found.
     * <p>
     * Note: Example output could be like this: Entity not found for parameters {id:'1'}.
     * </p>
     *
     * @param name       the name of entity.
     * @param parameters the keys and values of the parameters used in the query.
     * @return a message stating that the entity not found.
     */
    @SafeVarargs
    public static String notFound(String name, Pair<String, Object>... parameters) {

        StringBuilder builder = new StringBuilder(name + " not found");

        if (parameters.length > 0)
            builder.append(" for parameters");

        for (Pair<String, Object> pair : parameters)
            builder.append(String.format(" {%s='%s'}", pair.getFirst(), pair.getSecond()));

        return builder.append('.').toString();
    }
}
