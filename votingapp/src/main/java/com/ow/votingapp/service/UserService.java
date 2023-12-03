package com.ow.votingapp.service;

import com.ow.votingapp.model.dto.CreateCandidateDTO;
import com.ow.votingapp.model.dto.CreateVoterDTO;
import com.ow.votingapp.model.response.BaseCandidateResponse;
import com.ow.votingapp.model.response.BaseVoterResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    public BaseCandidateResponse createCandidate(CreateCandidateDTO createCandidateDTO) {
        return null;
    }

    public BaseVoterResponse createVoter(CreateVoterDTO createVoterDTO) {
        return null;
    }

    public List<BaseCandidateResponse> getAllCandidates() {
        return null;
    }

    public List<BaseVoterResponse> getAllVoters() {
        return null;
    }
}
