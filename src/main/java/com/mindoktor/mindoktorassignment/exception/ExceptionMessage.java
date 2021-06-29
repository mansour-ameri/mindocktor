package com.mindoktor.mindoktorassignment.exception;

import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.converter.HttpMessageNotReadableException;

public enum ExceptionMessage {
    RNS("Request Method Not Supported"),
    MNR("Request Not Readable"),
    MATM("Method Argument Type Mismatch"),
    AD("Access Denied"),
    ERDA("Empty Result - No Data Found"),
    IDAR("Internal Error");

    private final String message;

    ExceptionMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}
