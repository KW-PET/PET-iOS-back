package com.kw.pet.config.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
//    private LocalDateTime timestamp = LocalDateTime.now();
    private int status;
    private String errorCode;
    private String messege;
}
