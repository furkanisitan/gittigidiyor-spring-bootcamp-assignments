package dev.patika.schoolmanagementsystem.business;

import dev.patika.schoolmanagementsystem.business.dtos.CourseCreateDto;
import dev.patika.schoolmanagementsystem.business.dtos.CourseDto;
import dev.patika.schoolmanagementsystem.business.dtos.CourseUpdateDto;
import dev.patika.schoolmanagementsystem.core.exceptions.EntityNotExistsException;

import java.util.List;

public interface CourseService {

    /**
     * Returns all courses as {@link List<CourseDto>}.
     *
     * @return a {@link List<CourseDto>}.
     */
    List<CourseDto> findAll();

    /**
     * Returns all courses as {@link List<CourseDto>} by {@literal filter}.
     *
     * @param filter a text containing filter parameters.
     * @return a {@link List<CourseDto>} by {@literal filter}.
     */
    List<CourseDto> findAll(String filter);

    /**
     * Returns a course as {@link CourseDto} by {@literal id}.
     *
     * @param id the primary key of the entity.
     * @return a {@link CourseDto} by {@literal id}.
     */
    CourseDto findById(Long id);

    /**
     * Creates a new course.
     *
     * @param courseCreateDto the dto object required to create a new course.
     * @return the added course as {@link CourseDto}.
     */
    CourseDto create(CourseCreateDto courseCreateDto);

    /**
     * Updates the course.
     *
     * @param courseUpdateDto the dto object required to update the course.
     * @throws EntityNotExistsException if entity is not exists.
     */
    void update(CourseUpdateDto courseUpdateDto);

    /**
     * Deletes course by {@literal id}.
     *
     * @param id the primary key of the entity.
     * @throws EntityNotExistsException if entity is not exists by {@literal id}.
     */
    void deleteById(Long id);

    /**
     * Deletes all courses by {@literal name}.
     *
     * @param name course name to delete.
     */
    void deleteAllByName(String name);

    /**
     * Adds student to course.
     *
     * @param courseId  the primary key of the course.
     * @param studentId the primary key of the student.
     */
    void addStudent(Long courseId, Long studentId);

    /**
     * @param id the primary key of the entity.
     * @return true if course exists by {@literal id}, otherwise false.
     */
    boolean existsById(Long id);
}
