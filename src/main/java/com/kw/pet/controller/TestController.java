package com.kw.pet.controller;

import com.kw.pet.dto.JsonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {
    @GetMapping("/test")
    public ResponseEntity<Object> test(@RequestParam String msg){
        return ResponseEntity.ok(new JsonResponse(true, 200, "서버가 켜져있습니다.", msg));
    }
}
