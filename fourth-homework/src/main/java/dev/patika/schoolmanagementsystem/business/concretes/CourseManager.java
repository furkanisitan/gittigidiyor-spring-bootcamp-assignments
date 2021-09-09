package dev.patika.schoolmanagementsystem.business.concretes;

import dev.patika.schoolmanagementsystem.business.CourseService;
import dev.patika.schoolmanagementsystem.business.InstructorService;
import dev.patika.schoolmanagementsystem.business.StudentService;
import dev.patika.schoolmanagementsystem.business.dtos.CourseCreateDto;
import dev.patika.schoolmanagementsystem.business.dtos.CourseDto;
import dev.patika.schoolmanagementsystem.business.dtos.CourseUpdateDto;
import dev.patika.schoolmanagementsystem.business.helpers.FilterCriteriaHelper;
import dev.patika.schoolmanagementsystem.business.mappers.CourseMapper;
import dev.patika.schoolmanagementsystem.business.validation.validators.CourseValidator;
import dev.patika.schoolmanagementsystem.business.validation.validators.FilterCriteriaValidator;
import dev.patika.schoolmanagementsystem.core.exceptions.CourseIsAlreadyExistException;
import dev.patika.schoolmanagementsystem.core.exceptions.EntityNotExistsException;
import dev.patika.schoolmanagementsystem.core.exceptions.ForeignKeyConstraintViolationException;
import dev.patika.schoolmanagementsystem.core.specifications.criteria.FilterCriteria;
import dev.patika.schoolmanagementsystem.dataaccess.CourseRepository;
import dev.patika.schoolmanagementsystem.dataaccess.specifications.CourseSpecification;
import dev.patika.schoolmanagementsystem.entities.Course;
import dev.patika.schoolmanagementsystem.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Transactional(readOnly = true)
@Service
class CourseManager implements CourseService {

    private final CourseRepository repository;
    private final InstructorService instructorService;
    private final StudentService studentService;

    @Autowired
    public CourseManager(CourseRepository repository, InstructorService instructorService, StudentService studentService) {
        this.repository = repository;
        this.instructorService = instructorService;
        this.studentService = studentService;
    }

    @Override
    public List<CourseDto> findAll() {
        return CourseMapper.INSTANCE.toCourseDtoList(repository.findAll());
    }

    @Override
    public List<CourseDto> findAll(String filter) {

        List<FilterCriteria> criteria = FilterCriteriaHelper.from(filter);
        Specification<Course> spec = generateCourseSpecification(criteria);

        return CourseMapper.INSTANCE.toCourseDtoList(repository.findAll(spec));
    }

    @Override
    public CourseDto findById(Long id) {
        return CourseMapper.INSTANCE.toCourseDto(repository.findById(id).orElse(null));
    }

    @Transactional
    @Override
    public CourseDto create(CourseCreateDto courseCreateDto) {

        // Check if 'code' is unique
        validateCodeIsUnique(courseCreateDto.getCode());

        // Check if there is an instructor with the foreign key 'instructorId'
        validateInstructorIsExistsById(courseCreateDto.getInstructorId());

        Course course = CourseMapper.INSTANCE.fromCourseCreateDto(courseCreateDto);
        return CourseMapper.INSTANCE.toCourseDto(repository.save(course));
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Transactional
    @Override
    public void update(CourseUpdateDto courseUpdateDto) {

        // Check if the course is exists
        if (!repository.existsById(courseUpdateDto.getId()))
            throw new EntityNotExistsException("Course", Pair.of("id", courseUpdateDto.getId()));

        // Check if 'code' is unique for update
        validateCodeIsUnique(courseUpdateDto.getCode(), courseUpdateDto.getId());

        // Check if there is an instructor with the foreign key 'instructorId'
        validateInstructorIsExistsById(courseUpdateDto.getInstructorId());

        Course course = repository.findById(courseUpdateDto.getId()).get();
        CourseMapper.INSTANCE.updateFromCourseUpdateDto(courseUpdateDto, course);
        course.setInstructor(instructorService.getById(courseUpdateDto.getInstructorId()));

        repository.save(course);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {

        Course course = repository.findById(id)
                // Check if the Course is exists
                .orElseThrow(() -> new EntityNotExistsException("Course", Pair.of("id", id)));

        course.utility().clearStudents();
        repository.delete(course);
    }

    @Transactional
    @Override
    public void deleteAllByName(String name) {

        List<Course> courses = repository.findAllByName(name);

        for (Course course : courses) {
            course.utility().clearStudents();
            repository.delete(course);
        }
    }

    @Transactional
    @Override
    public void addStudent(Long courseId, Long studentId) {

        Course course = repository.findById(courseId)
                // Check if the Course is exists
                .orElseThrow(() -> new EntityNotExistsException("Course", Pair.of("courseId", courseId)));

        CourseValidator.validateStudentCount(course.getStudents().size());

        // Check if the Student is exists
        if (!studentService.existsById(studentId))
            throw new EntityNotExistsException("Student", Pair.of("studentId", studentId));

        // get proxy object
        Student student = studentService.getById(studentId);
        course.utility().addStudent(student);

        repository.save(course);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    //region utils
    private Specification<Course> generateCourseSpecification(List<FilterCriteria> criteria) {

        if (criteria.isEmpty())
            return null;

        Specification<Course> spec = Specification.where(new CourseSpecification(FilterCriteriaValidator.validateFilterCriteria(criteria.get(0), CourseValidator.filterCriteriaPermissions)));

        for (int i = 1; i < criteria.size(); i++)
            spec.and(new CourseSpecification(FilterCriteriaValidator.validateFilterCriteria(criteria.get(i), CourseValidator.filterCriteriaPermissions)));

        return spec;
    }
    //endregion


    //region validators

    /**
     * Checks if {@literal code} is unique.
     *
     * @param code unique value to validate.
     * @throws CourseIsAlreadyExistException if {@literal code} is not unique.
     */
    private void validateCodeIsUnique(String code) {

        if (repository.existsByCode(code))
            throw new CourseIsAlreadyExistException("code", code);
    }

    /**
     * Checks if {@literal code} is unique for update operation.
     *
     * @param code unique value to validate.
     * @param id   primary key of the course to be updated.
     * @throws CourseIsAlreadyExistException if {@literal code} is not unique for update.
     */
    private void validateCodeIsUnique(String code, Long id) {

        // get proxy object
        Course course = repository.getByCode(code);

        if (course != null && !Objects.equals(course.getId(), id))
            throw new CourseIsAlreadyExistException("code", code);
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
