package com.ow.votingapp.exception;

public class NoVotesYetException extends Exception {

    private static final String ERROR_MESSAGE = "No votes yet";

    public NoVotesYetException() {
        super(ERROR_MESSAGE);
    }

}
