package dev.patika.schoolmanagementsystem.entities;

import dev.patika.schoolmanagementsystem.core.entities.EntityUtility;
import org.springframework.util.Assert;

public class CourseUtility extends EntityUtility<Course> {

    CourseUtility(Course clazz) {
        super(clazz);
    }

    //region students

    /**
     * Adds the given student to this course.
     *
     * @param student element to be added to this course.
     * @throws IllegalArgumentException if student is {@literal null}.
     */
    public void addStudent(Student student) {
        Assert.notNull(student, "student must not be null");
        clazz.getStudents().add(student);
        student.getCourses().add(clazz);
    }

    /**
     * Removes the given student from this course.
     *
     * @param student element to be removed from this course.
     * @throws IllegalArgumentException if student is {@literal null}.
     */
    public void removeStudent(Student student) {
        Assert.notNull(student, "student must not be null");
        clazz.getStudents().remove(student);
        student.getCourses().remove(clazz);
    }

    /**
     * Removes all of the students from this course.
     */
    public void clearStudents() {
        for (Student student : clazz.getStudents())
            student.getCourses().remove(clazz);
        clazz.getStudents().clear();
    }
    //endregion
}
