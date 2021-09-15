package dev.patika.schoolmanagementsystem.dataaccess;

import dev.patika.schoolmanagementsystem.entities.InstructorSalaryLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstructorSalaryLogRepository extends JpaRepository<InstructorSalaryLog, Long> {

    List<InstructorSalaryLog> findAllByInstructor_Id(Long instructorId);

}
