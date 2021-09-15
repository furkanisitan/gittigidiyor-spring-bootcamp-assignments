package dev.patika.schoolmanagementsystem.business.concretes;

import dev.patika.schoolmanagementsystem.business.dtos.CourseUpdateDto;
import dev.patika.schoolmanagementsystem.business.dtos.StudentCreateDto;
import dev.patika.schoolmanagementsystem.business.dtos.StudentDto;
import dev.patika.schoolmanagementsystem.business.dtos.StudentUpdateDto;
import dev.patika.schoolmanagementsystem.business.validation.exceptions.StudentAgeNotValidException;
import dev.patika.schoolmanagementsystem.core.exceptions.EntityNotExistsException;
import dev.patika.schoolmanagementsystem.dataaccess.StudentRepository;
import dev.patika.schoolmanagementsystem.entities.Course;
import dev.patika.schoolmanagementsystem.entities.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentManagerTest {

    @Mock
    StudentRepository mockStudentRepository;
    @InjectMocks
    StudentManager studentManager;

    @Test
    void findAll_ReturnNotEmptyList() {

        when(mockStudentRepository.findAll()).thenReturn(Collections.singletonList(new Student()));

        List<StudentDto> studentDtoList = studentManager.findAll();

        assertFalse(studentDtoList.isEmpty());
    }

    @Test
    void findById_ReturnStudentDtoWithId() {

        long id = 1;
        Student student = new Student();
        student.setId(id);
        when(mockStudentRepository.findById(id)).thenReturn(Optional.of(student));

        StudentDto studentDto = studentManager.findById(id);

        assertAll(
                () -> assertNotNull(studentDto),
                () -> assertEquals(id, studentDto.getId())
        );
    }

    @Test
    void create_ReturnStudentDto() {

        StudentCreateDto studentCreateDto = new StudentCreateDto();
        studentCreateDto.setBirthDate(LocalDate.now().minusYears(25));
        Student student = new Student();
        when(mockStudentRepository.save(any(Student.class))).thenReturn(student);

        StudentDto studentDto = studentManager.create(studentCreateDto);

        assertNotNull(studentDto);
    }

    @Test
    void create_ThrowStudentAgeNotValidException_StudentAgeNotBetween18and40() {

        // Student age is 10.
        StudentCreateDto studentCreateDto = new StudentCreateDto();
        studentCreateDto.setBirthDate(LocalDate.now().minusYears(10));

        assertThrows(StudentAgeNotValidException.class, () -> {
            studentManager.create(studentCreateDto);
        });
    }

    @Test
    void update_DoesNotThrowException() {

        StudentUpdateDto studentUpdateDto = new StudentUpdateDto();
        studentUpdateDto.setBirthDate(LocalDate.now().minusYears(25));
        Student student = new Student();

        when(mockStudentRepository.existsById(studentUpdateDto.getId())).thenReturn(Boolean.TRUE);
        when(mockStudentRepository.findById(studentUpdateDto.getId())).thenReturn(Optional.of(student));
        when(mockStudentRepository.save(any(Student.class))).thenReturn(student);

        assertDoesNotThrow(() -> {
            studentManager.update(studentUpdateDto);
        });
    }

    @Test
    void update_ThrowEntityNotExistsException_IdNotExists() {

        StudentUpdateDto studentUpdateDto = new StudentUpdateDto();
        studentUpdateDto.setId(1L);
        studentUpdateDto.setBirthDate(LocalDate.now().minusYears(25));

        when(mockStudentRepository.existsById(studentUpdateDto.getId())).thenReturn(Boolean.FALSE);

        assertThrows(EntityNotExistsException.class, () -> {
            studentManager.update(studentUpdateDto);
        });
    }

    @Test
    void update_ThrowStudentAgeNotValidException_StudentAgeNotBetween18and40() {

        // Student age is 10.
        StudentUpdateDto studentUpdateDto = new StudentUpdateDto();
        studentUpdateDto.setBirthDate(LocalDate.now().minusYears(10));

        assertThrows(StudentAgeNotValidException.class, () -> {
            studentManager.update(studentUpdateDto);
        });
    }

    @Test
    void deleteById_DoesNotThrowException() {

        when(mockStudentRepository.existsById(anyLong())).thenReturn(Boolean.TRUE);

        assertDoesNotThrow(() -> {
            studentManager.deleteById(anyLong());
        });
    }

    @Test
    void deleteById_ThrowEntityNotExistsException_IdNotExists() {

        when(mockStudentRepository.existsById(anyLong())).thenReturn(Boolean.FALSE);

        assertThrows(EntityNotExistsException.class, () -> {
            studentManager.deleteById(anyLong());
        });
    }
}