package dev.patika.schoolmanagementsystem.business.concretes;

import dev.patika.schoolmanagementsystem.business.InstructorSalaryLogService;
import dev.patika.schoolmanagementsystem.business.VisitingResearcherService;
import dev.patika.schoolmanagementsystem.business.dtos.InstructorSalaryLogCreateDto;
import dev.patika.schoolmanagementsystem.business.dtos.InstructorSalaryLogDto;
import dev.patika.schoolmanagementsystem.core.exceptions.EntityNotExistsException;
import dev.patika.schoolmanagementsystem.core.utils.SalaryHelper;
import dev.patika.schoolmanagementsystem.dataaccess.VisitingResearcherRepository;
import dev.patika.schoolmanagementsystem.entities.VisitingResearcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
public class VisitingResearcherManager implements VisitingResearcherService {

    private final VisitingResearcherRepository repository;
    private final InstructorSalaryLogService instructorSalaryLogService;

    @Autowired
    public VisitingResearcherManager(VisitingResearcherRepository repository, InstructorSalaryLogService instructorSalaryLogService) {
        this.repository = repository;
        this.instructorSalaryLogService = instructorSalaryLogService;
    }

    @Transactional
    @Override
    public void updateHourlySalary(Long id, double percent) {

        VisitingResearcher visitingResearcher = repository.findById(id)
                // Check if the visitingResearcher is exists
                .orElseThrow(() -> new EntityNotExistsException("VisitingResearcher", Pair.of("id", id)));

        InstructorSalaryLogCreateDto dto = new InstructorSalaryLogCreateDto();
        dto.setInstructorId(id);
        dto.setPercent(percent);
        dto.setPreviousSalary(visitingResearcher.getHourlySalary());

        visitingResearcher.setHourlySalary(SalaryHelper.increaseByPercent(visitingResearcher.getHourlySalary(), percent));
        repository.save(visitingResearcher);

        dto.setCurrentSalary(visitingResearcher.getHourlySalary());
        instructorSalaryLogService.create(dto);
    }

    @Override
    public List<InstructorSalaryLogDto> findAllSalaryLogsById(Long id) {
        if (!repository.existsById(id))
            throw new EntityNotExistsException("VisitingResearcher", Pair.of("id", id));

        return instructorSalaryLogService.findAllByInstructorId(id);
    }
}
