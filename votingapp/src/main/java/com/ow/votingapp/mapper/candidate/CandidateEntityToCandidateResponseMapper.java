package com.ow.votingapp.mapper.candidate;

import com.ow.votingapp.model.entity.CandidateEntity;
import com.ow.votingapp.model.response.BaseCandidateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CandidateEntityToCandidateResponseMapper {

    CandidateEntityToCandidateResponseMapper INSTANCE = Mappers.getMapper(CandidateEntityToCandidateResponseMapper.class);

    BaseCandidateResponse map(CandidateEntity candidateEntity);

}
