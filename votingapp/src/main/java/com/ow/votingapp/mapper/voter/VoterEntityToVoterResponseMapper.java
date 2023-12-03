package com.ow.votingapp.mapper.voter;

import com.ow.votingapp.model.entity.VoterEntity;
import com.ow.votingapp.model.response.BaseVoterResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface VoterEntityToVoterResponseMapper {

    VoterEntityToVoterResponseMapper INSTANCE = Mappers.getMapper(VoterEntityToVoterResponseMapper.class);

    BaseVoterResponse map(VoterEntity voterEntity);

    List<BaseVoterResponse> map(List<VoterEntity> voterEntityList);

}
