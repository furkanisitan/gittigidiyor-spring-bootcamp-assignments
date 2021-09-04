package dev.patika.hw01.entities.concretes;

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

    public VisitingResearcher() {
    }

    public VisitingResearcher(String name, String address, String phoneNumber, BigDecimal hourlySalary) {
        super(name, address, phoneNumber);
        this.hourlySalary = hourlySalary;
    }

    public BigDecimal getHourlySalary() {
        return hourlySalary;
    }

    public void setHourlySalary(BigDecimal hourlySalary) {
        this.hourlySalary = hourlySalary;
    }
}
