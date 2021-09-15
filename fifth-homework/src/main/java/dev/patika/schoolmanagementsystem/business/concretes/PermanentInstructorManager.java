package dev.patika.schoolmanagementsystem.business.concretes;

import dev.patika.schoolmanagementsystem.business.InstructorSalaryLogService;
import dev.patika.schoolmanagementsystem.business.PermanentInstructorService;
import dev.patika.schoolmanagementsystem.business.dtos.InstructorSalaryLogCreateDto;
import dev.patika.schoolmanagementsystem.business.dtos.InstructorSalaryLogDto;
import dev.patika.schoolmanagementsystem.business.mappers.InstructorSalaryLogMapper;
import dev.patika.schoolmanagementsystem.core.exceptions.EntityNotExistsException;
import dev.patika.schoolmanagementsystem.core.utils.SalaryHelper;
import dev.patika.schoolmanagementsystem.dataaccess.PermanentInstructorRepository;
import dev.patika.schoolmanagementsystem.entities.PermanentInstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
public class PermanentInstructorManager implements PermanentInstructorService {

    private final PermanentInstructorRepository repository;
    private final InstructorSalaryLogService instructorSalaryLogService;

    @Autowired
    public PermanentInstructorManager(PermanentInstructorRepository repository, InstructorSalaryLogService instructorSalaryLogService) {
        this.repository = repository;
        this.instructorSalaryLogService = instructorSalaryLogService;
    }

    @Transactional
    @Override
    public void updateFixedSalary(Long id, double percent) {
        PermanentInstructor permanentInstructor = repository.findById(id)
                // Check if the visitingResearcher is exists
                .orElseThrow(() -> new EntityNotExistsException("PermanentInstructor", Pair.of("id", id)));

        InstructorSalaryLogCreateDto dto = new InstructorSalaryLogCreateDto();
        dto.setInstructorId(id);
        dto.setPercent(percent);
        dto.setPreviousSalary(permanentInstructor.getFixedSalary());

        permanentInstructor.setFixedSalary(SalaryHelper.increaseByPercent(permanentInstructor.getFixedSalary(), percent));
        repository.save(permanentInstructor);

        dto.setCurrentSalary(permanentInstructor.getFixedSalary());
        instructorSalaryLogService.create(dto);
    }

    @Override
    public List<InstructorSalaryLogDto> findAllSalaryLogsById(Long id) {

        if (!repository.existsById(id))
            throw new EntityNotExistsException("PermanentInstructor", Pair.of("id", id));

        return instructorSalaryLogService.findAllByInstructorId(id);
    }

}
