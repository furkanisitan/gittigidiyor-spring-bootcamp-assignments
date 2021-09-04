package dev.patika.hw02.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.patika.hw02.entities.enums.Gender;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "birth_date", nullable = false, columnDefinition = "DATE")
    private LocalDate birthDate;

    @Enumerated
    @Column(name = "gender", columnDefinition = "TINYINT")
    private Gender gender;

    @JsonIgnoreProperties({"students", "instructor"})
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "student_course", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> courses = new HashSet<>();

    //region getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Set<Course> getCourses() {
        return courses;
    }
    //endregion

    //region equals & hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;

        Student student = (Student) o;

        return id != null && id.equals(student.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
    //endregion

    //region utility methods

    /**
     * Adds the specified course to this student.
     *
     * @param course element to be added to this student.
     */
    public void addCourse(Course course) {
        courses.add(course);
        course.getStudents().add(this);
    }

    /**
     * Removes the specified course from this student.
     *
     * @param course element to be removed from this student.
     */
    public void removeCourse(Course course) {
        courses.remove(course);
        course.getStudents().remove(this);
    }

    /**
     * Removes all of the courses from this student.
     */
    public void clearCourses() {
        for (Course course : courses)
            course.getStudents().remove(this);
        courses.clear();
    }
    //endregion
}
