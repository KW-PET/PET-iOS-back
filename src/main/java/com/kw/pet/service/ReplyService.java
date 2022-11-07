//package com.kw.pet.service;
//
//import com.kw.pet.domain.comment.Comment;
//import com.kw.pet.domain.comment.CommentRepository;
//import com.kw.pet.domain.post.Post;
//import com.kw.pet.domain.post.PostRepository;
//import com.kw.pet.domain.reply.Reply;
//import com.kw.pet.domain.reply.ReplyRepository;
//import com.kw.pet.domain.user.User;
//import com.kw.pet.domain.user.UserRepository;
//import com.kw.pet.dto.CommentResponseDto;
//import com.kw.pet.dto.ReplyResponseDto;
//import com.kw.pet.dto.ResponseMapping;
//import com.kw.pet.dto.UserResponseDto;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@RequiredArgsConstructor
//@Service
//public class ReplyService {
//
//    private final ReplyRepository replyRepository;
//    private final UserRepository userRepository;
//    private final PostRepository postRepository;
//
//    /* CREATE */
//    @Transactional
//    public Long save(ReplyResponseDto.createReply dto, String userUuid) {
//        User user = userRepository.findByUuid(userUuid);
//        Post post = postRepository.findByPostId(dto.getPost()).orElseThrow(() ->
//                new IllegalArgumentException("대댓글 쓰기 실패: 해당 게시글이 존재하지 않습니다. " + dto.getPost()));
//        Reply reply = new Reply(dto.getReply(), post, user);
//
//        replyRepository.save(reply);
//
//        return reply.getReplyId();
//    }
//
//    /* READ */
//    @Transactional(readOnly = true)
//    public ResponseMapping.PostandComm findAll(Long id) {
//        Post post = postRepository.findById(id).orElseThrow(() ->
//                new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id: " + id));
//        List<Reply> reply = replyRepository.findAllByPostId(post.getPostId());
////        return reply;
//        List<ReplyResponseDto.Response> replyList = reply.stream().map(ReplyResponseDto.Response::new).collect(Collectors.toList());
//        ResponseMapping.PostandComm response = new ResponseMapping.PostandComm(post, replyList);
//        return response;
//    }
//
//    /* UPDATE */
//    @Transactional
//    public void update(Long id, ReplyResponseDto.Request dto) {
//        Reply reply = replyRepository.findById(id).orElseThrow(() ->
//                new IllegalArgumentException("해당 댓글이 존재하지 않습니다. " + id));
//
//        reply.update(dto.getReply());
//    }
//
//    /* DELETE */
//    @Transactional
//    public void delete(Long id) {
//        Reply reply = replyRepository.findById(id).orElseThrow(() ->
//                new IllegalArgumentException("해당 댓글이 존재하지 않습니다. id=" + id));
//
//        replyRepository.delete(reply);
//    }
//}
