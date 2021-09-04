package dev.patika.schoolmanagementsystem.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
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
@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION, defaultImpl = Instructor.class)
@JsonSubTypes({@JsonSubTypes.Type(PermanentInstructor.class), @JsonSubTypes.Type(VisitingResearcher.class)})
@Entity
@Table(name = "instructors")
@Inheritance(strategy = InheritanceType.JOINED)
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "instructor_id", nullable = false)
    private Long id;

    @Column(name = "phone_number", unique = true, nullable = false, length = 15)
    private String phoneNumber;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "address", nullable = false, length = 100)
    private String address;

    @Setter(AccessLevel.NONE)
    @JsonIgnoreProperties({"instructor", "students"})
    @OneToMany(mappedBy = "instructor", fetch = FetchType.LAZY)
    private Set<Course> courses = new HashSet<>();

    //region equals & hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Instructor)) return false;

        Instructor that = (Instructor) o;

        return Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return phoneNumber != null ? phoneNumber.hashCode() : 0;
    }
    //endregion

    //region utility methods

    /**
     * Adds the specified course to this instructor.
     *
     * @param course element to be added to this instructor.
     */
    public void addCourse(Course course) {
        courses.remove(course);
        course.setInstructor(this);
    }

    /**
     * Removes the specified course from this instructor.
     *
     * @param course element to be removed from this instructor.
     */
    public void removeCourse(Course course) {
        removeCourse(course, null);
    }

    /**
     * Removes the specified course from this instructor, assigns it to the other specified instructor.
     *
     * @param course     element to be removed from this instructor.
     * @param instructor other {@literal instructor} to be assigned to the {@literal course} to replace this instructor.
     */
    public void removeCourse(Course course, Instructor instructor) {
        courses.remove(course);
        course.setInstructor(instructor);
    }

    /**
     * Removes all of the courses from this instructor.
     */
    public void clearCourses() {
        clearCourses(null);
    }

    /**
     * Removes all courses from this instructor, assigns them to the other specified instructor.
     */
    public void clearCourses(Instructor instructor) {
        for (Course course : courses)
            course.setInstructor(instructor);
        courses.clear();
    }
    //endregion
}
