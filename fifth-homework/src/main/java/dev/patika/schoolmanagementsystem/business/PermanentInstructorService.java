package dev.patika.schoolmanagementsystem.business;

import dev.patika.schoolmanagementsystem.business.dtos.InstructorSalaryLogDto;

import java.util.List;

public interface PermanentInstructorService {

    /**
     * Increases or decreases the salary as a percentage.
     *
     * @param id      the primary key of the entity.
     * @param percent increase/decrease percentage.
     */
    void updateFixedSalary(Long id, double percent);

    List<InstructorSalaryLogDto> findAllSalaryLogsById(Long id);

}
