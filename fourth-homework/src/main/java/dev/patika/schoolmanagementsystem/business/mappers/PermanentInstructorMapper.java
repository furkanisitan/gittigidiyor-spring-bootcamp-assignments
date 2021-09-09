package dev.patika.schoolmanagementsystem.business.mappers;

import dev.patika.schoolmanagementsystem.business.dtos.PermanentInstructorCreateDto;
import dev.patika.schoolmanagementsystem.business.dtos.PermanentInstructorDto;
import dev.patika.schoolmanagementsystem.business.dtos.PermanentInstructorUpdateDto;
import dev.patika.schoolmanagementsystem.entities.PermanentInstructor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PermanentInstructorMapper {

    PermanentInstructorMapper INSTANCE = Mappers.getMapper(PermanentInstructorMapper.class);

    PermanentInstructorDto toPermanentInstructorDto(PermanentInstructor permanentInstructor);

    PermanentInstructor fromPermanentInstructorCreateDto(PermanentInstructorCreateDto permanentInstructorCreateDto);

    PermanentInstructor fromPermanentInstructorUpdateDto(PermanentInstructorUpdateDto permanentInstructorUpdateDto);

    void updateFromPermanentInstructorUpdateDto(PermanentInstructorUpdateDto permanentInstructorUpdateDto, @MappingTarget PermanentInstructor permanentInstructor);
}
