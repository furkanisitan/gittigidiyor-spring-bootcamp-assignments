package dev.patika.hw02.api.controllers;

import dev.patika.hw02.api.helpers.DataResultResponseHelper;
import dev.patika.hw02.api.helpers.ResultResponseHelper;
import dev.patika.hw02.business.abstracts.StudentService;
import dev.patika.hw02.core.results.abstracts.DataResult;
import dev.patika.hw02.core.results.abstracts.Result;
import dev.patika.hw02.core.results.helpers.DataResultHelper;
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
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<DataResult<List<Student>>> getAll() {
        return ResponseEntity.ok(DataResultHelper.ok(studentService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResult<Student>> getById(@PathVariable long id) {

        Student student = studentService.findById(id);

        return student != null ?
                ResponseEntity.ok(DataResultHelper.ok(student)) :

                // return 404 if student does not exist
                DataResultResponseHelper.notFound(Student.class.getSimpleName(), Pair.of("id", id));
    }

    @PostMapping
    public ResponseEntity<DataResult<Student>> create(@RequestBody Student student) {

        student.setId(null);
        Student createdStudent = studentService.create(student);

        // location header
        URI uri = MvcUriComponentsBuilder.fromMethodCall(
                on(StudentController.class).getById(createdStudent.getId())).buildAndExpand().toUri();

        return ResponseEntity.created(uri).body(DataResultHelper.ok(createdStudent));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> update(@PathVariable long id, @RequestBody Student student) {

        Student existStudent = studentService.findById(id);

        // return 404 if student does not exist
        if (existStudent == null)
            return ResultResponseHelper.notFound(Student.class.getSimpleName(), Pair.of("id", id));

        student.setId(id);
        studentService.update(student);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> delete(@PathVariable long id) {

        Student existStudent = studentService.findById(id);

        // return 404 if student does not exist
        if (existStudent == null)
            return ResultResponseHelper.notFound(Student.class.getSimpleName(), Pair.of("id", id));

        studentService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
