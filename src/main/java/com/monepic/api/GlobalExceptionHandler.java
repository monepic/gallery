package com.monepic.api;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.monepic.gallery.util.ExUtils;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @ExceptionHandler (value = Exception.class)
    public ResponseEntity<JsonError> handleException(HttpServletRequest req, Exception e) {

        log.warn ("handling exception", e);

        ResponseStatus statusAnnotation;

        HttpStatus responseStatus = null;

        if ((statusAnnotation = AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class)) != null) {
            responseStatus = statusAnnotation.value();
        }

        log.info("Status annotation is {}", responseStatus);

        //   if (e instanceof AuthenticationException) { responseStatus = HttpStatus.UNAUTHORIZED; }

        if ( responseStatus == null) { responseStatus = HttpStatus.INTERNAL_SERVER_ERROR; }

        return new ResponseEntity<JsonError> (new JsonError(e), responseStatus);

    }

    static class JsonError {

        public final String message;

        public JsonError(Exception e) {
            this.message = ExUtils.getMessage(e);
        }
    }

}

