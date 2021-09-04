package dev.patika.schoolmanagementsystem.api.controllers;

import dev.patika.schoolmanagementsystem.api.helpers.DataResultResponseHelper;
import dev.patika.schoolmanagementsystem.business.abstracts.InstructorService;
import dev.patika.schoolmanagementsystem.business.filtercriterias.InstructorCriteria;
import dev.patika.schoolmanagementsystem.core.results.abstracts.DataResult;
import dev.patika.schoolmanagementsystem.core.results.abstracts.Result;
import dev.patika.schoolmanagementsystem.core.results.helpers.DataResultHelper;
import dev.patika.schoolmanagementsystem.entities.concretes.Instructor;
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
@RequestMapping("/api/instructors")
public class InstructorController {

    private final InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping
    public ResponseEntity<DataResult<List<? extends Instructor>>> getAll(InstructorCriteria criteria) {

        return ResponseEntity.ok(DataResultHelper.ok(instructorService.findAll(criteria)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResult<Instructor>> getById(@PathVariable long id) {

        Optional<Instructor> instructor = instructorService.findById(id);

        return instructor.map(value -> ResponseEntity.ok(DataResultHelper.ok(value)))
                // return 404 if instructor is not exist
                .orElseGet(() -> DataResultResponseHelper.notFound(Instructor.class.getSimpleName(), Pair.of("id", id)));
    }

    @PostMapping
    public ResponseEntity<DataResult<Instructor>> create(@RequestBody Instructor instructor) {

        instructor.setId(null);
        Instructor createdInstructor = instructorService.create(instructor);

        return getResponseWithLocation(createdInstructor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> update(@PathVariable long id, @RequestBody Instructor instructor) {

        instructor.setId(id);
        instructorService.update(instructor);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteById(@PathVariable long id) {

        instructorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Result> deleteByName(@RequestParam String name) {

        instructorService.deleteByName(name);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<DataResult<Instructor>> getResponseWithLocation(Instructor instructor) {

        // location header
        URI uri = MvcUriComponentsBuilder.fromMethodCall(
                on(InstructorController.class).getById(instructor.getId())).buildAndExpand().toUri();

        return ResponseEntity.created(uri).body(DataResultHelper.ok(instructor));
    }

}

