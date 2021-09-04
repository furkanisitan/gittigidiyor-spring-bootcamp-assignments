package dev.patika.hw02.dataaccess.abstracts;

import dev.patika.hw02.entities.concretes.Course;

public interface CourseRepository extends Repository<Course, Long> {

    Course findByCode(String code);
}
