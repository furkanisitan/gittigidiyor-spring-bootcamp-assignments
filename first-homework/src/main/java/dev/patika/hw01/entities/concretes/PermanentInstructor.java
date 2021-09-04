package dev.patika.hw01.entities.concretes;

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

    public PermanentInstructor() {
    }

    public PermanentInstructor(String name, String address, String phoneNumber, BigDecimal fixedSalary) {
        super(name, address, phoneNumber);
        this.fixedSalary = fixedSalary;
    }

    public BigDecimal getFixedSalary() {
        return fixedSalary;
    }

    public void setFixedSalary(BigDecimal fixedSalary) {
        this.fixedSalary = fixedSalary;
    }
}
