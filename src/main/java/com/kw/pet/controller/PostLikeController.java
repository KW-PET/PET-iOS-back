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


    //일단 보류
//    //좋아요 세기
////    @GetMapping("/like/{postId}")
////    public ResponseEntity getLikeCount(@PathVariable Long postId, @LoginSecurity User user) {
////            List<String> response = postlikeService.count(postId, user);
////            return ResponseEntity.ok(new JsonResponse(true, 200, "postlikecount", response));
////
////    }
//
//    @GetMapping("/like/{postId}")
//    public ResponseEntity getLikeCount(@PathVariable Long postId, HttpServletRequest request) {
//        List<String> response = postlikeService.count(postId, user);
//        return ResponseEntity.ok(new JsonResponse(true, 200, "postlikecount", response));
//
//    }

    //좋아요 취소
//    @DeleteMapping("/like/{postId}")
//    public ResponseEntity<String> cancelLike(@LoginSecurity User user,
//                                             @PathVariable Long postId) {
//        if (user != null) {
//            postlikeService.cancelLike(user, postId);
//        }
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    @DeleteMapping("/like/{postId}")
    public ResponseEntity<String> cancelLike(@PathVariable Long postId, HttpServletRequest request) {
        String userUuid = jwtService.resolveToken(request);
        User user = userService.getUser(userUuid);
        if (user != null) {
            postlikeService.cancelLike(user, postId);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //좋아요 등록
    @PostMapping("/like/{postId}")
    public ResponseEntity<String> addLike(@PathVariable Long postId, HttpServletRequest request) {
        boolean result = false;
        String userUuid = jwtService.resolveToken(request);
        User user = userService.getUser(userUuid);
        if (Objects.nonNull(user))
            result = postlikeService.addLike(user, postId);

        return result ?
                new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
//    @PostMapping("/like/{postId}")
//    public ResponseEntity<String> addLike(@LoginSecurity User user,
//                                          @PathVariable Long postId) {
//        boolean result = false;
//
//        if (Objects.nonNull(user))
//            result = postlikeService.addLike(user, postId);
//
//        return result ?
//                new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//    }
}
