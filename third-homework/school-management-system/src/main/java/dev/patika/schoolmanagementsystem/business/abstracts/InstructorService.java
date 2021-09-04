package dev.patika.schoolmanagementsystem.business.abstracts;

import dev.patika.schoolmanagementsystem.business.filtercriterias.InstructorCriteria;
import dev.patika.schoolmanagementsystem.entities.concretes.Instructor;

import java.util.List;
import java.util.Optional;

public interface InstructorService {

    List<? extends Instructor> findAll(InstructorCriteria criteria);

    Optional<Instructor> findById(Long id);

    Instructor create(Instructor instructor);

    void update(Instructor instructor);

    void deleteById(Long id);

    /**
     * Deletes all matching instructors by {@literal name}.
     *
     * @param name instructor name to delete.
     */
    void deleteByName(String name);

    boolean existsById(Long id);
}
