package com.mindoktor.mindoktorassignment.model.repository;

import com.mindoktor.mindoktorassignment.model.entities.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, UUID> {
}
