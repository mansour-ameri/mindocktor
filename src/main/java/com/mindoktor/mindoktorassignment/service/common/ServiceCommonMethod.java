package com.mindoktor.mindoktorassignment.service.common;

import com.mindoktor.mindoktorassignment.model.entities.OccurrenceCase;
import com.mindoktor.mindoktorassignment.model.entities.Officer;
import com.mindoktor.mindoktorassignment.model.repository.OccurrenceCaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceCommonMethod {
    private final OccurrenceCaseRepository occurrenceCaseRepository;

    public ServiceCommonMethod(OccurrenceCaseRepository occurrenceCaseRepository) {
        this.occurrenceCaseRepository = occurrenceCaseRepository;
    }

    public void assignOfficerToCase(Officer officer) {
        List<OccurrenceCase> caseWithNoOfficer = occurrenceCaseRepository.findByOfficerIsNull();
        if(caseWithNoOfficer.size() > 0 ){
            officer.setAssigned(true);
            caseWithNoOfficer.get(0).setOfficer(officer);
            occurrenceCaseRepository.save(caseWithNoOfficer.get(0));
        }
    }
}
