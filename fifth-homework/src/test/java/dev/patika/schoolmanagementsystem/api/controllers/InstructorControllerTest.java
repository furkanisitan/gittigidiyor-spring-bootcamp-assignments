package dev.patika.schoolmanagementsystem.api.controllers;

import dev.patika.schoolmanagementsystem.business.InstructorService;
import dev.patika.schoolmanagementsystem.business.dtos.InstructorCreateDto;
import dev.patika.schoolmanagementsystem.business.dtos.InstructorDto;
import dev.patika.schoolmanagementsystem.business.dtos.InstructorUpdateDto;
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

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InstructorControllerTest {

    @Mock
    InstructorService instructorService;
    @InjectMocks
    InstructorController instructorController;

    @Test
    void getById_StatusOkAndReturnInstructorDto() {

        InstructorDto instructorDto = new InstructorDto();
        when(instructorService.findById(anyLong())).thenReturn(instructorDto);

        ResponseEntity<DataResult<InstructorDto>> response = instructorController.getById(anyLong());

        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(instructorDto, Objects.requireNonNull(response.getBody()).getPayload())
        );
    }

    @Test
    void getById_StatusNotFound_IdNotExists() {

        when(instructorService.findById(anyLong())).thenReturn(null);

        ResponseEntity<DataResult<InstructorDto>> response = instructorController.getById(anyLong());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void create_StatusCreatedAndReturnInstructorDto() {

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));

        InstructorCreateDto instructorCreateDto = new InstructorCreateDto();
        InstructorDto instructorDto = new InstructorDto();
        instructorDto.setId(1L);

        when(instructorService.create(instructorCreateDto)).thenReturn(instructorDto);

        ResponseEntity<DataResult<InstructorDto>> response = instructorController.create(instructorCreateDto);

        assertAll(
                () -> assertEquals(HttpStatus.CREATED, response.getStatusCode()),
                () -> assertEquals(instructorDto, Objects.requireNonNull(response.getBody()).getPayload())
        );
    }

    @Test
    void update_StatusNoContent() {

        long id = 1;
        InstructorUpdateDto instructorUpdateDto = new InstructorUpdateDto();
        instructorUpdateDto.setId(id);

        doNothing().when(instructorService).update(instructorUpdateDto);

        ResponseEntity<Result> response = instructorController.update(id, instructorUpdateDto);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void deleteById_StatusNoContent() {

        long id = 1;
        doNothing().when(instructorService).deleteById(id);

        ResponseEntity<Result> response = instructorController.deleteById(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}