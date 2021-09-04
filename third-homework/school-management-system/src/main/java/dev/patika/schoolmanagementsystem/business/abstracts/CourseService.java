package dev.patika.schoolmanagementsystem.business.abstracts;

import dev.patika.schoolmanagementsystem.entities.concretes.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    List<Course> findAll();

    /**
     * Filters those containing the name. If {@literal name} is empty, returns all courses.
     *
     * @param name course name to filter.
     * @return filtered list of courses.
     */
    List<Course> findAllByNameContains(String name);

    Optional<Course> findById(Long id);

    Course create(Course course);

    void update(Course course);

    void deleteById(Long id);

    /**
     * Deletes all matching courses by {@literal name}.
     *
     * @param name course name to delete.
     */
    void deleteByName(String name);
}
