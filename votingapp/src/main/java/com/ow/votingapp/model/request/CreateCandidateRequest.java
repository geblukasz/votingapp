package com.ow.votingapp.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCandidateRequest {

    @NonNull
    @Schema(description = "Name of the candidate", example = "John")
    private String name;

}
