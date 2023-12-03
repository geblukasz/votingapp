package com.ow.votingapp.mapper.candidate;

import com.ow.votingapp.model.dto.CreateCandidateDTO;
import com.ow.votingapp.model.entity.CandidateEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper
public interface CreateCandidateDTOToCandidateEntityMapper {


    CreateCandidateDTOToCandidateEntityMapper INSTANCE = Mappers.getMapper(CreateCandidateDTOToCandidateEntityMapper.class);

    @Mapping(target = "identificationNumber", expression = "java(java.util.UUID.randomUUID())")
    CandidateEntity map(CreateCandidateDTO createCandidateDTO);

    default UUID map() {
        return UUID.randomUUID();
    }

}
