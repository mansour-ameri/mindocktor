package com.mindoktor.mindoktorassignment.controller;

import com.mindoktor.mindoktorassignment.dto.IncidentDto;
import com.mindoktor.mindoktorassignment.dto.OfficerDto;
import com.mindoktor.mindoktorassignment.service.IncidentHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class IncidentController {

    private final IncidentHandler incidentHandler;

    @Autowired
    public IncidentController(IncidentHandler handler){
        this.incidentHandler = handler;
    }

    @Operation(summary = "Register new incident")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "incident register",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = IncidentDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid body supplied",
                    content = @Content) })
    @PostMapping("/incidents")
    @PreAuthorize("hasAuthority('incident:write')")
    public ResponseEntity<?> createIncident(@RequestBody IncidentDto incidentDto){
        incidentDto = incidentHandler.addNewIncident(incidentDto);
        return new ResponseEntity<>(incidentDto, HttpStatus.CREATED);
    }

    @Operation(summary = "Mark case as solved")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "case solved",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "Invalid body supplied")})
    @PatchMapping("/incidents/{id}")
    @PreAuthorize("hasAnyAuthority('incident:mark')")
    public ResponseEntity<?> caseSolved(@PathVariable UUID id){
        incidentHandler.caseSolved(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED );
    }

    @Operation(summary = "Get all incidents")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "return all incidents",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Invalid body supplied")})
    @GetMapping("/incidents")
    @PreAuthorize("hasAnyAuthority('report:read')")
    public ResponseEntity<?> getAllIncidents(@RequestParam(name = "max",defaultValue = "4" ) int max,
                                             @RequestParam(name = "page",defaultValue = "0") int page){
        List<IncidentDto> incidentDtoList = incidentHandler.getAllIncidents(max,page);
        return new ResponseEntity<>(incidentDtoList,HttpStatus.OK);
    }
}
