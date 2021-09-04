package dev.patika.schoolmanagementsystem.api.controllers;

import dev.patika.schoolmanagementsystem.api.helpers.DataResultResponseHelper;
import dev.patika.schoolmanagementsystem.business.abstracts.CourseService;
import dev.patika.schoolmanagementsystem.core.results.abstracts.DataResult;
import dev.patika.schoolmanagementsystem.core.results.abstracts.Result;
import dev.patika.schoolmanagementsystem.core.results.helpers.DataResultHelper;
import dev.patika.schoolmanagementsystem.entities.concretes.Course;
import dev.patika.schoolmanagementsystem.entities.concretes.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<DataResult<List<Course>>> getAll(@RequestParam(required = false) String name) {
        return ResponseEntity.ok(DataResultHelper.ok(courseService.findAllByNameContains(name)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResult<Course>> getById(@PathVariable long id) {

        Optional<Course> course = courseService.findById(id);

        return course.map(value -> ResponseEntity.ok(DataResultHelper.ok(value)))
                // return 404 if course does not exist
                .orElseGet(() -> DataResultResponseHelper.notFound(Student.class.getSimpleName(), Pair.of("id", id)));
    }

    @PostMapping
    public ResponseEntity<DataResult<Course>> create(@RequestBody Course course) {

        course.setId(null);
        Course createdCourse = courseService.create(course);

        // location header
        URI uri = MvcUriComponentsBuilder.fromMethodCall(
                on(CourseController.class).getById(createdCourse.getId())).buildAndExpand().toUri();

        return ResponseEntity.created(uri).body(DataResultHelper.ok(createdCourse));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> update(@PathVariable long id, @RequestBody Course course) {

        course.setId(id);
        courseService.update(course);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteById(@PathVariable long id) {

        courseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Result> deleteByName(@RequestParam String name) {

        courseService.deleteByName(name);
        return ResponseEntity.noContent().build();
    }
}
