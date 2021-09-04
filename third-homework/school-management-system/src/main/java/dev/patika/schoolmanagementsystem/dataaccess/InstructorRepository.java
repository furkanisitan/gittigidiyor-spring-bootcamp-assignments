package dev.patika.schoolmanagementsystem.dataaccess;

import dev.patika.schoolmanagementsystem.entities.concretes.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {

    List<Instructor> findAllByName(String name);

    List<Instructor> findAllByNameContainsIgnoreCase(String name);

    Instructor getByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumber(String phoneNumber);
}

