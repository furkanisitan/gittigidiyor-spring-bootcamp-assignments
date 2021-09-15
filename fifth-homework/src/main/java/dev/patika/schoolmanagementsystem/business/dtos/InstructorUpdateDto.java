package dev.patika.schoolmanagementsystem.business.dtos;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION, defaultImpl = InstructorCreateDto.class)
@JsonSubTypes({@JsonSubTypes.Type(PermanentInstructorUpdateDto.class), @JsonSubTypes.Type(VisitingResearcherUpdateDto.class)})
@Data
public class InstructorUpdateDto {

    private Long id;
    private String phoneNumber;
    private String name;
    private String address;
}
