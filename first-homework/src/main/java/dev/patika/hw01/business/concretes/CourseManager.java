package dev.patika.hw01.business.concretes;

import dev.patika.hw01.business.abstracts.CourseService;
import dev.patika.hw01.dataaccess.abstracts.CourseRepository;
import dev.patika.hw01.dataaccess.concretes.CourseRepositoryImpl;
import dev.patika.hw01.entities.concretes.Course;

import java.util.List;

public class CourseManager implements CourseService {

    private final CourseRepository repository;

    public CourseManager() {
        this.repository = new CourseRepositoryImpl();
    }

    @Override
    public List<Course> getAll() {
        return repository.findAll();
    }

    @Override
    public Course getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Course getByCode(String code) {
        return repository.findByCode(code);
    }

    @Override
    public Course getByCodeWithStudents(String code) {
        return repository.findByCodeFetchStudents(code);
    }

    @Override
    public void create(Course course) {
        repository.save(course);
    }

    @Override
    public void update(Course course) {
        repository.update(course);
    }

    @Override
    public void delete(Course course) {
        repository.delete(course);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
