package com.kw.pet.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JsonResponse {
    private boolean isSuccess;
    private int status;
    private String message;
    private Object data;

    public JsonResponse(boolean isSuccess, int status, String message, Object data) {
        this.isSuccess = isSuccess;
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
