package dev.patika.schoolmanagementsystem.entities;

import dev.patika.schoolmanagementsystem.core.entities.EntityUtility;
import org.springframework.util.Assert;

public class InstructorUtility extends EntityUtility<Instructor> {

    InstructorUtility(Instructor clazz) {
        super(clazz);
    }

    //region courses

    /**
     * Adds the given course to this instructor.
     *
     * @param course element to be added to this instructor.
     * @throws IllegalArgumentException if course is {@literal null}.
     */
    public void addCourse(Course course) {
        Assert.notNull(course, "course must not be null");
        clazz.getCourses().add(course);
        course.setInstructor(clazz);
    }

    /**
     * Removes the given course from this instructor.
     *
     * @param course element to be removed from this instructor.
     * @throws IllegalArgumentException if course is {@literal null}.
     */
    public void removeCourse(Course course) {
        removeCourse(course, null);
    }

    /**
     * Removes the given course from this instructor and assigns it to the given {@code instructor}.
     *
     * @param course     element to be removed from this instructor.
     * @param instructor another instructor to be assigned to the course.
     * @throws IllegalArgumentException if course is {@literal null}.
     */
    public void removeCourse(Course course, Instructor instructor) {
        Assert.notNull(course, "course must not be null");
        clazz.getCourses().remove(course);
        course.setInstructor(instructor);
    }

    /**
     * Removes all of the courses from this instructor.
     */
    public void clearCourses() {
        clearCourses(null);
    }

    /**
     * Removes all courses from this instructor and assigns them to the given {@code instructor}.
     */
    public void clearCourses(Instructor instructor) {
        for (Course course : clazz.getCourses())
            course.setInstructor(instructor);
        clazz.getCourses().clear();
    }
    //endregion
}
