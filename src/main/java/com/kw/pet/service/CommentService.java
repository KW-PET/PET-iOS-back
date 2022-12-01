package com.kw.pet.service;

import com.kw.pet.domain.comment.Comment;
import com.kw.pet.domain.comment.CommentRepository;
import com.kw.pet.domain.post.Post;
import com.kw.pet.domain.post.PostRepository;
import com.kw.pet.domain.user.User;
import com.kw.pet.domain.user.UserRepository;
import com.kw.pet.dto.CommentResponseDto;
import com.kw.pet.dto.PostResponseDto;
import com.kw.pet.dto.ResponseMapping;
import com.kw.pet.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public Long parentSave(CommentResponseDto.parentComment dto, String userUuid) {
        User user = userRepository.findByUuid(userUuid);
        Post post = postRepository.findByPostId(dto.getPost()).orElseThrow(() ->
                new IllegalArgumentException("댓글 쓰기 실패: 해당 게시글이 존재하지 않습니다. " + dto.getPost()));
        Comment comment = Comment.builder()
                .isParent(true)
                .comment(dto.getComment())
                .post(post)
                .user(user).build();

        commentRepository.save(comment);
        return post.getPostId();
    }

    @Transactional
    public Long childSave(CommentResponseDto.childComment dto, String userUuid) {
        User user = userRepository.findByUuid(userUuid);
        Post post = postRepository.findByPostId(dto.getPost()).orElseThrow(() ->
                new IllegalArgumentException("댓글 쓰기 실패: 해당 게시글이 존재하지 않습니다. " + dto.getPost()));
        Comment comment = Comment.builder()
                .isParent(false)
                .comment(dto.getComment())
                .parentCommentId(dto.getParentId())
                .post(post)
                .user(user).build();

        commentRepository.save(comment);
        return post.getPostId();
    }



    /* READ */
    @Transactional(readOnly = true)
    public List<CommentResponseDto.Response> findAll(Post post) {
        List<Comment> comments = commentRepository.findAllCommentParent(post.getPostId());
        List<CommentResponseDto.Response> response = new ArrayList<>();
        for(Comment comment : comments){
            List<Comment> childComments = commentRepository.findAllByParentComment(comment.getCommentId());
            response.add(new CommentResponseDto.Response(comment, childComments));
        }

        return response;
    }


    public int countComment(Post post) {
        return commentRepository.countByPost(post);
    }


}
