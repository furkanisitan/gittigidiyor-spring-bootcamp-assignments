package dev.patika.hw02.api.controllers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.patika.hw02.api.helpers.DataResultResponseHelper;
import dev.patika.hw02.business.abstracts.CourseService;
import dev.patika.hw02.core.results.abstracts.DataResult;
import dev.patika.hw02.core.results.abstracts.Result;
import dev.patika.hw02.core.results.helpers.DataResultHelper;
import dev.patika.hw02.entities.concretes.Course;
import dev.patika.hw02.entities.concretes.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;
import java.util.List;

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
    public ResponseEntity<DataResult<List<Course>>> getAll() {
        return ResponseEntity.ok(DataResultHelper.ok(courseService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResult<Course>> getById(@PathVariable long id) {

        Course course = courseService.findById(id);

        return course != null ?
                ResponseEntity.ok(DataResultHelper.ok(course)) :

                // return 404 if course does not exist
                DataResultResponseHelper.notFound(Student.class.getSimpleName(), Pair.of("id", id));
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
    public ResponseEntity<Result> delete(@PathVariable long id) {

        courseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
