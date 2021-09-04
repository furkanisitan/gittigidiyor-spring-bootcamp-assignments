package dev.patika.hw02.business.abstracts;

import dev.patika.hw02.entities.concretes.Student;

import java.util.List;

public interface StudentService {

    List<Student> findAll();

    Student findById(Long id);

    Student create(Student student);

    Student update(Student student);

    void deleteById(Long id);
}
