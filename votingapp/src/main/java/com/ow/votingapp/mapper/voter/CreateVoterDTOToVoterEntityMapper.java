package com.ow.votingapp.mapper.voter;

import com.ow.votingapp.model.dto.CreateVoterDTO;
import com.ow.votingapp.model.entity.VoterEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CreateVoterDTOToVoterEntityMapper {


    CreateVoterDTOToVoterEntityMapper INSTANCE = Mappers.getMapper(CreateVoterDTOToVoterEntityMapper.class);

    @Mapping(target = "identificationNumber", expression = "java(java.util.UUID.randomUUID())")
    VoterEntity map(CreateVoterDTO createVoterDTO);

}
