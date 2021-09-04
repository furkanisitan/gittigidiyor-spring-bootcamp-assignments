package dev.patika.hw02.business.concretes;

import dev.patika.hw02.business.abstracts.StudentService;
import dev.patika.hw02.dataaccess.abstracts.StudentRepository;
import dev.patika.hw02.entities.concretes.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentManager implements StudentService {

    private final StudentRepository repository;

    @Autowired
    public StudentManager(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Student> findAll() {
        return repository.findAll();
    }

    @Override
    public Student findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Student create(Student student) {
        return repository.save(student);
    }

    @Override
    public Student update(Student student) {
        return repository.update(student);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
