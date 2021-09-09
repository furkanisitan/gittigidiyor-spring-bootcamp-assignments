package dev.patika.schoolmanagementsystem.entities;

import dev.patika.schoolmanagementsystem.core.entities.EntityUtility;
import org.springframework.util.Assert;

public class StudentUtility extends EntityUtility<Student> {

    StudentUtility(Student clazz) {
        super(clazz);
    }

    /**
     * Adds the given course to this student.
     *
     * @param course element to be added to this student.
     * @throws IllegalArgumentException if course is {@literal null}.
     */
    public void addCourse(Course course) {
        Assert.notNull(course, "course must not be null");
        clazz.getCourses().add(course);
        course.getStudents().add(clazz);
    }

    /**
     * Removes the given course from this student.
     *
     * @param course element to be removed from this student.
     * @throws IllegalArgumentException if course is {@literal null}.
     */
    public void removeCourse(Course course) {
        Assert.notNull(course, "course must not be null");
        clazz.getCourses().remove(course);
        course.getStudents().remove(clazz);
    }

    /**
     * Removes all of the courses from this student.
     */
    public void clearCourses() {
        for (Course course : clazz.getCourses())
            course.getStudents().remove(clazz);
        clazz.getCourses().clear();
    }
}
