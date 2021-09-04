package dev.patika.hw01.api.controllers;

import dev.patika.hw01.business.abstracts.CourseService;
import dev.patika.hw01.business.concretes.CourseManager;
import dev.patika.hw01.entities.concretes.Course;

import java.util.List;

public class CourseController {

    private final CourseService courseService;

    public CourseController() {
        this.courseService = new CourseManager();
    }

    public List<Course> getAll() {
        return courseService.getAll();
    }

    public Course getById(Long id) {
        return courseService.getById(id);
    }

    public Course getByCode(String code) {
        return courseService.getByCode(code);
    }

    public Course getByCodeWithStudents(String code) {
        return courseService.getByCodeWithStudents(code);
    }

    public void create(Course course) {
        courseService.create(course);
    }

    public void update(Course course) {
        courseService.update(course);
    }

    public void deleteById(Long id) {
        courseService.deleteById(id);
    }
}
