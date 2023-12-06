package com.ow.votingapp.controller;

import com.ow.votingapp.exception.CandidateNotFoundException;
import com.ow.votingapp.exception.NoVotesYetException;
import com.ow.votingapp.exception.VoterAlreadyVotedException;
import com.ow.votingapp.exception.VoterNotFoundException;
import com.ow.votingapp.model.response.BaseCandidateResponse;
import com.ow.votingapp.service.VotingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/votes")
public class VotingController {

    private final VotingService votingService;

    public VotingController(VotingService votingService) {
        this.votingService = votingService;
    }

    @PostMapping("/vote")
    public ResponseEntity<String> castVote(@RequestHeader(defaultValue = "6f323a5b-083a-42f7-a775-2fe75156bf4e") final UUID candidateId,
                                           @RequestHeader(defaultValue = "6d3b4a1e-59c2-451c-b8b7-861b2b7b9419") final UUID voterId)
            throws VoterNotFoundException, CandidateNotFoundException, VoterAlreadyVotedException {
        votingService.castVote(candidateId, voterId);
        return ResponseEntity.ok("Vote cast successfully");
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<BaseCandidateResponse, Long>> calculateVotesPerCandidate() throws NoVotesYetException {
        final Map<BaseCandidateResponse, Long> votesPerCandidate = votingService.calculateVotesPerCandidate();
        return ResponseEntity.ok(votesPerCandidate);
    }

}
