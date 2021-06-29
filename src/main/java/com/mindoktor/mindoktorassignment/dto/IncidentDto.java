package com.mindoktor.mindoktorassignment.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class IncidentDto {

    private UUID id;

    private String name;

    private String lastname;

    private String vid;

    private boolean solved;

}
