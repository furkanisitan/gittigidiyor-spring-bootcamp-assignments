package dev.patika.schoolmanagementsystem.business.concretes;

import dev.patika.schoolmanagementsystem.business.InstructorService;
import dev.patika.schoolmanagementsystem.business.StudentService;
import dev.patika.schoolmanagementsystem.business.dtos.CourseCreateDto;
import dev.patika.schoolmanagementsystem.business.dtos.CourseDto;
import dev.patika.schoolmanagementsystem.business.dtos.CourseUpdateDto;
import dev.patika.schoolmanagementsystem.core.exceptions.CourseIsAlreadyExistException;
import dev.patika.schoolmanagementsystem.core.exceptions.EntityNotExistsException;
import dev.patika.schoolmanagementsystem.core.exceptions.ForeignKeyConstraintViolationException;
import dev.patika.schoolmanagementsystem.core.exceptions.StudentNumberForOneCourseExceededException;
import dev.patika.schoolmanagementsystem.dataaccess.CourseRepository;
import dev.patika.schoolmanagementsystem.entities.Course;
import dev.patika.schoolmanagementsystem.entities.CourseUtility;
import dev.patika.schoolmanagementsystem.entities.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourseManagerTest {

    @Mock
    CourseRepository mockCourseRepository;
    @Mock
    InstructorService mockInstructorService;
    @Mock
    StudentService mockStudentService;
    @Mock
    CourseUtility courseUtility;
    @InjectMocks
    CourseManager courseManager;

    @Test
    void findAll_ReturnNotEmptyList() {

        when(mockCourseRepository.findAll()).thenReturn(Collections.singletonList(new Course()));

        List<CourseDto> courseDtoList = courseManager.findAll();

        assertFalse(courseDtoList.isEmpty());
    }

    @Test
    void findById_ReturnCourseDtoWithId() {

        long id = 1;
        Course course = new Course();
        course.setId(id);
        when(mockCourseRepository.findById(id)).thenReturn(Optional.of(course));

        CourseDto courseDto = courseManager.findById(id);

        assertAll(
                () -> assertNotNull(courseDto),
                () -> assertEquals(id, courseDto.getId())
        );
    }

    @Test
    void create_ReturnCourseDto() {

        CourseCreateDto courseCreateDto = new CourseCreateDto();
        courseCreateDto.setCode("code");
        Course course = new Course();

        when(mockCourseRepository.existsByCode(courseCreateDto.getCode())).thenReturn(Boolean.FALSE);
        when(mockInstructorService.existsById(courseCreateDto.getInstructorId())).thenReturn(Boolean.TRUE);
        when(mockCourseRepository.save(any(Course.class))).thenReturn(course);


        CourseDto courseDto = courseManager.create(courseCreateDto);


        assertAll(
                () -> assertNotNull(courseDto),
                () -> assertEquals(course.getCode(), courseDto.getCode())
        );
    }

    @Test
    void create_ThrowCourseIsAlreadyExistException_CodeNotUnique() {

        CourseCreateDto courseCreateDto = new CourseCreateDto();

        when(mockCourseRepository.existsByCode(courseCreateDto.getCode())).thenReturn(Boolean.TRUE);

        assertThrows(CourseIsAlreadyExistException.class, () -> {
            courseManager.create(courseCreateDto);
        });
    }

    @Test
    void create_ThrowForeignKeyConstraintViolationException_InstructorIdNotExists() {

        CourseCreateDto courseCreateDto = new CourseCreateDto();

        when(mockInstructorService.existsById(courseCreateDto.getInstructorId())).thenReturn(Boolean.FALSE);

        assertThrows(ForeignKeyConstraintViolationException.class, () -> {
            courseManager.create(courseCreateDto);
        });
    }

    @Test
    void update_DoesNotThrowException() {

        CourseUpdateDto courseUpdateDto = new CourseUpdateDto();
        courseUpdateDto.setId(1L);
        courseUpdateDto.setCode("code");

        Course course = new Course();
        course.setId(1L);


        when(mockCourseRepository.existsById(courseUpdateDto.getId())).thenReturn(Boolean.TRUE);
        when(mockCourseRepository.getByCode(courseUpdateDto.getCode())).thenReturn(course);
        when(mockInstructorService.existsById(courseUpdateDto.getInstructorId())).thenReturn(Boolean.TRUE);
        when(mockCourseRepository.findById(courseUpdateDto.getId())).thenReturn(Optional.of(course));


        assertDoesNotThrow(() -> {
            courseManager.update(courseUpdateDto);
        });
    }

    @Test
    void update_ThrowEntityNotExistsException_IdNotExists() {

        CourseUpdateDto courseUpdateDto = new CourseUpdateDto();
        courseUpdateDto.setId(1L);

        when(mockCourseRepository.existsById(courseUpdateDto.getId())).thenReturn(Boolean.FALSE);

        assertThrows(EntityNotExistsException.class, () -> {
            courseManager.update(courseUpdateDto);
        });
    }


    @Test
    void update_ThrowCourseIsAlreadyExistException_CodeNotUnique() {

        CourseUpdateDto courseUpdateDto = new CourseUpdateDto();
        courseUpdateDto.setId(1L);

        Course otherCourse = new Course();
        otherCourse.setId(2L);

        when(mockCourseRepository.existsById(courseUpdateDto.getId())).thenReturn(Boolean.TRUE);
        when(mockCourseRepository.getByCode(courseUpdateDto.getCode())).thenReturn(otherCourse);

        assertThrows(CourseIsAlreadyExistException.class, () -> {
            courseManager.update(courseUpdateDto);
        });
    }

    @Test
    void update_ThrowForeignKeyConstraintViolationException_InstructorIdNotExists() {

        CourseUpdateDto courseUpdateDto = new CourseUpdateDto();

        Course course = new Course();

        when(mockCourseRepository.existsById(courseUpdateDto.getId())).thenReturn(Boolean.TRUE);
        when(mockCourseRepository.getByCode(courseUpdateDto.getCode())).thenReturn(course);
        when(mockInstructorService.existsById(courseUpdateDto.getInstructorId())).thenReturn(Boolean.FALSE);

        assertThrows(ForeignKeyConstraintViolationException.class, () -> {
            courseManager.update(courseUpdateDto);
        });
    }

    @Test
    void deleteById_DoesNotThrowException() {

        Course course = spy(new Course());

        when(mockCourseRepository.findById(anyLong())).thenReturn(Optional.of(course));
        when(course.utility()).thenReturn(courseUtility);

        assertDoesNotThrow(() -> {
            courseManager.deleteById(anyLong());
        });
    }

    @Test
    void deleteById_ThrowEntityNotExistsException_IdNotExists() {

        when(mockCourseRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotExistsException.class, () -> {
            courseManager.deleteById(anyLong());
        });
    }

    @Test
    void addStudent_DoesNotThrowException() {

        Long courseId = 1L, studentId = 1L;
        Course course = spy(new Course());
        Student student = new Student();

        when(mockCourseRepository.findById(courseId)).thenReturn(Optional.of(course));
        when(course.utility()).thenReturn(courseUtility);
        when(mockStudentService.existsById(studentId)).thenReturn(Boolean.TRUE);
        when(mockStudentService.getById(studentId)).thenReturn(student);

        assertDoesNotThrow(() -> {
            courseManager.addStudent(courseId, studentId);
        });
    }

    @Test
    void addStudent_ThrowEntityNotExistsException_CourseIdNotExists() {

        Long courseId = 1L;

        when(mockCourseRepository.findById(courseId)).thenReturn(Optional.empty());

        assertThrows(EntityNotExistsException.class, () -> {
            courseManager.addStudent(courseId, anyLong());
        });
    }

    @Test
    void addStudent_ThrowEntityNotExistsException_StudentIdNotExists() {

        Long courseId = 1L, studentId = 1L;
        Course course = spy(new Course());

        when(mockCourseRepository.findById(courseId)).thenReturn(Optional.of(course));
        when(mockStudentService.existsById(studentId)).thenReturn(Boolean.FALSE);

        assertThrows(EntityNotExistsException.class, () -> {
            courseManager.addStudent(courseId, studentId);
        });
    }

    @Test
    void addStudent_ThrowStudentNumberForOneCourseExceededException_StudentNumberGreaterThanOrEquals20() {

        Long courseId = 1L;
        Course course = spy(new Course());
        Set<Student> students = spy(new HashSet<>());

        when(mockCourseRepository.findById(courseId)).thenReturn(Optional.of(course));
        when(course.getStudents()).thenReturn(students);
        when(students.size()).thenReturn(20);

        assertThrows(StudentNumberForOneCourseExceededException.class, () -> {
            courseManager.addStudent(courseId, anyLong());
        });
    }
}