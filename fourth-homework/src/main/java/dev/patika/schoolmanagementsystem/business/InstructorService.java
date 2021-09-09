package dev.patika.schoolmanagementsystem.business;

import dev.patika.schoolmanagementsystem.business.criteria.InstructorCriteria;
import dev.patika.schoolmanagementsystem.business.dtos.InstructorCreateDto;
import dev.patika.schoolmanagementsystem.business.dtos.InstructorDto;
import dev.patika.schoolmanagementsystem.business.dtos.InstructorUpdateDto;
import dev.patika.schoolmanagementsystem.core.exceptions.EntityNotExistsException;
import dev.patika.schoolmanagementsystem.entities.Instructor;

import java.util.List;

public interface InstructorService {

    /**
     * Returns all instructors as {@link List<InstructorDto>} by {@literal filter} and {@literal criteria}.
     *
     * @param filter a text containing filter parameters.
     * @return a {@link List<InstructorDto>} by {@literal filter} and {@literal criteria}.
     */
    List<InstructorDto> findAll(String filter, InstructorCriteria criteria);


    /**
     * Returns a instructor as {@link InstructorDto} by {@literal id}.
     *
     * @param id the primary key of the entity.
     * @return a {@link InstructorDto} by {@literal id}.
     */
    InstructorDto findById(Long id);

    /**
     * @param id the primary key of the entity.
     * @return proxy object of Instructor by {@literal id}.
     */
    Instructor getById(Long id);

    /**
     * Creates a new instructor.
     *
     * @param instructorCreateDto the dto object required to create a new instructor.
     * @return the added instructor as {@link InstructorDto}.
     */
    InstructorDto create(InstructorCreateDto instructorCreateDto);

    /**
     * Updates the instructor.
     *
     * @param instructorUpdateDto the dto object required to update the instructor.
     * @throws EntityNotExistsException if entity is not exists.
     */
    void update(InstructorUpdateDto instructorUpdateDto);

    /**
     * Deletes instructor by {@literal id}.
     *
     * @param id the primary key of the entity.
     * @throws EntityNotExistsException if entity is not exists by {@literal id}.
     */
    void deleteById(Long id);

    /**
     * Deletes all instructors by {@literal name}.
     *
     * @param name instructor name to delete.
     */
    void deleteAllByName(String name);

    /**
     * @param id the primary key of the entity.
     * @return true if instructor exists by {@literal id}, otherwise false.
     */
    boolean existsById(Long id);
}
