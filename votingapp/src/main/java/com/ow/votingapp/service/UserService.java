package com.ow.votingapp.service;

import com.ow.votingapp.mapper.candidate.CandidateEntityToCandidateResponseMapper;
import com.ow.votingapp.mapper.candidate.CandidateEntityToCreateCandidateResponseMapper;
import com.ow.votingapp.mapper.candidate.CreateCandidateDTOToCandidateEntityMapper;
import com.ow.votingapp.mapper.voter.CreateVoterDTOToVoterEntityMapper;
import com.ow.votingapp.mapper.voter.VoterEntityToCreateVoterResponseMapper;
import com.ow.votingapp.mapper.voter.VoterEntityToVoterResponseMapper;
import com.ow.votingapp.model.dto.CreateCandidateDTO;
import com.ow.votingapp.model.dto.CreateVoterDTO;
import com.ow.votingapp.model.entity.CandidateEntity;
import com.ow.votingapp.model.entity.VoterEntity;
import com.ow.votingapp.model.response.BaseCandidateResponse;
import com.ow.votingapp.model.response.BaseVoterResponse;
import com.ow.votingapp.repository.CandidateRepository;
import com.ow.votingapp.repository.VoterRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    private CreateCandidateDTOToCandidateEntityMapper createCandidateRequestToCreateCandidateDTOMapper = CreateCandidateDTOToCandidateEntityMapper.INSTANCE;
    private CreateVoterDTOToVoterEntityMapper createVoterDTOToVoterEntityMapper = CreateVoterDTOToVoterEntityMapper.INSTANCE;
    private CandidateEntityToCreateCandidateResponseMapper candidateEntityToCreateCandidateResponseMapper = CandidateEntityToCreateCandidateResponseMapper.INSTANCE;
    private CandidateEntityToCandidateResponseMapper candidateEntityToCandidateResponseMapper = CandidateEntityToCandidateResponseMapper.INSTANCE;
    private VoterEntityToCreateVoterResponseMapper voterEntityToCreateVoterResponseMapper = VoterEntityToCreateVoterResponseMapper.INSTANCE;
    private VoterEntityToVoterResponseMapper voterEntityToVoterResponseMapper = VoterEntityToVoterResponseMapper.INSTANCE;

    private final CandidateRepository candidateRepository;
    private final VoterRepository voterRepository;

    public UserService(CandidateRepository candidateRepository, VoterRepository voterRepository) {
        this.candidateRepository = candidateRepository;
        this.voterRepository = voterRepository;
    }


    public BaseCandidateResponse createCandidate(@NotNull final CreateCandidateDTO createCandidateDTO) {
        final CandidateEntity candidate = createCandidateRequestToCreateCandidateDTOMapper.map(createCandidateDTO);
        return candidateEntityToCreateCandidateResponseMapper.map(candidateRepository.save(candidate));
    }

    public BaseVoterResponse createVoter(@NotNull final CreateVoterDTO createVoterDTO) {
        final VoterEntity voter = createVoterDTOToVoterEntityMapper.map(createVoterDTO);
        return voterEntityToCreateVoterResponseMapper.map(voterRepository.save(voter));
    }

    public List<BaseCandidateResponse> getAllCandidates() {
        return candidateRepository.findAll().stream()
                .map(candidateEntityToCandidateResponseMapper::map)
                .toList();

    }

    public List<BaseVoterResponse> getAllVoters() {
        return voterEntityToVoterResponseMapper.map(voterRepository.findAll());
    }
}
