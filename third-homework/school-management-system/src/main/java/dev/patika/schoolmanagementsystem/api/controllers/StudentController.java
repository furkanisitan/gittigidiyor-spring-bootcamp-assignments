package dev.patika.schoolmanagementsystem.api.controllers;

import dev.patika.schoolmanagementsystem.api.helpers.DataResultResponseHelper;
import dev.patika.schoolmanagementsystem.business.abstracts.StudentService;
import dev.patika.schoolmanagementsystem.core.results.abstracts.DataResult;
import dev.patika.schoolmanagementsystem.core.results.abstracts.Result;
import dev.patika.schoolmanagementsystem.core.results.helpers.DataResultHelper;
import dev.patika.schoolmanagementsystem.entities.concretes.Student;
import dev.patika.schoolmanagementsystem.entities.dtos.student.GenderCount;
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
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<DataResult<List<Student>>> getAll(@RequestParam(required = false) String name) {
        return ResponseEntity.ok(DataResultHelper.ok(studentService.findAllByNameContains(name)));
    }

    @GetMapping("/genders")
    public ResponseEntity<DataResult<List<GenderCount>>> getAllGenders() {
        return ResponseEntity.ok(DataResultHelper.ok(studentService.findGenderCounts()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResult<Student>> getById(@PathVariable long id) {

        Optional<Student> student = studentService.findById(id);

        return student.map(value -> ResponseEntity.ok(DataResultHelper.ok(value)))
                // return 404 if student does not exist
                .orElseGet(() -> DataResultResponseHelper.notFound(Student.class.getSimpleName(), Pair.of("id", id)));
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

        student.setId(id);
        studentService.update(student);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteById(@PathVariable long id) {

        studentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Result> deleteByName(@RequestParam String name) {

        studentService.deleteByName(name);
        return ResponseEntity.noContent().build();
    }

}
