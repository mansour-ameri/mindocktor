package com.mindoktor.mindoktorassignment.service;

import com.mindoktor.mindoktorassignment.dto.OfficerDto;
import com.mindoktor.mindoktorassignment.model.entities.OccurrenceCase;
import com.mindoktor.mindoktorassignment.model.entities.Officer;
import com.mindoktor.mindoktorassignment.model.repository.OccurrenceCaseRepository;
import com.mindoktor.mindoktorassignment.model.repository.OfficerRepository;
import com.mindoktor.mindoktorassignment.service.common.ServiceCommonMethod;
import com.mindoktor.mindoktorassignment.service.mappers.OfficerMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OfficerHandler {

    private final OfficerRepository officerRepository;
    private final OccurrenceCaseRepository occurrenceCaseRepository;
    private final OfficerMapper mapper;
    private final ServiceCommonMethod common;

    @Autowired
    public OfficerHandler(OfficerRepository officerRepository,
                          OfficerMapper mapper,
                          OccurrenceCaseRepository occurrenceCaseRepository,
                          ServiceCommonMethod common){
        this.mapper = mapper;
        this.common = common;
        this.officerRepository = officerRepository;
        this.occurrenceCaseRepository = occurrenceCaseRepository;
    }

    public List<OfficerDto> getAllOfficers(){
        List<Officer> officers = officerRepository.findAll();
        return officers.stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    public OfficerDto addOfficer(OfficerDto officerDto){
        Officer officer = officerRepository.save(mapper.mapToEntity(officerDto));
        common.assignOfficerToCase(officer);
        return mapper.mapToDto(officer);
    }



    public boolean removeOfficer(UUID id){
        if(officerRepository.existsById(id)) {
            if(officerRepository.findById(id).isPresent() &!officerRepository.findById(id).get().isAssigned())
                officerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public OfficerDto updateOfficerDetails(UUID id, OfficerDto officerDto){
       Optional<Officer> optionalOfficer = officerRepository.findById(id);
       if(optionalOfficer.isPresent()) {
           Officer officer = optionalOfficer.get();
           officer.setName(officerDto.getName());
           officer.setLastname(officerDto.getLastname());
           officer.setAssigned(officerDto.isAssigned());
           officerRepository.save(officer);
           return mapper.mapToDto(officer);
       }
       return new OfficerDto();
    }


}
