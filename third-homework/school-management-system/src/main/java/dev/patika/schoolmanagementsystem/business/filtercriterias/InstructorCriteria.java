package dev.patika.schoolmanagementsystem.business.filtercriterias;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

@Getter
@Setter
@NoArgsConstructor
public class InstructorCriteria {

    private String name;
    private String sort;
    private int limit;


    /**
     * Returns a PageRequest object if the limit is greater than 0.
     *
     * @return a PageRequest object if the limit is greater than 0.
     */
    public PageRequest generatePageRequest() {
        return limit > 0 ? PageRequest.ofSize(limit) : null;
    }

    /**
     * Returns a Sort object if the sort value is valid.
     *
     * @return a Sort object if the sort value is valid.
     */
    public Sort generateSort() {
        if (StringUtils.hasText(sort)) {
            switch (sort) {
                case "fixedSalary":
                case "fixedSalary.asc":
                    return Sort.by(Sort.Order.asc("fixedSalary").nullsLast());
                case "fixedSalary.desc":
                    return Sort.by(Sort.Order.desc("fixedSalary").nullsLast());
                case "hourlySalary":
                case "hourlySalary.asc":
                    return Sort.by(Sort.Order.asc("hourlySalary").nullsLast());
                case "hourlySalary.desc":
                    return Sort.by(Sort.Order.desc("hourlySalary").nullsLast());
            }
        }
        return null;
    }
}
