package com.kw.pet.service;

import com.kw.pet.config.exception.BadRequestException;
import com.kw.pet.domain.post.Post;
import com.kw.pet.domain.post.PostLike;
import com.kw.pet.domain.post.PostLikeRepository;
import com.kw.pet.domain.post.PostRepository;
import com.kw.pet.domain.user.User;
import com.kw.pet.dto.PostLikeResponseDto;
import com.kw.pet.dto.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


//@Transactional
@RequiredArgsConstructor
@Service
public class PostLikeService {
    private final PostLikeRepository postlikeRepository;
    private final PostRepository postRepository;
    private final CommentService commentService;
    private Post post;
    private com.kw.pet.domain.post.PostLike PostLike;

    //    //좋아요 등록
    public boolean addLike(User user, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow();
        //중복 좋아요 방지
        if(isNotAlreadyLike(user, post)) {
            postlikeRepository.save(new PostLike(post, user));
            return true;
        }
        return false;
    }

    //사용자가 이미 좋아요 한 게시물인지 체크
    private boolean isNotAlreadyLike(User user, Post post) {
        return postlikeRepository.findByUserAndPost(user, post).isEmpty();
    }
    //좋아요 취소
    public void cancelLike(User user, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow();
        PostLike postlike = postlikeRepository.findByUserAndPost(user, post).orElseThrow();
        postlikeRepository.delete(postlike);
    }

    //내가 좋아요한 post 조회
    public List<PostLike> getLikePostByUser(String user) {
        List<PostLike> postList = postlikeRepository.findLikeByUser(user);
        return postList;
    }


    public int countLike(Post post) {
        return postlikeRepository.countByPost(post);
    }


}