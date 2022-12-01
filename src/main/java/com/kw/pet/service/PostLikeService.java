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


//@Transactional
@RequiredArgsConstructor
@Service
public class PostLikeService {
    private final PostLikeRepository postlikeRepository;
    private final PostRepository postRepository;

    public String addLike(User user, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow();

        try{
            PostLike postLike = postlikeRepository.findByPost(post);
            if(postLike!=null){
                postlikeRepository.delete(postLike);
                return "unlike";
            }else{
                PostLike newLike= new PostLike(post, user);
                postlikeRepository.save(newLike);
                return "like";
            }
        } catch (Exception e){
            System.out.println("exception");
        }

        return null;
    }



    private boolean isNotAlreadyLike(Long postId, User user) {
        return postlikeRepository.findByUserAndPost(postId, user).isEmpty();
    }


    public int countLike(Post post) {
        return postlikeRepository.countByPost(post);
    }
}