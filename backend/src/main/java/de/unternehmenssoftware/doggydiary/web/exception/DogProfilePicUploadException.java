package de.unternehmenssoftware.doggydiary.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DogProfilePicUploadException extends IllegalArgumentException{

    public DogProfilePicUploadException(Exception e) {
        super("Server couldn't upload image: " + e.getMessage());
    }

}
