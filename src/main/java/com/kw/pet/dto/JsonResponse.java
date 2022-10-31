package com.kw.pet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonResponse {
    private boolean isSuccess;
    private int status;
    private String message;
    private Object data;
}
