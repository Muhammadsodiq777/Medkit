package com.example.medkit.exceptions;

import com.example.medkit.dto.response.GeneralResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<GeneralResponse> notFound(NotFoundException exception) {
        return exception(GeneralResponse.error(HttpResponseCode.NOT_FOUND.getCode(),
                exception.getMessage() == null ? HttpResponseCode.NOT_FOUND.getMessage() : exception.getMessage()));
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<GeneralResponse> badRequestException(BadRequestException exception) {
        return exception(GeneralResponse.error(400, exception.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<GeneralResponse> exception(Exception exception) {
        return exception(GeneralResponse.error(500, exception.getMessage()));
    }

    private ResponseEntity<GeneralResponse> exception(GeneralResponse response) {
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
