package com.ow.votingapp.model.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCandidateDTO {

    @NonNull
    private String name;

}
