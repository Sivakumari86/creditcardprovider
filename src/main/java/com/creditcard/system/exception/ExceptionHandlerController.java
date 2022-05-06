package com.creditcard.system.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.support.WebExchangeBindException;

import lombok.var;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerController {

	

	/**
     * Handle request Validation failures
     * @param e
     * @return errorResponse
     */
    @ExceptionHandler(WebExchangeBindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Mono<ErrorResponse> onValidationException(WebExchangeBindException e) {
        log.error("Validation exception occurred", e);

        ErrorResponse error = new ErrorResponse();
        for (ObjectError objectError : e.getAllErrors()) {
            error.getErrors().add(
                new Error(objectError.getDefaultMessage()));
        }
        return Mono.just(error);
    }
}