package dev.patika.schoolmanagementsystem.dataaccess;


import dev.patika.schoolmanagementsystem.entities.concretes.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findAllByName(String name);

    List<Course> findAllByNameContainsIgnoreCase(String name);

    Course getByCode(String code);

    boolean existsByCode(String code);
}
