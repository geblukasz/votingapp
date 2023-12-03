package com.ow.votingapp.mapper.candidate;

import com.ow.votingapp.model.dto.CreateCandidateDTO;
import com.ow.votingapp.model.request.CreateCandidateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CreateCandidateRequestToCreateCandidateDTOMapper {


    CreateCandidateRequestToCreateCandidateDTOMapper INSTANCE = Mappers.getMapper(CreateCandidateRequestToCreateCandidateDTOMapper.class);

    CreateCandidateDTO map(CreateCandidateRequest createCandidateRequest);

}
