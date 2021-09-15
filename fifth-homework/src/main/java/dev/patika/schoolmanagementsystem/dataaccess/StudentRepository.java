package dev.patika.schoolmanagementsystem.dataaccess;

import dev.patika.schoolmanagementsystem.dataaccess.dtos.StudentGroupByGenderResponse;
import dev.patika.schoolmanagementsystem.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {

    List<Student> findAllByCourses_Id(Long courseId);

    @Query("select s.gender as gender, count(s) as count from Student s group by s.gender")
    List<StudentGroupByGenderResponse> countGender();

    void deleteAllByName(String name);
}
