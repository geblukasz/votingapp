package com.ow.votingapp.exception;

public class VoterNotFoundException extends Exception {

    public VoterNotFoundException(String errorMessage) {
        super(errorMessage);
    }

}
