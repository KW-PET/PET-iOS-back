package com.kw.pet.controller;

import com.kw.pet.config.JwtService;
import com.kw.pet.domain.pet.Pet;
import com.kw.pet.domain.pet.PetRepository;
import com.kw.pet.domain.user.User;
import com.kw.pet.domain.user.UserRepository;
import com.kw.pet.dto.JsonResponse;
import com.kw.pet.dto.PetResponseDto;
import com.kw.pet.service.UserService;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class PetController {

    private final PetRepository petRepository;
    private final JwtService jwtService;
    private final UserService userService;

    @PostMapping("/pet")
    public ResponseEntity addPet(@RequestBody PetResponseDto petResponseDto, HttpServletRequest request) throws IOException, ParseException {
        String userUUid = jwtService.resolveToken(request);
        User user = userService.getUser(userUUid);
        Pet pet = Pet.builder()
                .age(petResponseDto.getAge())
                .name(petResponseDto.getName())
                .pic(petResponseDto.getPic())
                .sort(petResponseDto.getSort())
                .start_date(petResponseDto.getStart_date())
                .user(user)
                .build();
        Pet newPet = petRepository.save(pet);

        return ResponseEntity.ok(new JsonResponse(true, 200, "placeCategory", newPet));
    }

    @GetMapping("/pet")
    public ResponseEntity readPet(HttpServletRequest request){
        String userUUid = jwtService.resolveToken(request);
        User user = userService.getUser(userUUid);
        List<Pet> pets = petRepository.findAllByUser_uuid(user.getUuid());

        return ResponseEntity.ok(new JsonResponse(true, 200, "placeCategory", pets));
    }
}
