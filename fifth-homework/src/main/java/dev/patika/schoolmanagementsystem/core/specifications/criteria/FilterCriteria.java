package dev.patika.schoolmanagementsystem.core.specifications.criteria;

import dev.patika.schoolmanagementsystem.core.specifications.exceptions.InvalidFilterFormatException;
import dev.patika.schoolmanagementsystem.core.specifications.exceptions.InvalidOperationTypeShortcutException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Contains the required fields for queries that will be built using the {@link Specification}.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilterCriteria {

    private String field;
    private OperationType operationType;
    private String value;

    /**
     * Processes {@code filter} parameter according to {@code regex} and converts it to {@link FilterCriteria} list.
     * Returns an empty list if {@code filter} or {@code regex} is empty,
     *
     * @param filter a text containing filter parameters.
     * @param regex  a regular expression to match to convert text to a {@link FilterCriteria} object.
     * @return a list of {@link FilterCriteria}.
     * @throws InvalidFilterFormatException          if all the text of the filter does not match the regex.
     * @throws InvalidOperationTypeShortcutException if any unsupported operation type inside the filter.
     */
    public static List<FilterCriteria> valueOf(String filter, String regex) {

        if (!StringUtils.hasLength(filter) || !StringUtils.hasLength(regex))
            return new ArrayList<>();

        Pattern one = Pattern.compile(regex, Pattern.UNICODE_CHARACTER_CLASS);
        Pattern all = Pattern.compile("(" + regex + ")+", Pattern.UNICODE_CHARACTER_CLASS);

        Matcher matcher = all.matcher(filter);

        if (matcher.matches()) {

            List<FilterCriteria> criteria = new ArrayList<>();
            matcher = one.matcher(filter);

            while (matcher.find()) {
                OperationType operationType = OperationType.valueOfShortcut(matcher.group(2));
                criteria.add(new FilterCriteria(matcher.group(1), operationType, matcher.group(3)));
            }
            return criteria;
        } else {
            throw new InvalidFilterFormatException(filter);
        }
    }
}
