package dev.patika.hw01.business.abstracts;

import dev.patika.hw01.entities.concretes.Course;

import java.util.List;

public interface CourseService {

    List<Course> getAll();

    Course getById(Long id);

    Course getByCode(String code);

    Course getByCodeWithStudents(String code);

    void create(Course course);

    void update(Course course);

    void delete(Course course);

    void deleteById(Long id);
}
