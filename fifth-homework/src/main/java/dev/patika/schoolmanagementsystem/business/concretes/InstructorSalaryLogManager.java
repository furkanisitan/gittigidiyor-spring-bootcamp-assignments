package dev.patika.schoolmanagementsystem.business.concretes;

import dev.patika.schoolmanagementsystem.business.InstructorSalaryLogService;
import dev.patika.schoolmanagementsystem.business.dtos.InstructorSalaryLogCreateDto;
import dev.patika.schoolmanagementsystem.business.dtos.InstructorSalaryLogDto;
import dev.patika.schoolmanagementsystem.business.mappers.InstructorSalaryLogMapper;
import dev.patika.schoolmanagementsystem.core.utils.ClientRequestInfo;
import dev.patika.schoolmanagementsystem.dataaccess.InstructorSalaryLogRepository;
import dev.patika.schoolmanagementsystem.entities.InstructorSalaryLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
public class InstructorSalaryLogManager implements InstructorSalaryLogService {

    private final InstructorSalaryLogRepository repository;
    private final ClientRequestInfo clientRequestInfo;

    @Autowired
    public InstructorSalaryLogManager(InstructorSalaryLogRepository repository, ClientRequestInfo clientRequestInfo) {
        this.repository = repository;
        this.clientRequestInfo = clientRequestInfo;
    }

    @Override
    public List<InstructorSalaryLogDto> findAllByInstructorId(Long instructorId) {
        return InstructorSalaryLogMapper.INSTANCE.toInstructorSalaryLogDtoList(repository.findAllByInstructor_Id(instructorId));
    }

    @Transactional
    @Override
    public void create(InstructorSalaryLogCreateDto dto) {

        InstructorSalaryLog instructorSalaryLog = InstructorSalaryLogMapper.INSTANCE.fromInstructorSalaryLogCreateDto(dto);
        instructorSalaryLog.setClientUrl(clientRequestInfo.getClientUrl());
        instructorSalaryLog.setClientIpAddress(clientRequestInfo.getClientIpAddress());
        instructorSalaryLog.setSessionActivityId(clientRequestInfo.getSessionActivityId());

        repository.save(instructorSalaryLog);
    }
}
