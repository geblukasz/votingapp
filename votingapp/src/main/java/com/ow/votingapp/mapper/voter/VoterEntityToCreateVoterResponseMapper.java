package com.ow.votingapp.mapper.voter;

import com.ow.votingapp.model.entity.VoterEntity;
import com.ow.votingapp.model.response.BaseVoterResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper
public interface VoterEntityToCreateVoterResponseMapper {

    VoterEntityToCreateVoterResponseMapper INSTANCE = Mappers.getMapper(VoterEntityToCreateVoterResponseMapper.class);

    BaseVoterResponse map(VoterEntity voterEntity);

    default UUID map() {
        return UUID.randomUUID();
    }

}
