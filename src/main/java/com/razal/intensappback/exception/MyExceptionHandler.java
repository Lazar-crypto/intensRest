package com.razal.intensappback.exception;

import com.razal.intensappback.exception.custom.CandidateAlreadyHasSKillException;
import com.razal.intensappback.exception.custom.CandidateNotFoundException;
import com.razal.intensappback.exception.custom.SkillNameAlreadyExistsException;
import com.razal.intensappback.response.CustomHttpResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;

@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {

    //for bad request(400)
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.ok(
                CustomHttpResponse.builder()
                        .timeStamp(now())
                        .msg(ex.getMessage())
                        .reason("Validation error")
                        .status(BAD_REQUEST)
                        .statusCode(BAD_REQUEST.value())
                        .build()
        );
    }
    //for method not allowed(405)
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
                                                                         HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.ok(
                CustomHttpResponse.builder()
                        .timeStamp(now())
                        .msg(ex.getMessage())
                        .reason("User provided wrong Http method")
                        .status(METHOD_NOT_ALLOWED)
                        .statusCode(METHOD_NOT_ALLOWED.value())
                        .build()
        );
    }
    @ExceptionHandler(SkillNameAlreadyExistsException.class)
    public ResponseEntity<CustomHttpResponse> handleSkillNameNotFoundException(SkillNameAlreadyExistsException ex){
        return ResponseEntity.ok(
                CustomHttpResponse.builder()
                        .timeStamp(now())
                        .msg(ex.getMessage())
                        .status(BAD_REQUEST)
                        .statusCode(BAD_REQUEST.value())
                        .build()
        );
    }
    @ExceptionHandler(CandidateAlreadyHasSKillException.class)
    public ResponseEntity<CustomHttpResponse> handleCandidateAlreadyHasSkillException(CandidateAlreadyHasSKillException ex){
        return ResponseEntity.ok(
                CustomHttpResponse.builder()
                        .timeStamp(now())
                        .msg(ex.getMessage())
                        .status(BAD_REQUEST)
                        .statusCode(BAD_REQUEST.value())
                        .build()
        );
    }
    @ExceptionHandler(CandidateNotFoundException.class)
    public ResponseEntity<CustomHttpResponse> handleCandidateNotFoundException(CandidateNotFoundException ex){
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
