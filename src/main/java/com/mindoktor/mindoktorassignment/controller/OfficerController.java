package com.mindoktor.mindoktorassignment.controller;

import com.mindoktor.mindoktorassignment.dto.OfficerDto;
import com.mindoktor.mindoktorassignment.service.OfficerHandler;
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

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class OfficerController {


    private final OfficerHandler officerHandler;

    @Autowired
    public  OfficerController(OfficerHandler handler){
        this.officerHandler = handler;
    }

    @Operation(summary = "Register new officer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "officer register",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OfficerDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid body supplied",
                    content = @Content) })
    @PostMapping("/officers")
    @PreAuthorize("hasAuthority('officer:write')")
    ResponseEntity<?> addOfficer(@RequestBody OfficerDto officerDto){
        officerDto = officerHandler.addOfficer(officerDto);
        return new ResponseEntity<>(officerDto, HttpStatus.CREATED);
    }

    @Operation(summary = "Update officer by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "officer details updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OfficerDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid body supplied",
                    content = @Content) })
    @PutMapping("/officers/{id}")
    @PreAuthorize("hasAuthority('officer:write')")
    ResponseEntity<?> updateOfficerDetails(@RequestBody OfficerDto officerDto, @PathVariable UUID id){
        officerHandler.updateOfficerDetails(id, officerDto);
        return new ResponseEntity<>(officerDto, HttpStatus.CREATED);
    }

    @Operation(summary = "Delete officer by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "officer deleted",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Invalid body supplied")})
    @DeleteMapping("/officers/{id}")
    @PreAuthorize("hasAuthority('officer:write')")
    ResponseEntity<?> removeOfficer(@PathVariable UUID id){
       officerHandler.removeOfficer(id);
        return new ResponseEntity<>("Resource removed",HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Get all officers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "return all officer",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Invalid body supplied")})
    @GetMapping("/officers")
    @PreAuthorize("hasAuthority('officer:read')")
    ResponseEntity<?> getAllOfficers(){
        return new ResponseEntity<>(officerHandler.getAllOfficers(),HttpStatus.OK);
    }

}
