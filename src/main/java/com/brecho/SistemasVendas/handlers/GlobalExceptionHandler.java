package com.brecho.SistemasVendas.handlers;

import com.brecho.SistemasVendas.helpers.AppException;
import com.brecho.SistemasVendas.helpers.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler  {

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public CustomException mainExceptionHandler(Exception exception){
        var erro= new CustomException();
        erro.setStatusCode(HttpStatus.BAD_REQUEST.value());
        erro.setMessage(exception.getMessage());
        return erro;
    }
    @ExceptionHandler(AppException.class)
    public ResponseEntity<CustomException> mainExceptionHandler(AppException appException) {
        var erro = new CustomException();
        erro.setStatusCode(HttpStatus.BAD_REQUEST.value());
        erro.setMessage(appException.getMessage());
        return ResponseEntity.status(appException.getStatus()).body(erro);
    }
}
