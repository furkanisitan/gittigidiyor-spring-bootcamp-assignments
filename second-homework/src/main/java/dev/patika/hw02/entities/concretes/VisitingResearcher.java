package dev.patika.hw02.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "visiting_researchers")
@PrimaryKeyJoinColumn(name = "instructor_id")
public class VisitingResearcher extends Instructor {

    @Column(name = "hourly_salary", nullable = false, precision = 6, scale = 2)
    private BigDecimal hourlySalary;

    //region getters and setters
    public BigDecimal getHourlySalary() {
        return hourlySalary;
    }

    public void setHourlySalary(BigDecimal hourlySalary) {
        this.hourlySalary = hourlySalary;
    }
    //endregion
}
