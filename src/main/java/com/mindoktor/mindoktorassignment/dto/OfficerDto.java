package com.mindoktor.mindoktorassignment.dto;

import lombok.Data;


import java.util.UUID;

@Data
public class OfficerDto {

    private UUID id;

    private String name;

    private String lastname;

    private boolean assigned;

}
