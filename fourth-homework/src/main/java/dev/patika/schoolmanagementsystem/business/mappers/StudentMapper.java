package dev.patika.schoolmanagementsystem.business.mappers;

import dev.patika.schoolmanagementsystem.business.dtos.StudentCreateDto;
import dev.patika.schoolmanagementsystem.business.dtos.StudentDto;
import dev.patika.schoolmanagementsystem.business.dtos.StudentUpdateDto;
import dev.patika.schoolmanagementsystem.entities.Student;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    List<StudentDto> toStudentDtoList(List<Student> students);

    StudentDto toStudentDto(Student student);

    Student fromStudentCreateDto(StudentCreateDto studentCreateDto);

    void updateFromStudentUpdateDto(StudentUpdateDto studentUpdateDto, @MappingTarget Student student);
}
