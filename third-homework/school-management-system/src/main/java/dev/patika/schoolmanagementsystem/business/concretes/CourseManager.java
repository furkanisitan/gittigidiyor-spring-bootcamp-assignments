package dev.patika.schoolmanagementsystem.business.concretes;

import dev.patika.schoolmanagementsystem.business.abstracts.CourseService;
import dev.patika.schoolmanagementsystem.business.abstracts.InstructorService;
import dev.patika.schoolmanagementsystem.core.exceptions.EntityNotExistsException;
import dev.patika.schoolmanagementsystem.core.exceptions.ForeignKeyConstraintViolationException;
import dev.patika.schoolmanagementsystem.core.exceptions.UniqueConstraintViolationException;
import dev.patika.schoolmanagementsystem.dataaccess.CourseRepository;
import dev.patika.schoolmanagementsystem.entities.concretes.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Transactional(readOnly = true)
@Service
public class CourseManager implements CourseService {

    private final CourseRepository repository;
    private final InstructorService instructorService;

    @Autowired
    public CourseManager(CourseRepository repository, InstructorService instructorService) {
        this.repository = repository;
        this.instructorService = instructorService;
    }

    @Override
    public List<Course> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Course> findAllByNameContains(String name) {
        return name != null && !name.isEmpty() ? repository.findAllByNameContainsIgnoreCase(name) : findAll();
    }

    @Override
    public Optional<Course> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Course create(Course course) {

        // Check if 'code' is unique
        validateCodeIsUnique(course.getCode());

        // Check if there is an instructor with the foreign key 'instructorId'
        validateInstructorIsExistsById(course.getInstructor().getId());

        return repository.save(course);
    }

    @Transactional
    @Override
    public void update(Course course) {

        // Check if the course is exists
        if (!repository.existsById(course.getId()))
            throw new EntityNotExistsException("Course", Pair.of("id", course.getId()));

        // Check if 'code' is unique for update
        validateCodeIsUnique(course.getCode(), course.getId());

        // Check if there is an instructor with the foreign key 'instructorId'
        validateInstructorIsExistsById(course.getInstructor().getId());

        repository.save(course);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {

        Course course = findById(id)
                // Check if the Course is exists
                .orElseThrow(() -> new EntityNotExistsException("Course", Pair.of("id", id)));

        course.clearStudents();
        repository.delete(course);
    }

    @Transactional
    @Override
    public void deleteByName(String name) {

        List<Course> courses = repository.findAllByName(name);

        for (Course course : courses) {
            course.clearStudents();
            repository.delete(course);
        }
    }

    //region validators

    /**
     * Checks if {@literal code} is unique.
     *
     * @param code unique value to validate.
     * @throws UniqueConstraintViolationException if {@literal code} is not unique.
     */
    private void validateCodeIsUnique(String code) {

        if (repository.existsByCode(code))
            throw new UniqueConstraintViolationException("code", code);
    }

    /**
     * Checks if {@literal code} is unique for update operation.
     *
     * @param code unique value to validate.
     * @param id   primary key of the course to be updated.
     * @throws UniqueConstraintViolationException if {@literal code} is not unique for update.
     */
    private void validateCodeIsUnique(String code, Long id) {

        // get proxy object
        Course course = repository.getByCode(code);

        if (course != null && !Objects.equals(course.getId(), id))
            throw new UniqueConstraintViolationException("code", code);
    }

    /**
     * Checks if there is an instructor with the foreign key @{literal instructorId}.
     *
     * @param instructorId foreign key
     * @throws ForeignKeyConstraintViolationException if @{literal instructorId} is not exists.
     */
    private void validateInstructorIsExistsById(Long instructorId) {

        if (!instructorService.existsById(instructorId))
            throw new ForeignKeyConstraintViolationException("instructorId", instructorId);
    }
    //endregion
}
