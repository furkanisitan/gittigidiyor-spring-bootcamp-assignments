package dev.patika.schoolmanagementsystem.entities;

import dev.patika.schoolmanagementsystem.core.entities.Entity;
import dev.patika.schoolmanagementsystem.entities.enums.Gender;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@javax.persistence.Entity
@Table(name = "students")
@AttributeOverride(name = "id", column = @Column(name = "student_id", nullable = false))
public class Student extends Entity<Long> {

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "birth_date", columnDefinition = "DATE")
    private LocalDate birthDate;

    @Enumerated
    @Column(name = "gender", columnDefinition = "TINYINT")
    private Gender gender;

    @Setter(AccessLevel.NONE)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "student_course", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> courses = new HashSet<>();

    //region utility methods
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private transient StudentUtility utility;

    @PostLoad
    private void initUtility() {
        utility = new StudentUtility(this);
    }

    public StudentUtility utility() {
        return utility;
    }
    //endregion
}
