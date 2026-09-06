package com.siemens.leaveportal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LeaveRequestNotFoundException.class)
    public ResponseEntity<?> notFound(LeaveRequestNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", ex.getMessage()));
    }

    // BUG: falta handler para InsufficientLeaveBalanceException (checked) -
    // como o metodo do service a declara com "throws", quem chama e obrigado
    // a apanha-la; aqui nao ha @ExceptionHandler para ela, por isso, se
    // escapar, vira um 500 generico sem mensagem util.
}
