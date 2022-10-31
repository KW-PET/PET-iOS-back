package com.kw.pet.service;

import com.kw.pet.domain.comment.Comment;
import com.kw.pet.domain.comment.CommentRepository;
import com.kw.pet.domain.post.Post;
import com.kw.pet.domain.post.PostRepository;
import com.kw.pet.domain.user.User;
import com.kw.pet.domain.user.UserRepository;
import com.kw.pet.dto.CommentResponseDto;
import com.kw.pet.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    /* CREATE */
    @Transactional
    public Long save(CommentResponseDto.createComment dto, String userUuid) {
        User user = userRepository.findByUuid(userUuid);
        Post post = postRepository.findByPostId(dto.getPost()).orElseThrow(() ->
                new IllegalArgumentException("댓글 쓰기 실패: 해당 게시글이 존재하지 않습니다. " + dto.getPost()));
        Comment comment = new Comment(dto.getComment(), post, user);

        commentRepository.save(comment);

        return comment.getCommentId();
    }

    /* READ */
    @Transactional(readOnly = true)
    public List<CommentResponseDto.Response> findAll(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id: " + id));
        List<Comment> comment = commentRepository.findAllByPostId(post);

        return comment.stream().map(CommentResponseDto.Response::new).collect(Collectors.toList());
    }

    /* UPDATE */
    @Transactional
    public void update(Long id, CommentResponseDto.Request dto) {
        Comment comment = commentRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 댓글이 존재하지 않습니다. " + id));

        comment.update(dto.getComment());
    }

    /* DELETE */
    @Transactional
    public void delete(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 댓글이 존재하지 않습니다. id=" + id));

        commentRepository.delete(comment);
    }
}
