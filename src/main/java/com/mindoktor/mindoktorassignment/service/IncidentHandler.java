package com.mindoktor.mindoktorassignment.service;

import com.mindoktor.mindoktorassignment.dto.IncidentDto;
import com.mindoktor.mindoktorassignment.model.entities.Incident;
import com.mindoktor.mindoktorassignment.model.entities.OccurrenceCase;
import com.mindoktor.mindoktorassignment.model.entities.Officer;
import com.mindoktor.mindoktorassignment.model.repository.IncidentRepository;
import com.mindoktor.mindoktorassignment.model.repository.OccurrenceCaseRepository;
import com.mindoktor.mindoktorassignment.model.repository.OfficerRepository;
import com.mindoktor.mindoktorassignment.service.common.ServiceCommonMethod;
import com.mindoktor.mindoktorassignment.service.mappers.IncidentMapper;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class IncidentHandler {

    private final OccurrenceCaseRepository occurrenceCaseRepository;
    private final IncidentRepository incidentRepository;
    private final OfficerRepository officerRepository;
    private final IncidentMapper mapper;
    private final ServiceCommonMethod common;

    @Autowired
    public IncidentHandler(IncidentRepository incidentRepository,
                           IncidentMapper mapper,
                           ServiceCommonMethod common,
                           OfficerRepository officerRepository,
                           OccurrenceCaseRepository occurrenceCaseRepository){
        this.incidentRepository = incidentRepository;
        this.officerRepository = officerRepository;
        this.common = common;
        this.occurrenceCaseRepository = occurrenceCaseRepository;
        this.mapper = mapper;
    }

    public IncidentDto addNewIncident(IncidentDto incidentDto){
        OccurrenceCase occurrence = new OccurrenceCase();
        Incident incident = this.incidentRepository.save(mapper.mapToEntity(incidentDto));
        occurrence.setIncident(incident);
        List<Officer> unassignedOfficers = officerRepository.findByAssignedFalse();
        if (unassignedOfficers.size() > 0 ){
            unassignedOfficers.get(0).setAssigned(true);
            occurrence.setOfficer(unassignedOfficers.get(0));
            officerRepository.save(unassignedOfficers.get(0));
        }

        occurrenceCaseRepository.save(occurrence);
        return mapper.mapToDto(incident);
    }

    public void caseSolved(UUID id){
        if(incidentRepository.existsById(id)) {
            Incident incident = incidentRepository.getById(id);
            incident.setSolved(true);
            incidentRepository.save(incident);

            List<OccurrenceCase> occurrenceCase = occurrenceCaseRepository.findByIncident_Id(id);

            Officer officer = officerRepository.getById(occurrenceCase.get(0).getOfficer().getId());

            officer.setAssigned(false);

            officerRepository.save(officer);

            common.assignOfficerToCase(officer);
        }else {
            throw new EmptyResultDataAccessException(1);
        }
    }

    public List<IncidentDto> getAllIncidents(int max, int page){
        Pageable secondPageWithFiveElements = PageRequest.of(page, max);
        Page<Incident> incidents = incidentRepository.findAll(secondPageWithFiveElements);
        return incidents.stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }


}
