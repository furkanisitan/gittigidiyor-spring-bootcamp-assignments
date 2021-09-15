package dev.patika.schoolmanagementsystem.business.mappers;

import dev.patika.schoolmanagementsystem.business.dtos.InstructorSalaryLogCreateDto;
import dev.patika.schoolmanagementsystem.business.dtos.InstructorSalaryLogDto;
import dev.patika.schoolmanagementsystem.entities.InstructorSalaryLog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface InstructorSalaryLogMapper {

    InstructorSalaryLogMapper INSTANCE = Mappers.getMapper(InstructorSalaryLogMapper.class);

    List<InstructorSalaryLogDto> toInstructorSalaryLogDtoList(List<InstructorSalaryLog> instructorSalaryLogs);

    @Mapping(target = "instructorId", source = "instructor.id")
    InstructorSalaryLogDto toInstructorSalaryLogDto(InstructorSalaryLog instructorSalaryLog);

    @Mapping(target = "instructor.id", source = "instructorId")
    InstructorSalaryLog fromInstructorSalaryLogCreateDto(InstructorSalaryLogCreateDto dto);

}
