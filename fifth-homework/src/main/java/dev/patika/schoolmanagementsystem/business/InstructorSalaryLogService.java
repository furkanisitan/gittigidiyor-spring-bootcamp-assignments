package dev.patika.schoolmanagementsystem.business;

import dev.patika.schoolmanagementsystem.business.dtos.InstructorSalaryLogCreateDto;
import dev.patika.schoolmanagementsystem.business.dtos.InstructorSalaryLogDto;

import java.util.List;

public interface InstructorSalaryLogService {

    List<InstructorSalaryLogDto> findAllByInstructorId(Long instructorId);

    void create(InstructorSalaryLogCreateDto dto);

}
