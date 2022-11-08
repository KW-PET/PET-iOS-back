package com.kw.pet.service;

import com.kw.pet.domain.post.Post;
import com.kw.pet.domain.post.PostLike;
import com.kw.pet.domain.post.PostLikeRepository;
import com.kw.pet.domain.post.PostRepository;
import com.kw.pet.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.relational.core.sql.Like;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


@Transactional
@RequiredArgsConstructor
@Service
public class PostLikeService {
    private final PostLikeRepository postlikeRepository;
    private final PostRepository postRepository;

    public boolean addLike(User user, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow();

//        //중복 좋아요 방지
//        if (isNotAlreadyLike(user, post)) {
//            postlikeRepository.save(new PostLike(post, user));
//            return true;
//        }
//        중복 좋아요 방지
        if (isNotAlreadyLike(postId, user)) {
            postlikeRepository.save(new PostLike(post, user));
            return true;
        }


        return false;

    }

    //    public void cancelLike(User user, Long postId) {
//        Post post = postRepository.findByPostId(postId).orElseThrow();
//        PostLike postlike = postlikeRepository.findByUserAndPost(post, user).orElseThrow();
//        postlikeRepository.delete(postlike);
//        postlikeRepository.delete(new PostLike());
//    }
    public void cancelLike(User user, Long postId) {
        Post post = postRepository.findByPostId(postId).orElseThrow();
        PostLike postlike = postlikeRepository.findByUserAndPost(postId, user).orElseThrow();
        postlikeRepository.delete(postlike);
    }


//일단 보류
    /*
     *   1. 좋아요를 count할 대상 post를 가져온다.
     *   2. 가져온 post로 like테이블에 쿼리한 결과를 List에 담는다.
     *   3. 현재 로그인한 사용자가
     *       이미 해당 post에 좋아요를 눌렀는지 검사하고 결과를 List에 담아 반환한다.
     * */
//    public List<String> count(Long postId, User user) {
//        Post post = postRepository.findByPostId(postId).orElseThrow();
//
//        Integer postLikeCount = postlikeRepository.countByPost(post).orElse(0);
//
//        List<String> resultData =
//                new ArrayList<>(Arrays.asList(String.valueOf(postLikeCount)));
//
//        if (Objects.nonNull(user)) {
//            resultData.add(String.valueOf(isNotAlreadyLike(user, post)));
//            return resultData;
//        }
//
//        return resultData;
//    }


//    public List<String> getCount(Long postId) {
//        List<String> likeCount = postlikeRepository.countByPost(postId);
//        return likeCount;
//    }

    //    사용자가 이미 좋아요 한 게시물인지 체크
//    private boolean isNotAlreadyLike(User user, Post post) {
//        return postlikeRepository.findByUserAndPost(post, user).isEmpty();
//    }
    private boolean isNotAlreadyLike(Long postId, User user) {
        return postlikeRepository.findByUserAndPost(postId, user).isEmpty();
    }


}