package de.unternehmenssoftware.doggydiary.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserMailAlreadyExistsException extends RuntimeException {

    public UserMailAlreadyExistsException() {
        super("User Mail does already exist");
    }

}
