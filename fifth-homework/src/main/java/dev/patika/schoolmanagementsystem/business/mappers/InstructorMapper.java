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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

@Mapper
public abstract class InstructorMapper {

    public static InstructorMapper INSTANCE = Mappers.getMapper(InstructorMapper.class);

    @IterableMapping(qualifiedByName = "toDto")
    public abstract List<InstructorDto> toDtoList(List<? extends Instructor> instructors);

    //region toDto
    private Map<Class<?>, Function<Instructor, InstructorDto>> toDtoMap = new HashMap<Class<?>, Function<Instructor, InstructorDto>>() {{
        put(PermanentInstructor.class, (instructor) -> _toDto((PermanentInstructor) instructor));
        put(VisitingResearcher.class, (instructor) -> _toDto((VisitingResearcher) instructor));
    }};

    @Named(value = "toDto")
    public InstructorDto toDto(Instructor instructor) {
        if (instructor == null) return null;
        Function<Instructor, InstructorDto> f = toDtoMap.get(instructor.getClass());
        if (f == null) return _toDto(instructor);
        return f.apply(instructor);
    }

    abstract InstructorDto _toDto(Instructor instructor);

    abstract PermanentInstructorDto _toDto(PermanentInstructor instructor);

    abstract VisitingResearcherDto _toDto(VisitingResearcher instructor);
    //endregion

    //region fromCreateDto
    private Map<Class<?>, Function<InstructorCreateDto, Instructor>> fromCreateDtoMap = new HashMap<Class<?>, Function<InstructorCreateDto, Instructor>>() {{
        put(PermanentInstructorCreateDto.class, (dto) -> _fromCreateDto((PermanentInstructorCreateDto) dto));
        put(VisitingResearcherCreateDto.class, (dto) -> _fromCreateDto((VisitingResearcherCreateDto) dto));
    }};

    public Instructor fromCreateDto(InstructorCreateDto dto) {
        if (dto == null) return null;
        Function<InstructorCreateDto, Instructor> f = fromCreateDtoMap.get(dto.getClass());
        if (f == null) return _fromCreateDto(dto);
        return f.apply(dto);
    }

    abstract Instructor _fromCreateDto(InstructorCreateDto dto);

    abstract PermanentInstructor _fromCreateDto(PermanentInstructorCreateDto dto);

    abstract VisitingResearcher _fromCreateDto(VisitingResearcherCreateDto dto);
    //endregion

    //region fromUpdateDto
    private Map<Class<?>, Function<InstructorUpdateDto, Instructor>> fromUpdateDtoMap = new HashMap<Class<?>, Function<InstructorUpdateDto, Instructor>>() {{
        put(PermanentInstructorUpdateDto.class, (dto) -> _fromUpdateDto((PermanentInstructorUpdateDto) dto));
        put(VisitingResearcherUpdateDto.class, (dto) -> _fromUpdateDto((VisitingResearcherUpdateDto) dto));
    }};

    public Instructor fromUpdateDto(InstructorUpdateDto dto) {
        if (dto == null) return null;
        Function<InstructorUpdateDto, Instructor> f = fromUpdateDtoMap.get(dto.getClass());
        if (f == null) return _fromUpdateDto(dto);
        return f.apply(dto);
    }

    abstract Instructor _fromUpdateDto(InstructorUpdateDto dto);

    abstract PermanentInstructor _fromUpdateDto(PermanentInstructorUpdateDto dto);

    abstract VisitingResearcher _fromUpdateDto(VisitingResearcherUpdateDto dto);
    //endregion


    //region updateFromUpdateDto
    private Map<Class<?>, BiConsumer<InstructorUpdateDto, Instructor>> updateFromUpdateDtoMap = new HashMap<Class<?>, BiConsumer<InstructorUpdateDto, Instructor>>() {{
        put(PermanentInstructorUpdateDto.class, (dto, instructor) -> _updateFromUpdateDto((PermanentInstructorUpdateDto) dto, (PermanentInstructor) instructor));
        put(VisitingResearcherUpdateDto.class, (dto, instructor) -> _updateFromUpdateDto((VisitingResearcherUpdateDto) dto, (VisitingResearcher) instructor));
    }};

    public void updateFromUpdateDto(InstructorUpdateDto dto, @MappingTarget Instructor instructor) {
        if (dto == null) return;
        BiConsumer<InstructorUpdateDto, Instructor> f = updateFromUpdateDtoMap.get(dto.getClass());
        if (f == null) _updateFromUpdateDto(dto, instructor);
        else f.accept(dto, instructor);
    }

    abstract void _updateFromUpdateDto(InstructorUpdateDto dto, @MappingTarget Instructor instructor);

    abstract void _updateFromUpdateDto(PermanentInstructorUpdateDto dto, @MappingTarget PermanentInstructor instructor);

    abstract void _updateFromUpdateDto(VisitingResearcherUpdateDto dto, @MappingTarget VisitingResearcher instructor);
    //endregion

}
