package com.kw.pet.controller;

import com.kw.pet.config.JwtService;
import com.kw.pet.domain.pet.Pet;
import com.kw.pet.domain.pet.PetRepository;
import com.kw.pet.domain.user.User;
import com.kw.pet.dto.JsonResponse;
import com.kw.pet.service.UserService;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class PetController {

    private final PetRepository petRepository;
    private final JwtService jwtService;
    private final UserService userService;

    @PostMapping("/pet")
    public ResponseEntity addPet(int age, String name, String sort, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start_date, MultipartFile file, HttpServletRequest request) throws IOException, ParseException {
        String userUUid = jwtService.resolveToken(request);
        User user = userService.getUser(userUUid);
        String originFileName = file.getOriginalFilename();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmSS");
        String time = dateFormat.format(Calendar.getInstance().getTime());

        String newFileName = time + originFileName;
        System.out.println("[newFileName]="+newFileName);
//        File dest = new File("C:/images/" + newFileName);
        File dest = new File("/images/" + newFileName);
        file.transferTo(dest);

        String filePath = "http://49.50.164.40:8080/images?name="+newFileName;
//        String filePath = "http://localhost:8080/images?name="+newFileName;
        Pet pet = Pet.builder()
                .age(age)
                .name(name)
                .sort(sort)
                .start_date(start_date)
                .pic(filePath)
                .user(user)
                .build();
        Pet newPet = petRepository.save(pet);

        return ResponseEntity.ok(new JsonResponse(true, 200, "addPet", newPet));
    }

    @GetMapping("/pet")
    public ResponseEntity readPet(HttpServletRequest request){
        String userUUid = jwtService.resolveToken(request);
        User user = userService.getUser(userUUid);
        List<Pet> pets = petRepository.findAllByUser_uuid(user.getUuid());

        return ResponseEntity.ok(new JsonResponse(true, 200, "getPet", pets));
    }

    @GetMapping("/images")
    public ResponseEntity getImage(@RequestParam Map<String, String> param) throws IOException {

//        String imageRoot = "c:/images/";
        String imageRoot = "/images/";
        imageRoot = imageRoot + String.valueOf(param.get("name"));
        Resource resource = new FileSystemResource(imageRoot);

        if(!resource.exists()){
            return ResponseEntity.ok(new JsonResponse(false, 500, "존재하지 않는 파일입니다.", param.get("name")));
        }

        HttpHeaders header = new HttpHeaders();
        Path filepath = null;
        filepath = Paths.get(imageRoot);
        header.add("Content-Type", Files.probeContentType(filepath));

        return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
    }
}
