package com.kw.pet.controller;


import com.kw.pet.config.BaseException;
import com.kw.pet.config.BaseResponse;
import com.kw.pet.config.JwtService;
import com.kw.pet.domain.comment.Comment;
import com.kw.pet.domain.post.Post;
import com.kw.pet.domain.user.User;
import com.kw.pet.dto.*;
import com.kw.pet.service.CommentService;
import com.kw.pet.service.PostLikeService;
import com.kw.pet.service.PostService;
import com.kw.pet.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

import static com.kw.pet.config.BaseResponseStatus.USERS_EMPTY_USER_ID;
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
    private final PostLikeService postLikeService;
    private final CommentService commentService;
    private final JwtService jwtService;


    /* CREATE */
    @PostMapping("/post")
    public ResponseEntity save(@RequestBody PostResponseDto.Request dto, HttpServletRequest request) {
        System.out.println("save");
//        System.out.println("dto"+dto.getUser());
        String userUuid = jwtService.resolveToken(request);
        User user = userService.getUser(userUuid);
        System.out.println(user.getUuid() + user.getNickname());
        Long postId = postService.save(dto, user);
        System.out.println(postId);
        return ResponseEntity.ok(new JsonResponse(true, 200, "create post", postId));
    }

    /* READ */
    @GetMapping("/post/{postId}")
    public ResponseEntity<JsonResponse> read(@PathVariable Long postId) {
//            PostResponseDto.Response post = postService.read(postId);
        System.out.println(postId);
        Post post = postService.getPost(postId);
        int countLike = postLikeService.countLike(post);
        int countComment = commentService.countComment(post);
        List<CommentResponseDto.Response> comments = commentService.findAll(post);
//            List<Post> response = postService.getPostList(postId);
        postService.updateView(postId); // views ++


        PostResponseDto.readPost response = new PostResponseDto.readPost(post, countLike, countComment, comments);

        return ResponseEntity.ok(new JsonResponse(true, 200, "readPost", response));

    }

    //커뮤니티 목록 가져오기
    @GetMapping("/community/{page}/{limit}")
    public ResponseEntity community(@RequestBody PostResponseDto.community dto) {
        List<PostResponseDto.readPostList> postList = postService.getPostListByCategory(dto.getCategory());
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
        System.out.println(user.getUuid() + user.getNickname());
        postService.update(postId, dto);
        return ResponseEntity.ok(new JsonResponse(true, 200, "updatePost", postId));
    }

    /* DELETE */
    @DeleteMapping("/post/{postId}")
    public ResponseEntity delete(@PathVariable Long postId) {
        postService.delete(postId);
        return ResponseEntity.ok(new JsonResponse(true, 200, "deletePost", postId));
    }


//    //내가 쓴 글 조회
//    @GetMapping("/my/post/{userIdx}")
//    public ResponseEntity<JsonResponse> readmy(@PathVariable int userIdx) {
//        System.out.println("user의 post");
//        //List<PostResponseDto.readPostList> postList = postService.getPostListByUser(userId);
//        Post post = postService.getMypost(userIdx);
//        return ResponseEntity.ok(new JsonResponse(true, 200, "user가 작성한 post", userUuid));
//    }

    //내가 쓴 글 조회
//    @GetMapping("/mypost")
//    public ResponseEntity<JsonResponse> readmy(HttpServletRequest request){
//        String userUuid = jwtService.resolveToken(request);
//        System.out.println("userUuid : "+userUuid);
//        Post post = postService.getMyPost(request);
//        int countLike = postLikeService.countLike(post);
//        int countComment = commentService.countComment(post);
//        List<CommentResponseDto.Response> comments = commentService.findAll(post);
//
//        PostResponseDto.readPost response = new PostResponseDto.readPost(post, countLike, countComment, comments);
//
//        return ResponseEntity.ok(new JsonResponse(true, 200, "readMyPost", response));
//    }


}
