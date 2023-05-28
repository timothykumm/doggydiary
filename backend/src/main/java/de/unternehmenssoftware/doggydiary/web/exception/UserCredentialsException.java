package de.unternehmenssoftware.doggydiary.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class UserCredentialsException extends RuntimeException {

    public UserCredentialsException() {
        super("Wrong User Credentials");
    }

}
