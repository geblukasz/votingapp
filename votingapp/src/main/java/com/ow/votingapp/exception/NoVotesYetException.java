package com.ow.votingapp.exception;

public class NoVotesYetException extends Exception {

    public NoVotesYetException(String errorMessage) {
        super(errorMessage);
    }

}
