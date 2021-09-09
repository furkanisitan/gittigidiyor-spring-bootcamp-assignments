package dev.patika.schoolmanagementsystem.api.controllers;

import dev.patika.schoolmanagementsystem.business.ExceptionLogService;
import dev.patika.schoolmanagementsystem.business.dtos.CourseDto;
import dev.patika.schoolmanagementsystem.business.dtos.ExceptionLogDto;
import dev.patika.schoolmanagementsystem.core.results.DataResult;
import dev.patika.schoolmanagementsystem.core.utils.ResponseEntities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/exception-logs")
public class ExceptionLogController {

    private final ExceptionLogService exceptionLogService;

    @Autowired
    public ExceptionLogController(ExceptionLogService exceptionLogService) {
        this.exceptionLogService = exceptionLogService;
    }

    @GetMapping
    public ResponseEntity<DataResult<List<ExceptionLogDto>>> getAll(@RequestParam Optional<String> filter) {
        return ResponseEntities.okDataResult(exceptionLogService.findAll(filter.orElse(null)));
    }

}
