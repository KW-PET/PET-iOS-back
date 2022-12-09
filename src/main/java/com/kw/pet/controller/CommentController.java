package com.kw.pet.controller;

import com.kw.pet.config.JwtService;
import com.kw.pet.domain.comment.Comment;
import com.kw.pet.domain.post.Post;
import com.kw.pet.dto.JsonResponse;
import com.kw.pet.dto.ResponseMapping;
import com.kw.pet.service.CommentService;
import com.kw.pet.dto.CommentResponseDto;
import com.kw.pet.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * REST API Controller
 */
@RequiredArgsConstructor
@RequestMapping("/post")
@RestController
public class CommentController {
    private final CommentService commentService;
    private final JwtService jwtService;

    /* CREATE */

    @PostMapping("/comment")
    public ResponseEntity parentCommentSave(@RequestBody CommentResponseDto.parentComment dto, HttpServletRequest request) {
        String userUuid = jwtService.resolveToken(request);
        Long postId = commentService.parentSave(dto, userUuid);
        return ResponseEntity.ok(new JsonResponse(true, 200, "comment save", postId));
    }

    @PostMapping("/comment/reply")
    public ResponseEntity childCommentSave(@RequestBody CommentResponseDto.childComment dto, HttpServletRequest request) {
        String userUuid = jwtService.resolveToken(request);
        Long postId = commentService.childSave(dto, userUuid);
        return ResponseEntity.ok(new JsonResponse(true, 200, "reply save", postId));
    }

}
