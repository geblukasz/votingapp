package com.ow.votingapp.exception;

public class VoterAlreadyVotedException extends Exception {

    private static final String ERROR_MESSAGE = "Voter has already cast a vote";

    public VoterAlreadyVotedException() {
        super(ERROR_MESSAGE);
    }

}
