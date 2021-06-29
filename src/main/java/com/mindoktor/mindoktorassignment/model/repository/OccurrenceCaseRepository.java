package com.mindoktor.mindoktorassignment.model.repository;

import com.mindoktor.mindoktorassignment.model.entities.OccurrenceCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OccurrenceCaseRepository extends JpaRepository<OccurrenceCase, UUID> {

    @Query(value = "SELECT * FROM occurrence_case  where officer_id is null",nativeQuery = true)
    List<OccurrenceCase> findByOfficerIsNull();

    @Query
    List<OccurrenceCase> findByIncident_Id(UUID id);
}
