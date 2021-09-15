package dev.patika.schoolmanagementsystem.api.controllers;

import dev.patika.schoolmanagementsystem.business.PermanentInstructorService;
import dev.patika.schoolmanagementsystem.business.dtos.InstructorSalaryLogDto;
import dev.patika.schoolmanagementsystem.core.results.DataResult;
import dev.patika.schoolmanagementsystem.core.results.Result;
import dev.patika.schoolmanagementsystem.core.utils.ResponseEntities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instructors/permanent-instructors")
public class PermanentInstructorController {

    private final PermanentInstructorService permanentInstructorService;

    @Autowired
    public PermanentInstructorController(PermanentInstructorService permanentInstructorService) {
        this.permanentInstructorService = permanentInstructorService;
    }

    @GetMapping("/{id}/fixed-salary/history")
    public ResponseEntity<DataResult<List<InstructorSalaryLogDto>>> getSalaryLogs(@PathVariable long id) {
        return ResponseEntities.okDataResult(permanentInstructorService.findAllSalaryLogsById(id));
    }

    @PutMapping("/{id}/fixed-salary/{percent}")
    public ResponseEntity<Result> update(@PathVariable long id, @PathVariable double percent) {

        permanentInstructorService.updateFixedSalary(id, percent);
        return ResponseEntity.noContent().build();
    }


}
