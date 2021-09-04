package dev.patika.hw02.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "permanent_instructors")
@PrimaryKeyJoinColumn(name = "instructor_id")
public class PermanentInstructor extends Instructor {

    @Column(name = "fixed_salary", nullable = false, precision = 8, scale = 2)
    private BigDecimal fixedSalary;

    //region getters and setters
    public BigDecimal getFixedSalary() {
        return fixedSalary;
    }

    public void setFixedSalary(BigDecimal fixedSalary) {
        this.fixedSalary = fixedSalary;
    }
    //endregion
}
