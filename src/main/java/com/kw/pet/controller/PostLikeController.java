package com.kw.pet.controller;

import com.kw.pet.domain.post.Post;
import com.kw.pet.domain.user.User;
import com.kw.pet.dto.ErrorResponse;
import com.kw.pet.config.JwtService;
import com.kw.pet.dto.JsonResponse;
import com.kw.pet.service.PostLikeService;
import com.kw.pet.dto.PostLikeResponseDto;
import com.kw.pet.dto.PostResponseDto;
import com.kw.pet.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;
//import com.kw.pet.config.LoginSecurity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PostLikeController {
    private final PostLikeService postlikeService;

    private final UserService userService;
    private final JwtService jwtService;



    //좋아요 등록
    @PostMapping("/like/{postId}")
    public ResponseEntity<JsonResponse> addLike(@PathVariable Long postId, HttpServletRequest request) {
        boolean result = false;
        String userUuid = jwtService.resolveToken(request);
        User user = userService.getUser(userUuid);
        String like="";
        if (Objects.nonNull(user))
            like = postlikeService.addLike(user, postId);

        return ResponseEntity.ok(new JsonResponse(true, 200, "addLike", like));
//        return result ?
//                new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
