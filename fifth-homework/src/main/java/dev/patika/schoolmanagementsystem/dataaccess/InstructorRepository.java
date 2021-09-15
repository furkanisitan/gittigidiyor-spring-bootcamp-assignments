package dev.patika.schoolmanagementsystem.dataaccess;

import dev.patika.schoolmanagementsystem.entities.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstructorRepository<T extends Instructor> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {

    List<T> findAllByName(String name);

    T getByPhoneNumber(String phneNumber);

    boolean existsByPhoneNumber(String phoneNumber);

}
