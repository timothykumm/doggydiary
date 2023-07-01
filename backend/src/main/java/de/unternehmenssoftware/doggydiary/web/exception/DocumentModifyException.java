package de.unternehmenssoftware.doggydiary.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class DocumentModifyException extends IllegalArgumentException{

    public DocumentModifyException() {
        super("Couldn't modify Document");
    }

}
