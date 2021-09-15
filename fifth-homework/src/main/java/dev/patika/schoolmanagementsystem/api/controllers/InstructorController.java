package dev.patika.schoolmanagementsystem.api.controllers;

import dev.patika.schoolmanagementsystem.business.InstructorService;
import dev.patika.schoolmanagementsystem.business.criteria.InstructorCriteria;
import dev.patika.schoolmanagementsystem.business.dtos.InstructorCreateDto;
import dev.patika.schoolmanagementsystem.business.dtos.InstructorDto;
import dev.patika.schoolmanagementsystem.business.dtos.InstructorUpdateDto;
import dev.patika.schoolmanagementsystem.core.results.DataResult;
import dev.patika.schoolmanagementsystem.core.results.Result;
import dev.patika.schoolmanagementsystem.core.utils.ResponseEntities;
import dev.patika.schoolmanagementsystem.entities.Instructor;
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
    public ResponseEntity<DataResult<List<? extends InstructorDto>>> getAll(@RequestParam Optional<String> filter, InstructorCriteria criteria) {
        return ResponseEntities.okDataResult(instructorService.findAll(filter.orElse(null), criteria));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResult<InstructorDto>> getById(@PathVariable long id) {

        InstructorDto instructorDto = instructorService.findById(id);
        return instructorDto == null ?
                ResponseEntities.notFoundDataResult(Instructor.class.getSimpleName(), Pair.of("id", id)) :
                ResponseEntities.okDataResult(instructorDto);
    }

    @PostMapping
    public ResponseEntity<DataResult<InstructorDto>> create(@RequestBody InstructorCreateDto instructorCreateDto) {

        InstructorDto instructorDto = instructorService.create(instructorCreateDto);

        // location header
        URI uri = MvcUriComponentsBuilder.fromMethodCall(
                on(InstructorController.class).getById(instructorDto.getId())).buildAndExpand().toUri();

        return ResponseEntities.createdDataResult(instructorDto, uri);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> update(@PathVariable long id, @RequestBody InstructorUpdateDto instructorUpdateDto) {

        instructorUpdateDto.setId(id);
        instructorService.update(instructorUpdateDto);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteById(@PathVariable long id) {

        instructorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Result> deleteAllByName(@RequestParam String name) {

        instructorService.deleteAllByName(name);
        return ResponseEntity.noContent().build();
    }

}
