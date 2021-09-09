package dev.patika.schoolmanagementsystem.business.mappers;

import dev.patika.schoolmanagementsystem.business.dtos.VisitingResearcherCreateDto;
import dev.patika.schoolmanagementsystem.business.dtos.VisitingResearcherDto;
import dev.patika.schoolmanagementsystem.business.dtos.VisitingResearcherUpdateDto;
import dev.patika.schoolmanagementsystem.entities.VisitingResearcher;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VisitingResearcherMapper {

    VisitingResearcherMapper INSTANCE = Mappers.getMapper(VisitingResearcherMapper.class);

    VisitingResearcherDto toVisitingResearcherDto(VisitingResearcher visitingResearcher);

    VisitingResearcher fromVisitingResearcherCreateDto(VisitingResearcherCreateDto visitingResearcherCreateDto);

    VisitingResearcher fromVisitingResearcherUpdateDto(VisitingResearcherUpdateDto visitingResearcherUpdateDto);

    void updateFromVisitingResearcherUpdateDto(VisitingResearcherUpdateDto visitingResearcherUpdateDto, @MappingTarget VisitingResearcher visitingResearcher);
}
