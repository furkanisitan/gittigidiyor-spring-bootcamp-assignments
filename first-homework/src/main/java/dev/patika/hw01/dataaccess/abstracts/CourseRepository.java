package dev.patika.hw01.dataaccess.abstracts;

import dev.patika.hw01.entities.concretes.Course;

public interface CourseRepository extends CrudRepository<Course, Long> {

    Course findByCode(String code);

    Course findByCodeFetchStudents(String code);
}
