package dev.patika.schoolmanagementsystem.business.mappers;

import dev.patika.schoolmanagementsystem.business.dtos.*;
import dev.patika.schoolmanagementsystem.entities.Instructor;
import dev.patika.schoolmanagementsystem.entities.PermanentInstructor;
import dev.patika.schoolmanagementsystem.entities.VisitingResearcher;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface InstructorMapper {

    InstructorMapper INSTANCE = Mappers.getMapper(InstructorMapper.class);

    @IterableMapping(qualifiedByName = "toInstructorDto")
    List<InstructorDto> toInstructorDtoList(List<? extends Instructor> instructors);

    InstructorDto instructorToInstructorDto(Instructor instructor);

    @Named(value = "toInstructorDto")
    default InstructorDto toInstructorDto(Instructor instructor) {
        if (instructor instanceof PermanentInstructor)
            return PermanentInstructorMapper.INSTANCE.toPermanentInstructorDto((PermanentInstructor) instructor);
        else if (instructor instanceof VisitingResearcher)
            return VisitingResearcherMapper.INSTANCE.toVisitingResearcherDto((VisitingResearcher) instructor);
        return InstructorMapper.INSTANCE.instructorToInstructorDto(instructor);
    }

    Instructor instructorFromInstructorCreateDto(InstructorCreateDto instructorCreateDto);

    @Named(value = "fromInstructorCreateDto")
    default Instructor fromInstructorCreateDto(InstructorCreateDto instructorCreateDto) {
        if (instructorCreateDto instanceof PermanentInstructorCreateDto)
            return PermanentInstructorMapper.INSTANCE.fromPermanentInstructorCreateDto((PermanentInstructorCreateDto) instructorCreateDto);
        else if (instructorCreateDto instanceof VisitingResearcherCreateDto)
            return VisitingResearcherMapper.INSTANCE.fromVisitingResearcherCreateDto((VisitingResearcherCreateDto) instructorCreateDto);
        return InstructorMapper.INSTANCE.instructorFromInstructorCreateDto(instructorCreateDto);
    }

    Instructor instructorFromInstructorUpdateDto(InstructorUpdateDto instructorUpdateDto);

    @Named(value = "fromInstructorUpdateDto")
    default Instructor fromInstructorUpdateDto(InstructorUpdateDto instructorUpdateDto) {
        if (instructorUpdateDto instanceof PermanentInstructorUpdateDto)
            return PermanentInstructorMapper.INSTANCE.fromPermanentInstructorUpdateDto((PermanentInstructorUpdateDto) instructorUpdateDto);
        else if (instructorUpdateDto instanceof VisitingResearcherUpdateDto)
            return VisitingResearcherMapper.INSTANCE.fromVisitingResearcherUpdateDto((VisitingResearcherUpdateDto) instructorUpdateDto);
        return InstructorMapper.INSTANCE.instructorFromInstructorUpdateDto(instructorUpdateDto);
    }

    void instructorUpdateFromInstructorUpdateDto(InstructorUpdateDto instructorUpdateDto, @MappingTarget Instructor instructor);

    @Named(value = "updateFromInstructorUpdateDto")
    default void updateFromInstructorUpdateDto(InstructorUpdateDto instructorUpdateDto, @MappingTarget Instructor instructor) {
        if (instructorUpdateDto instanceof PermanentInstructorUpdateDto)
            PermanentInstructorMapper.INSTANCE.updateFromPermanentInstructorUpdateDto((PermanentInstructorUpdateDto) instructorUpdateDto, (PermanentInstructor) instructor);
        else if (instructorUpdateDto instanceof VisitingResearcherUpdateDto)
            VisitingResearcherMapper.INSTANCE.updateFromVisitingResearcherUpdateDto((VisitingResearcherUpdateDto) instructorUpdateDto, (VisitingResearcher) instructor);
        else
            InstructorMapper.INSTANCE.instructorUpdateFromInstructorUpdateDto(instructorUpdateDto, instructor);
    }

}
