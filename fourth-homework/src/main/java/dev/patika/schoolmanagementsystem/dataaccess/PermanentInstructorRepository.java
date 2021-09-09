package dev.patika.schoolmanagementsystem.dataaccess;

import dev.patika.schoolmanagementsystem.entities.PermanentInstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermanentInstructorRepository extends JpaRepository<PermanentInstructor, Long> {
}
