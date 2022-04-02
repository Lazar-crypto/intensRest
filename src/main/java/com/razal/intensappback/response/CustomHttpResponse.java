package com.razal.intensappback.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data @SuperBuilder @NoArgsConstructor
@JsonInclude(NON_NULL)
public class CustomHttpResponse {

    LocalDateTime timeStamp;
    int statusCode;
    HttpStatus status;
    String reason; //for exeptions
    String msg;
    Map<?,?> data;
}
