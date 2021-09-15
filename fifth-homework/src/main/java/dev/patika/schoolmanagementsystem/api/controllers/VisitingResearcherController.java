package dev.patika.schoolmanagementsystem.api.controllers;

import dev.patika.schoolmanagementsystem.business.VisitingResearcherService;
import dev.patika.schoolmanagementsystem.business.dtos.InstructorSalaryLogDto;
import dev.patika.schoolmanagementsystem.core.results.DataResult;
import dev.patika.schoolmanagementsystem.core.results.Result;
import dev.patika.schoolmanagementsystem.core.utils.ResponseEntities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instructors/visiting-researchers")
public class VisitingResearcherController {

    private final VisitingResearcherService visitingResearcherService;

    @Autowired
    public VisitingResearcherController(VisitingResearcherService visitingResearcherService) {
        this.visitingResearcherService = visitingResearcherService;
    }

    @GetMapping("/{id}/hourly-salary/history")
    public ResponseEntity<DataResult<List<InstructorSalaryLogDto>>> getSalaryLogs(@PathVariable long id) {
        return ResponseEntities.okDataResult(visitingResearcherService.findAllSalaryLogsById(id));
    }

    @PutMapping("/{id}/hourly-salary/{percent}")
    public ResponseEntity<Result> update(@PathVariable long id, @PathVariable double percent) {

        visitingResearcherService.updateHourlySalary(id, percent);
        return ResponseEntity.noContent().build();
    }

}
