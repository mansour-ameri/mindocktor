package com.mindoktor.mindoktorassignment.model.repository;

import com.mindoktor.mindoktorassignment.model.entities.Officer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OfficerRepository  extends JpaRepository<Officer, UUID> {

    @Query
    List<Officer> findByAssignedFalse();
}
