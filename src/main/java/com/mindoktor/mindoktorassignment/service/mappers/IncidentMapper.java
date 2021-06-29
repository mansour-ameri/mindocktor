package com.mindoktor.mindoktorassignment.service.mappers;

import com.mindoktor.mindoktorassignment.dto.IncidentDto;
import com.mindoktor.mindoktorassignment.model.entities.Incident;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IncidentMapper {
    private final ModelMapper mapper;

    @Autowired
    public IncidentMapper(ModelMapper mapper){
        this.mapper = mapper;
    }

    public IncidentDto mapToDto(Incident entity){
        return mapper.map(entity, IncidentDto.class);
    }

    public Incident mapToEntity(IncidentDto dto){
        return mapper.map(dto, Incident.class);
    }
}
