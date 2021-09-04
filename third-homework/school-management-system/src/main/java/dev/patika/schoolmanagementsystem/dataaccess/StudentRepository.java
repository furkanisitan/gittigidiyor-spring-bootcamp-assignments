package dev.patika.schoolmanagementsystem.dataaccess;

import dev.patika.schoolmanagementsystem.entities.concretes.Student;
import dev.patika.schoolmanagementsystem.entities.dtos.student.GenderCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findAllByNameContainsIgnoreCase(String name);

    @Query("select s.gender as gender, count(s) as count from Student s group by s.gender")
    List<GenderCount> countTotalStudentsByGender();

    void deleteAllByName(String name);
}
