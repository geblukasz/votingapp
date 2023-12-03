package com.ow.votingapp.service;

import com.ow.votingapp.model.response.BaseCandidateResponse;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class VotingService {

    public void castVote(UUID candidateId, UUID voterId) {

    }

    public Map<BaseCandidateResponse, Long> calculateVotesPerCandidate() {
        return null;
    }
}
