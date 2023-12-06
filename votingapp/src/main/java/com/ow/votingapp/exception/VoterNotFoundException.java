package com.ow.votingapp.exception;

public class VoterNotFoundException extends Exception {

    private static final String ERROR_MESSAGE = "Voter not found";

    public VoterNotFoundException() {
        super(ERROR_MESSAGE);
    }

}
