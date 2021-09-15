package dev.patika.schoolmanagementsystem.entities;

import dev.patika.schoolmanagementsystem.core.entities.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@javax.persistence.Entity
@Table(name = "courses")
@AttributeOverride(name = "id", column = @Column(name = "course_id", nullable = false))
public class Course extends Entity<Long> {

    @Column(name = "code", unique = true, nullable = false)
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "credit_score", columnDefinition = "TINYINT")
    private int creditScore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @Setter(AccessLevel.NONE)
    @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
    private Set<Student> students = new HashSet<>();

    //region equals & hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(code, course.code);
    }

    @Override
    public int hashCode() {
        return code == null ? getClass().hashCode() : code.hashCode();
    }
    //endregion

    //region utility methods
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private transient CourseUtility utility;

    @PostLoad
    private void initUtility() {
        utility = new CourseUtility(this);
    }

    public CourseUtility utility() {
        return utility;
    }
    //endregion
}
