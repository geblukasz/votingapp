package com.ow.votingapp.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseCandidateResponse {

    @Schema(description = "Name of the candidate", example = "John")
    private String name;

    @Schema(description = "Identification number of the candidate", example = "c0d85110-83bd-49da-93e1-830d19425e40")
    private UUID identificationNumber;

}
