package dev.patika.schoolmanagementsystem.entities.concretes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "permanent_instructors")
@PrimaryKeyJoinColumn(name = "instructor_id")
public class PermanentInstructor extends Instructor {

    @Column(name = "fixed_salary", nullable = false, precision = 8, scale = 2)
    private BigDecimal fixedSalary;
}
