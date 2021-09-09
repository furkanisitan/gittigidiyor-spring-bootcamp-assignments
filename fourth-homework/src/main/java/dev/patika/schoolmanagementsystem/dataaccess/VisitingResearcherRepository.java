package dev.patika.schoolmanagementsystem.dataaccess;

import dev.patika.schoolmanagementsystem.entities.VisitingResearcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitingResearcherRepository extends JpaRepository<VisitingResearcher, Long> {
}
