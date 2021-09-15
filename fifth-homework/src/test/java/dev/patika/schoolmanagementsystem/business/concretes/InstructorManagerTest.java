package dev.patika.schoolmanagementsystem.business.concretes;

import dev.patika.schoolmanagementsystem.business.dtos.InstructorCreateDto;
import dev.patika.schoolmanagementsystem.business.dtos.InstructorDto;
import dev.patika.schoolmanagementsystem.business.dtos.InstructorUpdateDto;
import dev.patika.schoolmanagementsystem.business.dtos.PermanentInstructorUpdateDto;
import dev.patika.schoolmanagementsystem.core.exceptions.EntityNotExistsException;
import dev.patika.schoolmanagementsystem.core.exceptions.InstructorIsAlreadyExistException;
import dev.patika.schoolmanagementsystem.core.exceptions.InvalidEntityTypeException;
import dev.patika.schoolmanagementsystem.dataaccess.InstructorRepository;
import dev.patika.schoolmanagementsystem.entities.Instructor;
import dev.patika.schoolmanagementsystem.entities.InstructorUtility;
import dev.patika.schoolmanagementsystem.entities.VisitingResearcher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InstructorManagerTest {

    @Mock
    InstructorRepository<Instructor> mockInstructorRepository;
    @Mock
    InstructorUtility instructorUtility;
    @InjectMocks
    InstructorManager instructorManager;

    @Test
    void findById_ReturnInstructorDtoWithId() {

        long id = 1;
        Instructor instructor = new Instructor();
        instructor.setId(id);
        when(mockInstructorRepository.findById(id)).thenReturn(Optional.of(instructor));

        InstructorDto instructorDto = instructorManager.findById(id);

        assertAll(
                () -> assertNotNull(instructorDto),
                () -> assertEquals(id, instructorDto.getId())
        );
    }

    @Test
    void create_ReturnInstructorDto() {

        InstructorCreateDto instructorCreateDto = new InstructorCreateDto();
        Instructor instructor = new Instructor();
        when(mockInstructorRepository.existsByPhoneNumber(instructorCreateDto.getPhoneNumber())).thenReturn(Boolean.FALSE);
        when(mockInstructorRepository.save(any(Instructor.class))).thenReturn(instructor);

        InstructorDto instructorDto = instructorManager.create(instructorCreateDto);

        assertNotNull(instructorDto);
    }

    @Test
    void create_ThrowInstructorIsAlreadyExistException_PhoneNumberNotUnique() {

        InstructorCreateDto instructorCreateDto = new InstructorCreateDto();
        when(mockInstructorRepository.existsByPhoneNumber(instructorCreateDto.getPhoneNumber())).thenReturn(Boolean.TRUE);

        assertThrows(InstructorIsAlreadyExistException.class, () -> {
            instructorManager.create(instructorCreateDto);
        });
    }

    @Test
    void update_DoesNotThrowException() {

        InstructorUpdateDto instructorUpdateDto = new InstructorUpdateDto();
        Instructor instructor = new Instructor();
        when(mockInstructorRepository.findById(instructorUpdateDto.getId())).thenReturn(Optional.of(instructor));
        when(mockInstructorRepository.getByPhoneNumber(instructorUpdateDto.getPhoneNumber())).thenReturn(instructor);

        assertDoesNotThrow(() -> {
            instructorManager.update(instructorUpdateDto);
        });
    }

    @Test
    void update_ThrowEntityNotExistsException_IdNotExists() {

        InstructorUpdateDto instructorUpdateDto = new InstructorUpdateDto();
        instructorUpdateDto.setId(1L);

        when(mockInstructorRepository.findById(instructorUpdateDto.getId())).thenReturn(Optional.empty());

        assertThrows(EntityNotExistsException.class, () -> {
            instructorManager.update(instructorUpdateDto);
        });
    }

    @Test
    void update_ThrowInstructorIsAlreadyExistException_PhoneNumberNotUnique() {

        InstructorUpdateDto instructorUpdateDto = new InstructorUpdateDto();
        instructorUpdateDto.setId(1L);

        Instructor otherInstructor = new Instructor();
        otherInstructor.setId(2L);

        when(mockInstructorRepository.findById(instructorUpdateDto.getId())).thenReturn(Optional.of(new Instructor()));
        when(mockInstructorRepository.getByPhoneNumber(instructorUpdateDto.getPhoneNumber())).thenReturn(otherInstructor);

        assertThrows(InstructorIsAlreadyExistException.class, () -> {
            instructorManager.update(instructorUpdateDto);
        });
    }

    @Test
    void update_ThrowInvalidEntityTypeException_InstructorTypesNotEqual() {

        PermanentInstructorUpdateDto permanentInstructorUpdateDto = new PermanentInstructorUpdateDto();
        VisitingResearcher visitingResearcher = new VisitingResearcher();

        when(mockInstructorRepository.findById(permanentInstructorUpdateDto.getId())).thenReturn(Optional.of(visitingResearcher));

        assertThrows(InvalidEntityTypeException.class, () -> {
            instructorManager.update(permanentInstructorUpdateDto);
        });
    }

    @Test
    void deleteById_DoesNotThrowException() {

        Instructor instructor = spy(new Instructor());

        when(mockInstructorRepository.findById(anyLong())).thenReturn(Optional.of(instructor));
        when(instructor.utility()).thenReturn(instructorUtility);

        assertDoesNotThrow(() -> {
            instructorManager.deleteById(anyLong());
        });
    }

    @Test
    void deleteById_ThrowEntityNotExistsException_IdNotExists() {

        when(mockInstructorRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotExistsException.class, () -> {
            instructorManager.deleteById(anyLong());
        });
    }
}