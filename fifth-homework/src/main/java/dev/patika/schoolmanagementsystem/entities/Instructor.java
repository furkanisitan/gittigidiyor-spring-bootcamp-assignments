package dev.patika.schoolmanagementsystem.entities;

import dev.patika.schoolmanagementsystem.core.entities.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@javax.persistence.Entity
@Table(name = "instructors")
@Inheritance(strategy = InheritanceType.JOINED)
@AttributeOverride(name = "id", column = @Column(name = "instructor_id", nullable = false))
public class Instructor extends Entity<Long> {

    @Column(name = "phone_number", unique = true, nullable = false)
    private String phoneNumber;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "instructor", fetch = FetchType.LAZY)
    private Set<Course> courses = new HashSet<>();

    //region equals & hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        // Check for subclasses
        if (!(o instanceof Instructor)) return false;
        Instructor instructor = (Instructor) o;
        return Objects.equals(phoneNumber, instructor.phoneNumber);
    }

    @Override
    public int hashCode() {
        return phoneNumber == null ? getClass().hashCode() : phoneNumber.hashCode();
    }
    //endregion

    //region utility methods
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private transient InstructorUtility utility;

    @PostLoad
    private void initUtility() {
        utility = new InstructorUtility(this);
    }

    public InstructorUtility utility() {
        return utility;
    }
    //endregion
}
