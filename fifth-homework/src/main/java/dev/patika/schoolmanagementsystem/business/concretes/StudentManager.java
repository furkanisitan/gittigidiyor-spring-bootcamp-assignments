package dev.patika.schoolmanagementsystem.business.concretes;

import dev.patika.schoolmanagementsystem.business.CourseService;
import dev.patika.schoolmanagementsystem.business.StudentService;
import dev.patika.schoolmanagementsystem.business.dtos.StudentCreateDto;
import dev.patika.schoolmanagementsystem.business.dtos.StudentDto;
import dev.patika.schoolmanagementsystem.business.dtos.StudentUpdateDto;
import dev.patika.schoolmanagementsystem.business.helpers.FilterCriteriaHelper;
import dev.patika.schoolmanagementsystem.business.mappers.StudentMapper;
import dev.patika.schoolmanagementsystem.business.validation.validators.FilterCriteriaValidator;
import dev.patika.schoolmanagementsystem.business.validation.validators.StudentValidator;
import dev.patika.schoolmanagementsystem.core.exceptions.EntityNotExistsException;
import dev.patika.schoolmanagementsystem.core.specifications.criteria.FilterCriteria;
import dev.patika.schoolmanagementsystem.dataaccess.StudentRepository;
import dev.patika.schoolmanagementsystem.dataaccess.dtos.StudentGroupByGenderResponse;
import dev.patika.schoolmanagementsystem.dataaccess.specifications.StudentSpecification;
import dev.patika.schoolmanagementsystem.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
class StudentManager implements StudentService {

    private final StudentRepository repository;

    @Autowired
    public StudentManager(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<StudentDto> findAll() {
        return StudentMapper.INSTANCE.toStudentDtoList(repository.findAll());
    }

    @Override
    public List<StudentDto> findAll(String filter) {

        List<FilterCriteria> criteria = FilterCriteriaHelper.from(filter);
        Specification<Student> spec = generateStudentSpecification(criteria);

        return StudentMapper.INSTANCE.toStudentDtoList(repository.findAll(spec));
    }

    @Override
    public List<StudentDto> findAllByCourseId(Long courseId) {
        return StudentMapper.INSTANCE.toStudentDtoList(repository.findAllByCourses_Id(courseId));
    }

    @Override
    public StudentDto findById(Long id) {
        return StudentMapper.INSTANCE.toStudentDto(repository.findById(id).orElse(null));
    }

    @Override
    public Student getById(Long id) {
        return repository.getById(id);
    }

    @Transactional
    @Override
    public StudentDto create(StudentCreateDto studentCreateDto) {

        // Check student's age
        StudentValidator.validateAge(studentCreateDto.getBirthDate());

        Student student = StudentMapper.INSTANCE.fromStudentCreateDto(studentCreateDto);
        return StudentMapper.INSTANCE.toStudentDto(repository.save(student));
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Transactional
    @Override
    public void update(StudentUpdateDto studentUpdateDto) {

        // Check student's age
        StudentValidator.validateAge(studentUpdateDto.getBirthDate());

        // Check if the entity is exists.
        validateExistsById(studentUpdateDto.getId());

        Student student = repository.findById(studentUpdateDto.getId()).get();
        StudentMapper.INSTANCE.updateFromStudentUpdateDto(studentUpdateDto, student);

        repository.save(student);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {

        // Check if the entity is exists.
        validateExistsById(id);

        repository.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteAllByName(String name) {
        repository.deleteAllByName(name);
    }

    @Override
    public List<StudentGroupByGenderResponse> countAndGroupByGender() {
        return repository.countGender();
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    //region utils
    private Specification<Student> generateStudentSpecification(List<FilterCriteria> criteria) {

        if (criteria.isEmpty())
            return null;

        Specification<Student> spec = Specification.where(new StudentSpecification(FilterCriteriaValidator.validateFilterCriteria(criteria.get(0), StudentValidator.filterCriteriaPermissions)));

        for (int i = 1; i < criteria.size(); i++)
            spec.and(new StudentSpecification(FilterCriteriaValidator.validateFilterCriteria(criteria.get(i), StudentValidator.filterCriteriaPermissions)));

        return spec;
    }
    //endregion

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
