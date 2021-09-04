package dev.patika.schoolmanagementsystem.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id", nullable = false)
    private Long id;

    @Column(name = "code", unique = true, nullable = false)
    private String code;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "credit_score", nullable = false, columnDefinition = "TINYINT")
    private int creditScore;

    @JsonIgnoreProperties({"courses"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @Setter(AccessLevel.NONE)
    @JsonIgnoreProperties({"courses"})
    @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
    private Set<Student> students = new HashSet<>();

    //region equals & hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;

        Course course = (Course) o;

        return Objects.equals(code, course.code);
    }

    @Override
    public int hashCode() {
        return code != null ? code.hashCode() : 0;
    }
    //endregion

    //region utility methods

    /**
     * Adds the specified student to this course.
     *
     * @param student element to be added to this course.
     */
    public void addStudent(Student student) {
        students.add(student);
        student.getCourses().add(this);
    }

    /**
     * Removes the specified student from this course.
     *
     * @param student element to be removed from this course.
     */
    public void removeStudent(Student student) {
        students.remove(student);
        student.getCourses().remove(this);
    }

    /**
     * Removes all of the students from this course.
     */
    public void clearStudents() {
        for (Student student : students)
            student.getCourses().remove(this);
        students.clear();
    }
    //endregion
}
