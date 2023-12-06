package com.ow.votingapp.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateVoterRequest {

    @NonNull
    @Schema(description = "Name of the account holder", example = "John")
    private String name;

}
