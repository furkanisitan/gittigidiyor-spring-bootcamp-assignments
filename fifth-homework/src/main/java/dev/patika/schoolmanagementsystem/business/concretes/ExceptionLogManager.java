package dev.patika.schoolmanagementsystem.business.concretes;

import dev.patika.schoolmanagementsystem.business.ExceptionLogService;
import dev.patika.schoolmanagementsystem.business.dtos.ExceptionLogCreateDto;
import dev.patika.schoolmanagementsystem.business.dtos.ExceptionLogDto;
import dev.patika.schoolmanagementsystem.business.helpers.FilterCriteriaHelper;
import dev.patika.schoolmanagementsystem.business.mappers.ExceptionLogMapper;
import dev.patika.schoolmanagementsystem.business.validation.validators.ExceptionLogValidator;
import dev.patika.schoolmanagementsystem.business.validation.validators.FilterCriteriaValidator;
import dev.patika.schoolmanagementsystem.core.specifications.criteria.FilterCriteria;
import dev.patika.schoolmanagementsystem.dataaccess.ExceptionLogRepository;
import dev.patika.schoolmanagementsystem.dataaccess.specifications.ExceptionLogSpecification;
import dev.patika.schoolmanagementsystem.entities.ExceptionLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Repository
public class ExceptionLogManager implements ExceptionLogService {

    private final ExceptionLogRepository repository;

    @Autowired
    public ExceptionLogManager(ExceptionLogRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ExceptionLogDto> findAll(String filter) {

        List<FilterCriteria> criteria = FilterCriteriaHelper.from(filter);
        Specification<ExceptionLog> spec = generateExceptionLogSpecification(criteria);

        return ExceptionLogMapper.INSTANCE.toExceptionLogDtoList(repository.findAll(spec));
    }

    @Transactional
    @Override
    public void create(ExceptionLogCreateDto exceptionLogCreateDto) {

        ExceptionLog exceptionLog = ExceptionLogMapper.INSTANCE.fromExceptionLogCreateDto(exceptionLogCreateDto);
        repository.save(exceptionLog);
    }

    //region utils
    private Specification<ExceptionLog> generateExceptionLogSpecification(List<FilterCriteria> criteria) {

        if (criteria.isEmpty())
            return null;

        Specification<ExceptionLog> spec = Specification.where(new ExceptionLogSpecification(FilterCriteriaValidator.validateFilterCriteria(criteria.get(0), ExceptionLogValidator.filterCriteriaPermissions)));

        for (int i = 1; i < criteria.size(); i++)
            spec.and(new ExceptionLogSpecification(FilterCriteriaValidator.validateFilterCriteria(criteria.get(i), ExceptionLogValidator.filterCriteriaPermissions)));

        return spec;
    }
    //endregion
}
