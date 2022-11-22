package com.kw.pet.dto;

import lombok.*;

public class PlaceResponseDto {

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {
        private Double lon;
        private Double lat;
        private Integer sort;
        private String category;
    }


}