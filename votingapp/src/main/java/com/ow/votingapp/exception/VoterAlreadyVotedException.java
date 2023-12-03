package com.ow.votingapp.exception;

public class VoterAlreadyVotedException extends Exception {

    public VoterAlreadyVotedException(String errorMessage) {
        super(errorMessage);
    }

}
