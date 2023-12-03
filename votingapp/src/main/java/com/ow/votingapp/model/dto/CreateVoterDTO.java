package com.ow.votingapp.model.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateVoterDTO {

    @NonNull
    private String name;

}
