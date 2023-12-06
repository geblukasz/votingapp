package com.ow.votingapp.exception;

public class CandidateNotFoundException extends Exception {

    private static final String ERROR_MESSAGE = "Candidate not found";

    public CandidateNotFoundException() {
        super(ERROR_MESSAGE);
    }

}
