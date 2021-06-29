package com.mindoktor.mindoktorassignment.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import static com.mindoktor.mindoktorassignment.exception.ExceptionMessage.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {


    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<Object>  apiRequest(HttpRequestMethodNotSupportedException e){

        HttpStatus  badRequest = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(new ApiException(RNS.getMessage(), badRequest,getTZ()), badRequest);
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public  ResponseEntity<Object> httpMessageNotReadable(HttpMessageNotReadableException e){

        HttpStatus  badRequest = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(new ApiException(MNR.getMessage(), badRequest,getTZ()), badRequest);
    }

    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    public  ResponseEntity<Object> methodArgumentTypeMismatch(MethodArgumentTypeMismatchException e){

        HttpStatus  notAcceptable = HttpStatus.NOT_ACCEPTABLE;
        return new ResponseEntity<>(new ApiException(MATM.getMessage(), notAcceptable,getTZ()), notAcceptable);
    }

    @ExceptionHandler(value = {InvalidDataAccessResourceUsageException.class})
    public  ResponseEntity<Object> invalidDataAccessResourceUsage(InvalidDataAccessResourceUsageException e){

        HttpStatus  internalServerError = HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(new ApiException(IDAR.getMessage(), internalServerError,getTZ()), internalServerError);
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    public  ResponseEntity<Object> accessDenied(AccessDeniedException e){

        HttpStatus  forbidden = HttpStatus.FORBIDDEN;
        return new ResponseEntity<>(new ApiException(AD.getMessage(), forbidden,getTZ()), forbidden);
    }


    @ExceptionHandler(value = {EmptyResultDataAccessException.class})
    public  ResponseEntity<Object> accessDenied(EmptyResultDataAccessException e){

        HttpStatus  notFound = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(new ApiException(ERDA.getMessage(), notFound,getTZ()), notFound);
    }
    private ZonedDateTime getTZ(){
        return ZonedDateTime.now(ZoneId.of("Z"));
    }
}
