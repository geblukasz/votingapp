package com.ow.votingapp.controller;

import com.ow.votingapp.mapper.candidate.CreateCandidateRequestToCreateCandidateDTOMapper;
import com.ow.votingapp.mapper.voter.CreateVoterRequestToCreateVoterDTOMapper;
import com.ow.votingapp.model.dto.CreateCandidateDTO;
import com.ow.votingapp.model.dto.CreateVoterDTO;
import com.ow.votingapp.model.request.CreateCandidateRequest;
import com.ow.votingapp.model.request.CreateVoterRequest;
import com.ow.votingapp.model.response.BaseCandidateResponse;
import com.ow.votingapp.model.response.BaseVoterResponse;
import com.ow.votingapp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/api/user")
public class UserController {

    private CreateCandidateRequestToCreateCandidateDTOMapper createCandidateRequestToCreateCandidateDTOMapper = CreateCandidateRequestToCreateCandidateDTOMapper.INSTANCE;
    private CreateVoterRequestToCreateVoterDTOMapper createVoterRequestToCreateVoterDTOMapper = CreateVoterRequestToCreateVoterDTOMapper.INSTANCE;

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/candidates")
    @Operation(summary = "Create candidate", description = "Create candidate with name")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Candidate created"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<BaseCandidateResponse> createCandidate(@RequestBody @Valid final CreateCandidateRequest createCandidateRequest) {
        final CreateCandidateDTO createCandidateDTO = createCandidateRequestToCreateCandidateDTOMapper.map(createCandidateRequest);
        final BaseCandidateResponse baseCandidateResponse = userService.createCandidate(createCandidateDTO);
        return ResponseEntity.ok(baseCandidateResponse);
    }

    @PostMapping("/voters")
    @Operation(summary = "Create voter", description = "Create voter with name")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Voter created"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<BaseVoterResponse> createVoter(@RequestBody @Valid final CreateVoterRequest createVoterRequest) {
        final CreateVoterDTO createVoterDTO = createVoterRequestToCreateVoterDTOMapper.map(createVoterRequest);
        final BaseVoterResponse createVoterResponse = userService.createVoter(createVoterDTO);
        return ResponseEntity.ok(createVoterResponse);
    }

    @GetMapping("/candidates")
    @Operation(summary = "Get all candidates", description = "Get all candidates")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Candidates retrieved"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<BaseCandidateResponse>> getAllCandidates() {
        final List<BaseCandidateResponse> candidates = userService.getAllCandidates();
        return ResponseEntity.ok(candidates);
    }

    @GetMapping("/voters")
    @Operation(summary = "Get all voters", description = "Get all voters")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Voters retrieved"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<BaseVoterResponse>> getAllVoters() {
        final List<BaseVoterResponse> voters = userService.getAllVoters();
        return ResponseEntity.ok(voters);
    }

}
