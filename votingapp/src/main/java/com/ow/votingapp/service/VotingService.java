package com.ow.votingapp.service;

import com.ow.votingapp.exception.CandidateNotFoundException;
import com.ow.votingapp.exception.NoVotesYetException;
import com.ow.votingapp.exception.VoterAlreadyVotedException;
import com.ow.votingapp.exception.VoterNotFoundException;
import com.ow.votingapp.mapper.candidate.CandidateEntityToCandidateResponseMapper;
import com.ow.votingapp.model.entity.CandidateEntity;
import com.ow.votingapp.model.entity.VoteEntity;
import com.ow.votingapp.model.entity.VoterEntity;
import com.ow.votingapp.model.response.BaseCandidateResponse;
import com.ow.votingapp.repository.CandidateRepository;
import com.ow.votingapp.repository.VoteRepository;
import com.ow.votingapp.repository.VoterRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class VotingService {

    private CandidateEntityToCandidateResponseMapper candidateEntityToCandidateResponseMapper = CandidateEntityToCandidateResponseMapper.INSTANCE;

    private final VoteRepository voteRepository;
    private final CandidateRepository candidateRepository;
    private final VoterRepository voterRepository;

    public VotingService(VoteRepository voteRepository, CandidateRepository candidateRepository, VoterRepository voterRepository) {
        this.voteRepository = voteRepository;
        this.candidateRepository = candidateRepository;
        this.voterRepository = voterRepository;
    }

    public void castVote(@NotNull final UUID candidateId, @NotNull final UUID voterId) throws CandidateNotFoundException, VoterNotFoundException, VoterAlreadyVotedException {
        final CandidateEntity candidate = candidateRepository.findByIdentificationNumber(candidateId).orElseThrow(CandidateNotFoundException::new);
        final VoterEntity voter = voterRepository.findByIdentificationNumber(voterId).orElseThrow(VoterNotFoundException::new);
        if (voter.isVoted()) {
            throw new VoterAlreadyVotedException();
        }
        voter.setVoted(true);
        VoteEntity vote = VoteEntity.builder()
                .candidate(candidate)
                .voter(voter)
                .build();
        voteRepository.save(vote);
        voterRepository.save(voter);
    }

    public Map<BaseCandidateResponse, Long> calculateVotesPerCandidate() throws NoVotesYetException {
        List<VoteEntity> allVotes = voteRepository.findAll();
        if (allVotes.isEmpty()) {
            throw new NoVotesYetException();
        }
        return candidateRepository.findAll().stream()
                .collect(Collectors.toMap(
                        candidateEntityToCandidateResponseMapper::map,
                        candidate -> allVotes.stream()
                                .filter(vote -> vote.getCandidate().getIdentificationNumber().equals(candidate.getIdentificationNumber()))
                                .count()
                ));
    }
}
