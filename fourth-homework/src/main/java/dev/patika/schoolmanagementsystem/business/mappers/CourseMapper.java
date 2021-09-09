package dev.patika.schoolmanagementsystem.business.mappers;

import dev.patika.schoolmanagementsystem.business.dtos.CourseCreateDto;
import dev.patika.schoolmanagementsystem.business.dtos.CourseDto;
import dev.patika.schoolmanagementsystem.business.dtos.CourseUpdateDto;
import dev.patika.schoolmanagementsystem.business.dtos.StudentUpdateDto;
import dev.patika.schoolmanagementsystem.entities.Course;
import dev.patika.schoolmanagementsystem.entities.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CourseMapper {

    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    List<CourseDto> toCourseDtoList(List<Course> courses);

    @Mapping(target = "instructorId", source = "instructor.id")
    CourseDto toCourseDto(Course course);

    @Mapping(target = "instructor.id", source = "instructorId")
    Course fromCourseCreateDto(CourseCreateDto courseCreateDto);

    void updateFromCourseUpdateDto(CourseUpdateDto courseUpdateDto, @MappingTarget Course course);

}
