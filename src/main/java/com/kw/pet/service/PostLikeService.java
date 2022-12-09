package com.kw.pet.service;

import com.kw.pet.domain.post.Post;
import com.kw.pet.domain.post.PostLike;
import com.kw.pet.domain.post.PostLikeRepository;
import com.kw.pet.domain.post.PostRepository;
import com.kw.pet.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


//@Transactional
@RequiredArgsConstructor
@Service
public class PostLikeService {
    private final PostLikeRepository postlikeRepository;
    private final PostRepository postRepository;

    private final CommentService commentService;

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

    //내가 좋아요한 post 조회
    public List<PostLike> getLikePostByUser(String user) {
        List<PostLike> postList = postlikeRepository.findLikeByUser(user);
        //List<PostResponseDto.readPostList> response = new ArrayList<>();
        return postList;
    }

    private boolean isNotAlreadyLike(Long postId, User user) {
        return postlikeRepository.findByUserAndPost(postId, user).isEmpty();
    }


    public int countLike(Post post) {
        return postlikeRepository.countByPost(post);
    }



}