package com.ow.votingapp.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseVoterResponse {

    private String name;
    private UUID identificationNumber;
    private boolean voted;

    // Other methods or annotations as needed

    // Explicitly define a constructor
    public BaseVoterResponse(String name, UUID identificationNumber, boolean voted) {
        this.name = name;
        this.identificationNumber = identificationNumber;
        this.voted = voted;
    }
}
