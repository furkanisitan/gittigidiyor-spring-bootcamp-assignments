package dev.patika.schoolmanagementsystem.business.concretes;

import dev.patika.schoolmanagementsystem.business.abstracts.StudentService;
import dev.patika.schoolmanagementsystem.core.exceptions.EntityNotExistsException;
import dev.patika.schoolmanagementsystem.dataaccess.StudentRepository;
import dev.patika.schoolmanagementsystem.entities.concretes.Student;
import dev.patika.schoolmanagementsystem.entities.dtos.student.GenderCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
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
    public List<Student> findAllByNameContains(String name) {
        return name != null && !name.isEmpty() ? repository.findAllByNameContainsIgnoreCase(name) : findAll();
    }

    @Override
    public List<GenderCount> findGenderCounts() {
        return repository.countTotalStudentsByGender();
    }

    @Override
    public Optional<Student> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Student create(Student student) {
        return repository.save(student);
    }

    @Transactional
    @Override
    public void update(Student student) {

        // Check if the student is exists
        validateExistsById(student.getId());

        repository.save(student);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {

        // Check if the student is exists
        validateExistsById(id);

        repository.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteByName(String name) {
        repository.deleteAllByName(name);
    }

    //region validators

    /**
     * Checks if there is a student with the given {@literal id}.
     *
     * @param id primary key of the entity.
     * @throws EntityNotExistsException if entity is not exists by {@literal id}.
     */
    private void validateExistsById(Long id) {

        if (!repository.existsById(id))
            throw new EntityNotExistsException("Student", Pair.of("id", id));
    }
    //endregion
}
