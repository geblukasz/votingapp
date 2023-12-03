package com.ow.votingapp.service;

import com.ow.votingapp.exception.CandidateNotFoundException;
import com.ow.votingapp.exception.NoVotesYetException;
import com.ow.votingapp.exception.VoterAlreadyVotedException;
import com.ow.votingapp.exception.VoterNotFoundException;
import com.ow.votingapp.model.entity.CandidateEntity;
import com.ow.votingapp.model.entity.VoteEntity;
import com.ow.votingapp.model.entity.VoterEntity;
import com.ow.votingapp.model.response.BaseCandidateResponse;
import com.ow.votingapp.repository.CandidateRepository;
import com.ow.votingapp.repository.VoteRepository;
import com.ow.votingapp.repository.VoterRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VotingServiceTest {

    @Mock
    private VoteRepository voteRepository;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private VoterRepository voterRepository;

    @InjectMocks
    private VotingService votingService;

    @Test
    void testCastVote() throws CandidateNotFoundException, VoterNotFoundException, VoterAlreadyVotedException {
        // given
        UUID candidateId = UUID.randomUUID();
        UUID voterId = UUID.randomUUID();

        CandidateEntity candidate = CandidateEntity.builder()
                .id(1L)
                .name("Candidate 1")
                .identificationNumber(candidateId)
                .build();

        VoterEntity voter = VoterEntity.builder()
                .id(1L)
                .name("Voter 1")
                .identificationNumber(voterId)
                .voted(false)
                .build();

        // when
        when(candidateRepository.findByIdentificationNumber(candidateId)).thenReturn(Optional.of(candidate));
        when(voterRepository.findByIdentificationNumber(voterId)).thenReturn(Optional.of(voter));
        when(voteRepository.save(any(VoteEntity.class))).thenReturn(mock(VoteEntity.class));
        when(voterRepository.save(any(VoterEntity.class))).thenReturn(mock(VoterEntity.class));

        votingService.castVote(candidateId, voterId);

        // then
        verify(voteRepository).save(any(VoteEntity.class));
        verify(voterRepository).save(eq(voter));
    }

    @Test
    void testCastVoteCandidateNotFound() {
        // given
        UUID candidateId = UUID.randomUUID();
        UUID voterId = UUID.randomUUID();

        // when
        when(candidateRepository.findByIdentificationNumber(candidateId)).thenReturn(Optional.empty());

        // then
        assertThrows(CandidateNotFoundException.class, () -> {
            votingService.castVote(candidateId, voterId);
        });
    }

    @Test
    void testCalculateVotesPerCandidate() throws NoVotesYetException {
        // given
        UUID voter1IdentificationNumber = UUID.randomUUID();
        UUID voter2IdentificationNumber = UUID.randomUUID();
        UUID candidate1IdentificationNumber = UUID.randomUUID();
        UUID candidate2IdentificationNumber = UUID.randomUUID();
        CandidateEntity candidate1 = CandidateEntity.builder()
                .id(1L)
                .identificationNumber(candidate1IdentificationNumber)
                .name("Candidate 1")
                .build();
        CandidateEntity candidate2 = CandidateEntity
                .builder()
                .id(2L)
                .identificationNumber(candidate2IdentificationNumber)
                .name("Candidate 2").build();
        VoterEntity voter1 = VoterEntity.builder()
                .id(1L)
                .identificationNumber(voter1IdentificationNumber)
                .name("Voter 1")
                .voted(true)
                .build();
        VoterEntity voter2 = VoterEntity.builder()
                .id(2L)
                .identificationNumber(voter2IdentificationNumber)
                .name("Voter 2")
                .voted(true)
                .build();
        List<CandidateEntity> candidates = Arrays.asList(candidate1, candidate2);
        List<VoteEntity> votes = Arrays.asList(
                VoteEntity.builder()
                        .id(1L)
                        .candidate(candidate1)
                        .voter(voter1)
                        .build(),
                VoteEntity.builder()
                        .id(2L)
                        .candidate(candidate1)
                        .voter(voter2)
                        .build()
        );

        // when
        when(voteRepository.findAll()).thenReturn(votes);
        when(candidateRepository.findAll()).thenReturn(candidates);
        Map<BaseCandidateResponse, Long> result = votingService.calculateVotesPerCandidate();

        // then
        assertEquals(2, result.size());
        assertEquals(2L, result.get(findByIdentificationNumber(result, candidate1IdentificationNumber)));
        assertEquals(0L, result.get(findByIdentificationNumber(result, candidate2IdentificationNumber)));
    }

    private BaseCandidateResponse findByIdentificationNumber(Map<BaseCandidateResponse, Long> result, UUID identificationNumber) {
        return result.keySet().stream()
                .filter(x -> x.getIdentificationNumber().equals(identificationNumber))
                .findAny()
                .orElseThrow();
    }

    @Test
    void testCalculateVotesPerCandidateNoVotes() {
        // when
        when(voteRepository.findAll()).thenReturn(Collections.emptyList());

        // then
        assertThrows(NoVotesYetException.class, () -> {
            votingService.calculateVotesPerCandidate();
        });
    }

}
