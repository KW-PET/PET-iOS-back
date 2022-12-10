package com.kw.pet.controller;

import com.kw.pet.domain.post.PostLike;
import com.kw.pet.domain.user.User;
import com.kw.pet.config.JwtService;
import com.kw.pet.dto.JsonResponse;
import com.kw.pet.service.PostLikeService;
import com.kw.pet.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import com.kw.pet.config.LoginSecurity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

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
        String userUuid = jwtService.resolveToken(request);
        User user = userService.getUser(userUuid);
        boolean result = false;
        if (Objects.nonNull(user))
            result = postlikeService.addLike(user, postId);
        return ResponseEntity.ok(new JsonResponse(true, 200, "addLike", result));
//        return result ?
//                new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    //좋아요 취소
    @DeleteMapping("/like/{postId}")
    public ResponseEntity<JsonResponse> cancelLike(@PathVariable Long postId, HttpServletRequest request) {
        String userUuid = jwtService.resolveToken(request);
        User user = userService.getUser(userUuid);
        if (user != null) {
            postlikeService.cancelLike(user, postId);
        }
        return ResponseEntity.ok(new JsonResponse(true, 200, "cancelLike", true));
    }

    //내가 좋아요 한 글 보기
    @GetMapping("/likepost")
    public Object likePost(HttpServletRequest request){
        String userUuid = jwtService.resolveToken(request);
        System.out.println("user가 좋아요 한 post");
        List<PostLike> postList = postlikeService.getLikePostByUser(userUuid);
        return ResponseEntity.ok(new JsonResponse(true, 200, "해당 user가 좋아요한 post", postList));
    }

}
