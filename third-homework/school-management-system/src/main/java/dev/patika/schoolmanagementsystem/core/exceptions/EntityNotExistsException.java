package dev.patika.schoolmanagementsystem.core.exceptions;

import dev.patika.schoolmanagementsystem.core.helpers.ApiErrors;
import org.springframework.data.util.Pair;

public class EntityNotExistsException extends RuntimeException {

    private final String name;
    private final Pair<String, Object>[] parameters;

    /**
     * @param name       the name of entity.
     * @param parameters the names and values of the parameters used in the query.
     */
    @SafeVarargs
    public EntityNotExistsException(String name, Pair<String, Object>... parameters) {
        super(ApiErrors.entityNotFound(name, parameters));
        this.name = name;
        this.parameters = parameters;
    }

    public String getName() {
        return name;
    }

    public Pair<String, Object>[] getParameters() {
        return parameters;
    }
}
