package dev.patika.hw02.business.concretes;

import dev.patika.hw02.business.abstracts.CourseService;
import dev.patika.hw02.business.abstracts.InstructorService;
import dev.patika.hw02.core.exceptions.EntityNotExistsException;
import dev.patika.hw02.core.exceptions.ForeignKeyConstraintViolationException;
import dev.patika.hw02.core.exceptions.UniqueConstraintViolationException;
import dev.patika.hw02.dataaccess.abstracts.CourseRepository;
import dev.patika.hw02.entities.concretes.Course;
import dev.patika.hw02.entities.concretes.Instructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
    public Course findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Course findByCode(String code) {
        return repository.findByCode(code);
    }

    @Override
    public Course create(Course course) {

        // check if 'code' is unique
        validateCodeIsUnique(course.getCode());

        // Check if there is an instructor with the foreign key 'instructorId'
        validateInstructorIsExists(course.getInstructor().getId());

        return repository.save(course);
    }

    @Override
    public Course update(Course course) {

        // Check if the course is exists
        if (findById(course.getId()) == null)
            throw new EntityNotExistsException("Course", Pair.of("id", course.getId()));

        // check if 'code' is unique
        validateCodeIsUniqueForUpdate(course.getCode(), course.getId());

        // Check if there is an instructor with the foreign key 'instructorId'
        validateInstructorIsExists(course.getInstructor().getId());

        return repository.update(course);
    }

    @Override
    public void deleteById(Long id) {

        Course course = findById(id);
        if (course == null)
            throw new EntityNotExistsException("Course", Pair.of("id", id));

        course.clearStudents();
        repository.delete(course);
    }

    //region validators

    /**
     * Checks if {@literal code} is unique.
     *
     * @param code unique value to validate.
     * @throws UniqueConstraintViolationException if {@literal code} is not unique.
     */
    private void validateCodeIsUnique(String code) {
        Course existsCourse = findByCode(code);
        if (existsCourse != null)
            throw new UniqueConstraintViolationException("code", code);
    }

    /**
     * Checks if {@literal code} is unique for update operation.
     *
     * @param code     unique value to validate.
     * @param courseId the id of the course to be updated.
     * @throws UniqueConstraintViolationException if {@literal code} is not unique.
     */
    private void validateCodeIsUniqueForUpdate(String code, Long courseId) {
        Course existsCourse = findByCode(code);
        if (existsCourse != null && !Objects.equals(existsCourse.getId(), courseId))
            throw new UniqueConstraintViolationException("code", code);
    }

    /**
     * Checks if there is an instructor with the foreign key @{literal instructorId}.
     *
     * @param instructorId foreign key
     * @throws ForeignKeyConstraintViolationException if @{literal instructorId} is not exists.
     */
    private void validateInstructorIsExists(Long instructorId) {
        Instructor existsInstructor = instructorService.findById(instructorId);
        if (existsInstructor == null)
            throw new ForeignKeyConstraintViolationException("instructorId", instructorId);
    }
    //endregion
}
