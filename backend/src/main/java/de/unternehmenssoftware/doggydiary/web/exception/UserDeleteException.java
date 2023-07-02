package de.unternehmenssoftware.doggydiary.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class UserDeleteException extends IllegalArgumentException{

    public UserDeleteException() {
        super("Couldn't delete User");
    }

}
