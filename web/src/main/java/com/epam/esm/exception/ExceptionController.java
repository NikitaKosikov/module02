package com.epam.esm.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

@ControllerAdvice
public class ExceptionController {

    private final MessageSource messageSource;

    @Autowired
    public ExceptionController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(GiftCertificateException.class)
    public ResponseEntity<ExceptionDetails> invalidNameOfCertificate(GiftCertificateException e) {
        String errorMessage = messageSource.getMessage(e.getMessage(), new String[]{}, Locale.ENGLISH);
        return new ResponseEntity<>(new ExceptionDetails(errorMessage, e.getErrorCode(), HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidNameOfTag.class)
    public ResponseEntity<Object> invalidNameOfTag(InvalidNameOfTag e) {
        String errorMessage = messageSource.getMessage(e.getMessage(), new String[]{}, Locale.ENGLISH);
        return new ResponseEntity<>(new ExceptionDetails(errorMessage, e.getErrorCode(), HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }

}
