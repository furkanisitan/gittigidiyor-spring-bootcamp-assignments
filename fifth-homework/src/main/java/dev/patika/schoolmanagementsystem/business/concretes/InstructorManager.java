package dev.patika.schoolmanagementsystem.business.concretes;

import dev.patika.schoolmanagementsystem.business.InstructorService;
import dev.patika.schoolmanagementsystem.business.criteria.InstructorCriteria;
import dev.patika.schoolmanagementsystem.business.dtos.InstructorCreateDto;
import dev.patika.schoolmanagementsystem.business.dtos.InstructorDto;
import dev.patika.schoolmanagementsystem.business.dtos.InstructorUpdateDto;
import dev.patika.schoolmanagementsystem.business.helpers.FilterCriteriaHelper;
import dev.patika.schoolmanagementsystem.business.mappers.InstructorMapper;
import dev.patika.schoolmanagementsystem.business.validation.validators.FilterCriteriaValidator;
import dev.patika.schoolmanagementsystem.business.validation.validators.InstructorValidator;
import dev.patika.schoolmanagementsystem.core.exceptions.EntityNotExistsException;
import dev.patika.schoolmanagementsystem.core.exceptions.InstructorIsAlreadyExistException;
import dev.patika.schoolmanagementsystem.core.exceptions.InvalidEntityTypeException;
import dev.patika.schoolmanagementsystem.core.specifications.criteria.FilterCriteria;
import dev.patika.schoolmanagementsystem.dataaccess.InstructorRepository;
import dev.patika.schoolmanagementsystem.dataaccess.PermanentInstructorRepository;
import dev.patika.schoolmanagementsystem.dataaccess.VisitingResearcherRepository;
import dev.patika.schoolmanagementsystem.dataaccess.specifications.InstructorSpecification;
import dev.patika.schoolmanagementsystem.entities.Instructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Transactional(readOnly = true)
@Service
class InstructorManager implements InstructorService {

    private final InstructorRepository<Instructor> repository;
    private final PermanentInstructorRepository permanentInstructorRepository;
    private final VisitingResearcherRepository visitingResearcherRepository;

    @Autowired
    public InstructorManager(InstructorRepository<Instructor> repository, PermanentInstructorRepository permanentInstructorRepository, VisitingResearcherRepository visitingResearcherRepository) {
        this.repository = repository;
        this.permanentInstructorRepository = permanentInstructorRepository;
        this.visitingResearcherRepository = visitingResearcherRepository;
    }

    @Override
    public List<InstructorDto> findAll(String filter, InstructorCriteria criteria) {

        List<FilterCriteria> filterCriteria = FilterCriteriaHelper.from(filter);
        Specification<Instructor> spec = generateInstructorSpecification(filterCriteria);

        // Filter by valid limit or sort criteria
        PageRequest pageRequest = criteria.generatePageRequest();
        Sort sort = criteria.generateSort();

        if (sort != null) {
            JpaRepository<? extends Instructor, Long> repo = getRepositoryForSort(criteria.getSort());
            return InstructorMapper.INSTANCE.toDtoList(pageRequest == null ? repo.findAll(sort) : repo.findAll(pageRequest.withSort(sort)).getContent());
        }

        return InstructorMapper.INSTANCE.toDtoList(pageRequest == null ? repository.findAll(spec) : repository.findAll(spec, pageRequest).getContent());
    }

    @Override
    public InstructorDto findById(Long id) {
        return InstructorMapper.INSTANCE.toDto(repository.findById(id).orElse(null));
    }

    @Override
    public Instructor getById(Long id) {
        return repository.getById(id);
    }

    @Transactional
    @Override
    public InstructorDto create(InstructorCreateDto instructorCreateDto) {

        // Check if 'phoneNumber' is unique
        validatePhoneNumberIsUnique(instructorCreateDto.getPhoneNumber());

        Instructor instructor = InstructorMapper.INSTANCE.fromCreateDto(instructorCreateDto);
        return InstructorMapper.INSTANCE.toDto(repository.save(instructor));
    }

    @Transactional
    @Override
    public void update(InstructorUpdateDto instructorUpdateDto) {

        Instructor existsInstructor = repository.findById(instructorUpdateDto.getId()).orElse(null);

        // Check if the instructor is exists
        if (existsInstructor == null)
            throw new EntityNotExistsException("Instructor", Pair.of("id", instructorUpdateDto.getId()));

        // Check if existing entity type equals type of entity to update.
        Instructor instructor = InstructorMapper.INSTANCE.fromUpdateDto(instructorUpdateDto);
        if (!instructor.getClass().equals(existsInstructor.getClass()))
            throw new InvalidEntityTypeException(existsInstructor.getClass().getSimpleName());

        // Check if 'phoneNumber' is unique for update
        validatePhoneNumberIsUnique(instructorUpdateDto.getPhoneNumber(), existsInstructor.getId());

        InstructorMapper.INSTANCE.updateFromUpdateDto(instructorUpdateDto, existsInstructor);
        repository.save(existsInstructor);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {

        Instructor instructor = repository.findById(id)
                // Check if the Course is exists
                .orElseThrow(() -> new EntityNotExistsException("Instructor", Pair.of("id", id)));

        instructor.utility().clearCourses();
        repository.delete(instructor);
    }

    @Transactional
    @Override
    public void deleteAllByName(String name) {
        List<Instructor> instructors = repository.findAllByName(name);

        for (Instructor instructor : instructors) {
            instructor.utility().clearCourses();
            repository.delete(instructor);
        }
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    //region utils
    private Specification<Instructor> generateInstructorSpecification(List<FilterCriteria> criteria) {

        if (criteria.isEmpty())
            return null;

        Specification<Instructor> spec = Specification.where(new InstructorSpecification(FilterCriteriaValidator.validateFilterCriteria(criteria.get(0), InstructorValidator.filterCriteriaPermissions)));

        for (int i = 1; i < criteria.size(); i++)
            spec.and(new InstructorSpecification(FilterCriteriaValidator.validateFilterCriteria(criteria.get(i), InstructorValidator.filterCriteriaPermissions)));

        return spec;
    }

    private JpaRepository<? extends Instructor, Long> getRepositoryForSort(String sort) {
        if (sort.contains("fixedSalary")) return permanentInstructorRepository;
        if (sort.contains("hourlySalary")) return visitingResearcherRepository;
        return repository;
    }
    //endregion

    //region validators

    /**
     * Checks if {@literal phoneNumber} is unique.
     *
     * @param phoneNumber unique value to validate.
     * @throws InstructorIsAlreadyExistException if {@literal phoneNumber} is not unique.
     */
    private void validatePhoneNumberIsUnique(String phoneNumber) {

        if (repository.existsByPhoneNumber(phoneNumber))
            throw new InstructorIsAlreadyExistException("phoneNumber", phoneNumber);
    }

    /**
     * Checks if {@literal phoneNumber} is unique for update operation.
     *
     * @param phoneNumber unique value to validate.
     * @param id          primary key of the instructor to be updated.
     * @throws InstructorIsAlreadyExistException if {@literal phoneNumber} is not unique for update.
     */
    private void validatePhoneNumberIsUnique(String phoneNumber, Long id) {

        // get proxy object
        Instructor existsInstructor = repository.getByPhoneNumber(phoneNumber);

        if (existsInstructor != null && !Objects.equals(existsInstructor.getId(), id))
            throw new InstructorIsAlreadyExistException("phoneNumber", phoneNumber);
    }
    //endregion
}
