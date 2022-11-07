package com.kw.pet.controller;


import com.kw.pet.config.JwtService;
import com.kw.pet.domain.post.Post;
import com.kw.pet.domain.user.User;
import com.kw.pet.dto.ErrorResponse;
import com.kw.pet.dto.JsonResponse;
import com.kw.pet.dto.PostResponseDto;
import com.kw.pet.dto.UserResponseDto;
import com.kw.pet.service.PostService;
import com.kw.pet.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

/**
 * REST API Controller
 */
@Slf4j
@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostService postService;
    private final UserService userService;
    private final JwtService jwtService;


    /* CREATE */
    @PostMapping("/post")
    public ResponseEntity save(@RequestBody PostResponseDto.Request dto, HttpServletRequest request) {
        System.out.println("save");
//        System.out.println("dto"+dto.getUser());
        String userUuid = jwtService.resolveToken(request);
        User user = userService.getUser(userUuid);
        System.out.println(user.getUuid()+user.getNickname());
        Long postId = postService.save(dto, user);
        System.out.println(postId);
        return ResponseEntity.ok(new JsonResponse(true, 200, "create post", postId));
    }

    /* READ */
    @GetMapping("/post/{postId}")
    public ResponseEntity read(@PathVariable Long postId) {
        try{
//            PostResponseDto.Response post = postService.read(postId);
              Optional<Post> response = postService.getPostList(postId);
//            List<Post> response = postService.getPostList(postId);
                postService.updateView(postId); // views ++
            return ResponseEntity.ok(new JsonResponse(true, 200, "readPost", response));

        }catch (IllegalArgumentException e){
            log.info("not found post");
            return ResponseEntity.badRequest().body(new ErrorResponse(false, 404, "not found Post"));
        }
    }

        //커뮤니티 목록 가져오기
    @GetMapping("/community/{page}/{limit}")
    public ResponseEntity community(@RequestBody PostResponseDto.community dto){
        List<Post> postList = postService.getPostListByCategory(dto.getCategory());
        return ResponseEntity.ok(new JsonResponse(true, 200, "community", postList));
    }

//    /* UPDATE */
//    @PutMapping("/post/edit/{postId}")
//    public ResponseEntity update(@PathVariable Long postId, @RequestBody PostResponseDto.Request dto) {
//        postService.update(postId, dto);
//        return ResponseEntity.ok(new JsonResponse(true, 200, "updatePost", postId));
//    }

    /* UPDATE */
    @PutMapping("/post/edit/{postId}")
    public ResponseEntity update(@PathVariable Long postId, @RequestBody PostResponseDto.Request dto, HttpServletRequest request) {
        String userUuid = jwtService.resolveToken(request);
        User user = userService.getUser(userUuid);
        System.out.println(user.getUuid()+user.getNickname());
        postService.update(postId, dto);
        return ResponseEntity.ok(new JsonResponse(true, 200, "updatePost", postId));
    }

    /* DELETE */
    @DeleteMapping("/post/{postId}")
    public ResponseEntity delete(@PathVariable Long postId) {
        postService.delete(postId);
        return ResponseEntity.ok(new JsonResponse(true, 200, "deletePost", postId));
    }
}
