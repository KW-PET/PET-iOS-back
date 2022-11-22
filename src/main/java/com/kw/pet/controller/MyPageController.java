package com.kw.pet.controller;

import com.kw.pet.domain.pet.Pet;
import com.kw.pet.domain.pet.PetRepository;
import com.kw.pet.domain.placeLike.PlaceLike;
import com.kw.pet.dto.JsonResponse;
import com.kw.pet.dto.PetResponseDto;
import com.kw.pet.dto.PlaceResponseDto;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class MyPageController {

    @Autowired
    PetRepository petRepository;

    //TODO
    //authorization header로 user찾아서 user_id넣어줘야함.
    @PostMapping("/my/pet")
    public ResponseEntity addPet(@RequestBody PetResponseDto petResponseDto) throws IOException, ParseException {
        Pet pet = Pet.builder()
                .age(petResponseDto.getAge())
                .name(petResponseDto.getName())
                .pic(petResponseDto.getPic())
                .sort(petResponseDto.getSort())
                .start_date(petResponseDto.getStart_date())
                .build();
        Pet newPet = petRepository.save(pet);

        return ResponseEntity.ok(new JsonResponse(true, 200, "placeCategory", newPet));
    }
}
