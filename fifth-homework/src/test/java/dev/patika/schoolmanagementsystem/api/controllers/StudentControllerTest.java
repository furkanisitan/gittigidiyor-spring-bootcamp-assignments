package dev.patika.schoolmanagementsystem.api.controllers;

import dev.patika.schoolmanagementsystem.business.StudentService;
import dev.patika.schoolmanagementsystem.business.dtos.StudentCreateDto;
import dev.patika.schoolmanagementsystem.business.dtos.StudentDto;
import dev.patika.schoolmanagementsystem.business.dtos.StudentUpdateDto;
import dev.patika.schoolmanagementsystem.core.results.DataResult;
import dev.patika.schoolmanagementsystem.core.results.Result;
import dev.patika.schoolmanagementsystem.dataaccess.dtos.StudentGroupByGenderResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

    @Mock
    StudentService studentService;
    @InjectMocks
    StudentController studentController;

    @Test
    void getById_StatusOkAndReturnStudentDto() {

        StudentDto studentDto = new StudentDto();
        when(studentService.findById(anyLong())).thenReturn(studentDto);

        ResponseEntity<DataResult<StudentDto>> response = studentController.getById(anyLong());

        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(studentDto, Objects.requireNonNull(response.getBody()).getPayload())
        );
    }

    @Test
    void getById_StatusNotFound_IdNotExists() {

        when(studentService.findById(anyLong())).thenReturn(null);

        ResponseEntity<DataResult<StudentDto>> response = studentController.getById(anyLong());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void create_StatusCreatedAndReturnStudentDto() {

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));

        StudentCreateDto studentCreateDto = new StudentCreateDto();
        StudentDto studentDto = new StudentDto();
        studentDto.setId(1L);

        when(studentService.create(studentCreateDto)).thenReturn(studentDto);

        ResponseEntity<DataResult<StudentDto>> response = studentController.create(studentCreateDto);

        assertAll(
                () -> assertEquals(HttpStatus.CREATED, response.getStatusCode()),
                () -> assertEquals(studentDto, Objects.requireNonNull(response.getBody()).getPayload())
        );
    }

    @Test
    void update_StatusNoContent() {

        long id = 1;
        StudentUpdateDto studentUpdateDto = new StudentUpdateDto();
        studentUpdateDto.setId(id);

        doNothing().when(studentService).update(studentUpdateDto);

        ResponseEntity<Result> response = studentController.update(id, studentUpdateDto);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void deleteById_StatusNoContent() {

        long id = 1;
        doNothing().when(studentService).deleteById(id);

        ResponseEntity<Result> response = studentController.deleteById(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void getGenderCounts_StatusOkAndReturnStudentGroupByGenderResponseList() {

        List<StudentGroupByGenderResponse> expectedList = new ArrayList<>();
        when(studentService.countAndGroupByGender()).thenReturn(expectedList);

        ResponseEntity<DataResult<List<StudentGroupByGenderResponse>>> response = studentController.getGenderCounts(Optional.empty());

        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertArrayEquals(expectedList.toArray(), Objects.requireNonNull(response.getBody()).getPayload().toArray())
        );
    }
}