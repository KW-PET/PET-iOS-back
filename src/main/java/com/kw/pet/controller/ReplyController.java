//package com.kw.pet.controller;
//
//import com.kw.pet.config.JwtService;
//import com.kw.pet.domain.comment.Comment;
//import com.kw.pet.domain.post.Post;
//import com.kw.pet.dto.*;
//import com.kw.pet.service.CommentService;
//import com.kw.pet.service.ReplyService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.List;
//
///**
// * REST API Controller
// */
//@RequiredArgsConstructor
//@RequestMapping("/post")
//@RestController
//public class ReplyController {
//    private final ReplyService replyService;
//    private final JwtService jwtService;
//
//    /* CREATE */
//
//    @PostMapping("/comment/reply")
//    public ResponseEntity save(@RequestBody ReplyResponseDto.createReply dto, HttpServletRequest request) {
//        String userUuid = jwtService.resolveToken(request);
//        Long replyIdx = replyService.save(dto, userUuid);
//        return ResponseEntity.ok(new JsonResponse(true, 200, "reply save", replyIdx));
//    }
//
//    //    /* READ */
//    @GetMapping("/reply/{postid}/{commentid}")
//    public ResponseEntity<JsonResponse> read(@PathVariable Long postid) {
//        ResponseMapping.PostandComm response =  replyService.findAll(postid);
//        return ResponseEntity.ok(new JsonResponse(true, 200, "ReplyRead", response));
//    }
//
//    //대댓글 수정 보류
//    /* UPDATE
//    @PutMapping("/edit/{replyid}")
//    public ResponseEntity update(@PathVariable Long id, @RequestBody ReplyResponseDto.Request dto) {
//        replyService.update(id, dto);
//        return ResponseEntity.ok(id);
//    }
//
//     */
//
//
////    //대댓글 삭제 보류
////    /* DELETE */
////    @DeleteMapping("/{replyid}")
////    public ResponseEntity delete(@PathVariable Long replyId) {
////        replyService.delete(replyId);
////        return ResponseEntity.ok(replyId);
////    }
//}
