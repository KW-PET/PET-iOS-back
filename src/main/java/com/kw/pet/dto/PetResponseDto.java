package com.kw.pet.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetResponseDto {
    private String name;
    private String sort;
    private int age;
    private String pic;
    private LocalDate start_date;
}
