package de.unternehmenssoftware.doggydiary.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DogProfilePicCreateException extends IllegalArgumentException{

    public DogProfilePicCreateException(String message) {
        super(message);
    }

}
