package com.ow.votingapp.configuration;

import com.ow.votingapp.exception.CandidateNotFoundException;
import com.ow.votingapp.exception.NoVotesYetException;
import com.ow.votingapp.exception.VoterAlreadyVotedException;
import com.ow.votingapp.exception.VoterNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String ERROR = "error";
    private static final String STATUS = "status";
    private static final String MESSAGE = "message";

    @ExceptionHandler(VoterAlreadyVotedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, Object>> handleVoterAlreadyVotedException(VoterAlreadyVotedException voterAlreadyVotedException, WebRequest request) {
        Map<String, Object> body = prepareExceptionBody(HttpStatus.BAD_REQUEST, voterAlreadyVotedException.getMessage());
        log.error("[handleVoterAlreadyVotedException] Voter already voted", voterAlreadyVotedException);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CandidateNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, Object>> handleCandidateNotFoundException(CandidateNotFoundException candidateNotFoundException, WebRequest request) {
        Map<String, Object> body = prepareExceptionBody(HttpStatus.BAD_REQUEST, candidateNotFoundException.getMessage());
        log.error("[handleCandidateNotFoundException] Candidate not found", candidateNotFoundException);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(VoterNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, Object>> handleVoterNotFoundException(VoterNotFoundException voterNotFoundException, WebRequest request) {
        Map<String, Object> body = prepareExceptionBody(HttpStatus.BAD_REQUEST, voterNotFoundException.getMessage());
        log.error("[handleVoterNotFoundException] Voter not found", voterNotFoundException);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoVotesYetException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, Object>> handleNoVotesYetException(NoVotesYetException noVotesYetException, WebRequest request) {
        Map<String, Object> body = prepareExceptionBody(HttpStatus.BAD_REQUEST, "No votes yet");
        log.error("[handleNoVotesYetException] No votes yet", noVotesYetException);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Map<String, Object>> handleGeneralException(Exception exception, WebRequest request) {
        Map<String, Object> body = prepareExceptionBody(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        log.error("[handleGeneralException] Internal server error", exception);
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Map<String, Object> prepareExceptionBody(final HttpStatus badRequest, final String candidateNotFoundException) {
        Map<String, Object> body = new HashMap<>();
        body.put(STATUS, badRequest.value());
        body.put(ERROR, badRequest.getReasonPhrase());
        body.put(MESSAGE, candidateNotFoundException);
        return body;
    }
}
