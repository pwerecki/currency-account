package com.pw.currency.account.presentation;

import com.pw.currency.account.application.CurrencyAccountNotFound;
import com.pw.currency.account.domain.CurrencyExchangeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
class CurrencyAccountControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({IllegalArgumentException.class, CurrencyExchangeException.class})
    ResponseEntity<Object> handleExceptions(RuntimeException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler({CurrencyAccountNotFound.class})
    ResponseEntity<Object> handleCurrencyAccountNotFound(CurrencyAccountNotFound exception) {
        return ResponseEntity.status(NOT_FOUND).body(exception.getMessage());
    }
}
