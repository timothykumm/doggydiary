package de.unternehmenssoftware.doggydiary.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserCreateException extends IllegalArgumentException{

    public UserCreateException() {
        super("Couldn't create User");
    }

}
