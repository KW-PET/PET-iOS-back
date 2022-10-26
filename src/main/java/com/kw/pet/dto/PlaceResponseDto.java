package com.kw.pet.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlaceResponseDto {
    private Integer placeid;
    private Double lon;
    private Double lat;
    private Integer sort;
    private String category;
}