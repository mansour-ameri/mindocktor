package com.mindoktor.mindoktorassignment.service.mappers;

import com.mindoktor.mindoktorassignment.dto.OfficerDto;
import com.mindoktor.mindoktorassignment.model.entities.Officer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OfficerMapper {

    private final ModelMapper mapper;

    @Autowired
    public OfficerMapper(ModelMapper mapper){
        this.mapper = mapper;
    }

    public OfficerDto mapToDto(Officer entity){
        return mapper.map(entity, OfficerDto.class);
    }

    public Officer mapToEntity(OfficerDto dto){
        return mapper.map(dto, Officer.class);
    }
}
