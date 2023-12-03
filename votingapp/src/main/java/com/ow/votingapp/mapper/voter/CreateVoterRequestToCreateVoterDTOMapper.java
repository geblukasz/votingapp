package com.ow.votingapp.mapper.voter;

import com.ow.votingapp.model.dto.CreateVoterDTO;
import com.ow.votingapp.model.request.CreateVoterRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CreateVoterRequestToCreateVoterDTOMapper {


    CreateVoterRequestToCreateVoterDTOMapper INSTANCE = Mappers.getMapper(CreateVoterRequestToCreateVoterDTOMapper.class);

    CreateVoterDTO map(CreateVoterRequest createVoterRequest);

}
