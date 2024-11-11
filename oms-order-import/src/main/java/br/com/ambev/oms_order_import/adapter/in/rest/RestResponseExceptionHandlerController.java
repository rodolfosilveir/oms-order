package br.com.ambev.oms_order_import.adapter.in.rest;

import java.util.Arrays;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import br.com.ambev.oms_order_import.adapter.in.rest.response.DefaultResponse;
import br.com.ambev.oms_order_import.domain.exception.OrderAlreadyImportedException;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestControllerAdvice
public class RestResponseExceptionHandlerController {

    @ExceptionHandler(value = {OrderAlreadyImportedException.class})
    protected ResponseEntity<DefaultResponse<Object>> handleBadRequestValidationExceptions(RuntimeException ex, WebRequest request) {

        log.error("Bad request error. {}", ex.getMessage(), ex);
        DefaultResponse<Object> body = DefaultResponse.builder()
            .httpStatus(400)
            .errors(Arrays.asList(ex.getMessage()))
        .build();
        return ResponseEntity.badRequest().body(body); 
    }
    
}