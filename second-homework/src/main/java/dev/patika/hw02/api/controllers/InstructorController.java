package dev.patika.hw02.api.controllers;

import dev.patika.hw02.api.helpers.DataResultResponseHelper;
import dev.patika.hw02.api.helpers.ResultResponseHelper;
import dev.patika.hw02.business.abstracts.InstructorService;
import dev.patika.hw02.core.results.abstracts.DataResult;
import dev.patika.hw02.core.results.abstracts.Result;
import dev.patika.hw02.core.results.helpers.DataResultHelper;
import dev.patika.hw02.entities.concretes.Instructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;
import java.util.List;

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
    public ResponseEntity<DataResult<List<Instructor>>> getAll() {
        return ResponseEntity.ok(DataResultHelper.ok(instructorService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResult<Instructor>> getById(@PathVariable long id) {

        Instructor instructor = instructorService.findById(id);

        return instructor != null ?
                ResponseEntity.ok(DataResultHelper.ok(instructor)) :

                // return 404 if instructor does not exist
                DataResultResponseHelper.notFound(Instructor.class.getSimpleName(), Pair.of("id", id));
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
    public ResponseEntity<Result> delete(@PathVariable long id) {

        Instructor existsInstructor = instructorService.findById(id);

        // return 404 if instructor does not exist
        if (existsInstructor == null)
            return ResultResponseHelper.notFound(Instructor.class.getSimpleName(), Pair.of("id", id));

        instructorService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<DataResult<Instructor>> getResponseWithLocation(Instructor instructor) {

        // location header
        URI uri = MvcUriComponentsBuilder.fromMethodCall(
                on(InstructorController.class).getById(instructor.getId())).buildAndExpand().toUri();

        return ResponseEntity.created(uri).body(DataResultHelper.ok(instructor));
    }

}

