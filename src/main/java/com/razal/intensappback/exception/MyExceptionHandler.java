package com.razal.intensappback.exception;

import com.razal.intensappback.exception.custom.SkillNameAlreadyExistsException;
import com.razal.intensappback.response.CustomHttpResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(SkillNameAlreadyExistsException.class)
    public ResponseEntity<Object> handleSkillNameNotFoundException(SkillNameAlreadyExistsException ex){
        return ResponseEntity.ok(
                CustomHttpResponse.builder()
                        .timeStamp(now())
                        .msg(ex.getMessage())
                        .status(BAD_REQUEST)
                        .statusCode(BAD_REQUEST.value())
                        .build()
        );
    }

}
