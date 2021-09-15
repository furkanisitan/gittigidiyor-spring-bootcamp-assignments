package dev.patika.schoolmanagementsystem.core.specifications;

import dev.patika.schoolmanagementsystem.core.specifications.criteria.FilterCriteria;
import dev.patika.schoolmanagementsystem.core.specifications.exceptions.InvalidFilterCriteriaException;
import dev.patika.schoolmanagementsystem.core.specifications.exceptions.InvalidFilterException;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * An abstract base class that implements the {@link Specification}.
 *
 * @param <T> the type of the Root the resulting Specification operates on.
 */
public abstract class BaseSpecification<T> implements Specification<T> {

    private final FilterCriteria criteria;

    public BaseSpecification(FilterCriteria criteria) {
        this.criteria = criteria;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

        Class fieldType;

        // Check if the field is valid.
        try {
            fieldType = root.get(criteria.getField()).getJavaType();
        } catch (IllegalArgumentException e) {
            throw new InvalidFilterCriteriaException(criteria.getField());
        }

        try {
            Comparable value = parseByType(fieldType, criteria.getValue());

            switch (criteria.getOperationType()) {

                case EQUAL:
                    return criteriaBuilder.equal(root.get(criteria.getField()), value);
                case NOT_EQUAL:
                    return criteriaBuilder.notEqual(root.get(criteria.getField()), value);
                case STARTS_WITH:
                    return criteriaBuilder.like(root.get(criteria.getField()), value + "%");
                case ENDS_WITH:
                    return criteriaBuilder.like(root.get(criteria.getField()), "%" + value);
                case CONTAINS:
                    return criteriaBuilder.like(root.get(criteria.getField()), "%" + value + "%");
                case GREATER_THAN:
                    return criteriaBuilder.greaterThan(root.get(criteria.getField()), value);
                case LESS_THAN:
                    return criteriaBuilder.lessThan(root.get(criteria.getField()), value);
                case GREATER_THAN_OR_EQUAL:
                    return criteriaBuilder.greaterThanOrEqualTo(root.get(criteria.getField()), value);
                case LESS_THAN_OR_EQUAL:
                    return criteriaBuilder.lessThanOrEqualTo(root.get(criteria.getField()), value);
                default:
                    throw new InvalidFilterCriteriaException(criteria.getOperationType());
            }
        }
        // IllegalArgumentException => enum or number exception
        catch (IllegalArgumentException | DateTimeParseException e) {
            throw new InvalidFilterCriteriaException(criteria.getField(), criteria.getValue());
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private Comparable parseByType(Class type, String value) {

        if (type.isEnum()) return Enum.valueOf(type, value);
        if (type.isAssignableFrom(Instant.class)) return Instant.parse(value);
        if (type.isAssignableFrom(LocalDate.class)) return LocalDate.parse(value);
        if (type.isAssignableFrom(LocalDateTime.class)) return LocalDateTime.parse(value);
        if (type.isAssignableFrom(LocalTime.class)) return LocalTime.parse(value);
        return value;
    }
}
