package com.ow.votingapp.service;

import com.ow.votingapp.mapper.candidate.CandidateEntityToCreateCandidateResponseMapper;
import com.ow.votingapp.mapper.voter.VoterEntityToCreateVoterResponseMapper;
import com.ow.votingapp.model.dto.CreateCandidateDTO;
import com.ow.votingapp.model.dto.CreateVoterDTO;
import com.ow.votingapp.model.entity.CandidateEntity;
import com.ow.votingapp.model.entity.VoterEntity;
import com.ow.votingapp.model.response.BaseCandidateResponse;
import com.ow.votingapp.model.response.BaseVoterResponse;
import com.ow.votingapp.repository.CandidateRepository;
import com.ow.votingapp.repository.VoterRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    private CandidateEntityToCreateCandidateResponseMapper candidateEntityToCreateCandidateResponseMapper = CandidateEntityToCreateCandidateResponseMapper.INSTANCE;
    private VoterEntityToCreateVoterResponseMapper voterEntityToCreateVoterResponseMapper = VoterEntityToCreateVoterResponseMapper.INSTANCE;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private VoterRepository voterRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testCreateCandidate() {
        // given
        CreateCandidateDTO createCandidateDTO = new CreateCandidateDTO();
        CandidateEntity candidateEntity = new CandidateEntity();
        when(candidateRepository.save(any())).thenReturn(candidateEntity);

        // when
        BaseCandidateResponse result = userService.createCandidate(createCandidateDTO);

        // then
        verify(candidateRepository, times(1)).save(any());
        assertEquals(candidateEntityToCreateCandidateResponseMapper.map(candidateEntity), result);
    }

    @Test
    void testCreateVoter() {
        // given
        CreateVoterDTO createVoterDTO = new CreateVoterDTO();
        VoterEntity voterEntity = new VoterEntity();
        when(voterRepository.save(any())).thenReturn(voterEntity);

        // when
        BaseVoterResponse result = userService.createVoter(createVoterDTO);

        // then
        verify(voterRepository, times(1)).save(any());
        assertEquals(voterEntityToCreateVoterResponseMapper.map(voterEntity), result);
    }

    @Test
    void testGetAllCandidates() {
        // given
        List<CandidateEntity> candidateEntities = Arrays.asList(new CandidateEntity(), new CandidateEntity());
        when(candidateRepository.findAll()).thenReturn(candidateEntities);

        // when
        List<BaseCandidateResponse> result = userService.getAllCandidates();

        // then
        verify(candidateRepository, times(1)).findAll();
        assertEquals(candidateEntities.size(), result.size());
    }

    @Test
    void testGetAllVoters() {
        // given
        List<VoterEntity> voterEntities = Arrays.asList(new VoterEntity(), new VoterEntity());
        when(voterRepository.findAll()).thenReturn(voterEntities);

        // when
        List<BaseVoterResponse> result = userService.getAllVoters();

        // then
        verify(voterRepository, times(1)).findAll();
        assertEquals(voterEntities.size(), result.size());
    }
}
