package com.kw.pet.dto;

import com.kw.pet.domain.comment.Comment;
import com.kw.pet.domain.pet.Pet;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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



