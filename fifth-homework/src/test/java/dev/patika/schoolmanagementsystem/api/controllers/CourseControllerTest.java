package dev.patika.schoolmanagementsystem.api.controllers;

import dev.patika.schoolmanagementsystem.business.CourseService;
import dev.patika.schoolmanagementsystem.business.StudentService;
import dev.patika.schoolmanagementsystem.business.dtos.CourseCreateDto;
import dev.patika.schoolmanagementsystem.business.dtos.CourseDto;
import dev.patika.schoolmanagementsystem.business.dtos.CourseUpdateDto;
import dev.patika.schoolmanagementsystem.business.dtos.StudentDto;
import dev.patika.schoolmanagementsystem.core.exceptions.EntityNotExistsException;
import dev.patika.schoolmanagementsystem.core.results.DataResult;
import dev.patika.schoolmanagementsystem.core.results.Result;
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

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CourseControllerTest {

    @Mock
    CourseService courseService;
    @Mock
    StudentService studentService;
    @InjectMocks
    CourseController courseController;

    @Test
    void getById_StatusOkAndReturnCourseDto() {

        CourseDto courseDto = new CourseDto();
        when(courseService.findById(anyLong())).thenReturn(courseDto);

        ResponseEntity<DataResult<CourseDto>> response = courseController.getById(anyLong());

        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(courseDto, Objects.requireNonNull(response.getBody()).getPayload())
        );
    }

    @Test
    void getById_StatusNotFound_IdNotExists() {

        when(courseService.findById(anyLong())).thenReturn(null);

        ResponseEntity<DataResult<CourseDto>> response = courseController.getById(anyLong());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void create_StatusCreatedAndReturnCourseDto() {

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));

        CourseCreateDto courseCreateDto = new CourseCreateDto();
        CourseDto courseDto = new CourseDto();
        courseDto.setId(1L);

        when(courseService.create(courseCreateDto)).thenReturn(courseDto);

        ResponseEntity<DataResult<CourseDto>> response = courseController.create(courseCreateDto);

        assertAll(
                () -> assertEquals(HttpStatus.CREATED, response.getStatusCode()),
                () -> assertEquals(courseDto, Objects.requireNonNull(response.getBody()).getPayload())
        );
    }

    @Test
    void update_StatusNoContent() {

        long id = 1;
        CourseUpdateDto courseUpdateDto = new CourseUpdateDto();
        courseUpdateDto.setId(id);

        doNothing().when(courseService).update(courseUpdateDto);

        ResponseEntity<Result> response = courseController.update(id, courseUpdateDto);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void deleteById_StatusNoContent() {

        long id = 1;
        doNothing().when(courseService).deleteById(id);

        ResponseEntity<Result> response = courseController.deleteById(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void getStudents_StatusOkAndReturnStudentDtoList() {

        long id = 1;
        List<StudentDto> studentDtoList = Collections.singletonList(new StudentDto());
        when(courseService.existsById(id)).thenReturn(Boolean.TRUE);
        when(studentService.findAllByCourseId(id)).thenReturn(studentDtoList);

        ResponseEntity<DataResult<List<StudentDto>>> response = courseController.getStudents(id);

        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertArrayEquals(studentDtoList.toArray(), Objects.requireNonNull(response.getBody()).getPayload().toArray())
        );
    }

    @Test
    void getStudents_ThrowEntityNotExistsException_IdNotExists() {

        when(courseService.existsById(anyLong())).thenReturn(Boolean.FALSE);

        assertThrows(EntityNotExistsException.class, () -> courseController.getStudents(anyLong()));
    }

    @Test
    void addStudent_StatusNoContent() {

        long courseId = 1, studentId = 1;
        doNothing().when(courseService).addStudent(courseId, studentId);

        ResponseEntity<DataResult<CourseDto>> response = courseController.addStudent(courseId, studentId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}