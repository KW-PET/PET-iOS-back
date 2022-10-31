package com.kw.pet.controller;

import com.kw.pet.config.JwtService;
import com.kw.pet.dto.JsonResponse;
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

    @PostMapping("/comment/write")
    public ResponseEntity save(@RequestBody CommentResponseDto.createComment dto, HttpServletRequest request) {
        String userUuid = jwtService.resolveToken(request);
        Long commentIdx = commentService.save(dto, userUuid);
        return ResponseEntity.ok(new JsonResponse(true, 200, "comment save", commentIdx));
    }

//    /* READ */
    @GetMapping("/comment/{postid}")
    public List<CommentResponseDto.Response> read(@PathVariable Long postid) {
        return commentService.findAll(postid);
    }

    //댓글 수정 보류
    /* UPDATE
    @PutMapping("/edit/:commentid")
    public ResponseEntity update(@PathVariable Long id, @RequestBody CommentResponseDto.Request dto) {
        commentService.update(id, dto);
        return ResponseEntity.ok(id);
    }

     */



    /* DELETE */
    @DeleteMapping("/:commentid")
    public ResponseEntity delete(@PathVariable Long id) {
        commentService.delete(id);
        return ResponseEntity.ok(id);
    }
}
