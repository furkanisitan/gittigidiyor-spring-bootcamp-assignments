package dev.patika.schoolmanagementsystem.business;

import dev.patika.schoolmanagementsystem.business.dtos.StudentCreateDto;
import dev.patika.schoolmanagementsystem.business.dtos.StudentDto;
import dev.patika.schoolmanagementsystem.business.dtos.StudentUpdateDto;
import dev.patika.schoolmanagementsystem.core.exceptions.EntityNotExistsException;
import dev.patika.schoolmanagementsystem.dataaccess.dtos.StudentGroupByGenderResponse;
import dev.patika.schoolmanagementsystem.entities.Student;

import java.util.List;

public interface StudentService {

    /**
     * Returns all students as {@link List<StudentDto>}.
     *
     * @return a {@link List<StudentDto>}.
     */
    List<StudentDto> findAll();

    /**
     * Returns all students as {@link List<StudentDto>} by {@literal filter}.
     *
     * @param filter a text containing filter parameters.
     * @return a {@link List<StudentDto>} by {@literal filter}.
     */
    List<StudentDto> findAll(String filter);

    /**
     * Returns all students enrolled in the course.
     *
     * @param courseId the primary key of the course.
     * @return a {@link List<StudentDto>}.
     */
    List<StudentDto> findAllByCourseId(Long courseId);

    /**
     * Returns a student as {@link StudentDto} by {@literal id}.
     *
     * @param id the primary key of the entity.
     * @return a {@link StudentDto} by {@literal id}.
     */
    StudentDto findById(Long id);

    /**
     * @param id the primary key of the entity.
     * @return proxy object of Student by {@literal id}.
     */
    Student getById(Long id);

    /**
     * Creates a new student.
     *
     * @param studentCreateDto the dto object required to create a new student.
     * @return the added student as {@link StudentDto}.
     */
    StudentDto create(StudentCreateDto studentCreateDto);

    /**
     * Updates the student.
     *
     * @param studentUpdateDto the dto object required to update the student.
     * @throws EntityNotExistsException if entity is not exists.
     */
    void update(StudentUpdateDto studentUpdateDto);

    /**
     * Deletes student by {@literal id}.
     *
     * @param id the primary key of the entity.
     * @throws EntityNotExistsException if entity is not exists by {@literal id}.
     */
    void deleteById(Long id);

    /**
     * Deletes all students by {@literal name}.
     *
     * @param name student name to delete.
     */
    void deleteAllByName(String name);

    /**
     * Returns a list of {@link StudentGroupByGenderResponse} grouped by gender.
     *
     * @return a {@link List<StudentGroupByGenderResponse>}.
     */
    List<StudentGroupByGenderResponse> countAndGroupByGender();

    /**
     * @param id the primary key of the entity.
     * @return true if student exists by {@literal id}, otherwise false.
     */
    boolean existsById(Long id);
}
