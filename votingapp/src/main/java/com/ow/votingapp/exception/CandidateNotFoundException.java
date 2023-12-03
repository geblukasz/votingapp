package com.ow.votingapp.exception;

public class CandidateNotFoundException extends Exception {

    public CandidateNotFoundException(String errorMessage) {
        super(errorMessage);
    }

}
