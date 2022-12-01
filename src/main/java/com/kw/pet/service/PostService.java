package com.kw.pet.service;


import com.kw.pet.config.exception.BadRequestException;
import com.kw.pet.dto.CommentResponseDto;
import com.kw.pet.dto.PostResponseDto;
import com.kw.pet.domain.post.Post;
import com.kw.pet.domain.post.PostRepository;
import com.kw.pet.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostService<Int, Optimal> {
    private final PostRepository postRepository;
    private final PostLikeService postLikeService;
    private final CommentService commentService;

    /* CREATE */
    @Transactional
    public Long save(PostResponseDto.Request dto, User user) {
        /* User 정보를 가져와 dto에 담아준다. */
        log.info("PostsService save() 실행");
        System.out.println("test     "+user.getNickname());
        Post post = Post.builder()
                .content(dto.getContent())
                .title(dto.getTitle())
                .user(user)
                .writer(user.getNickname())
                .tag(dto.getTag())
                .category(dto.getCategory())
                .pic(dto.getPic())
                .build();
        postRepository.save(post);

        return post.getPostId();
    }

    /* READ 게시글 리스트 조회 readOnly 속성으로 조회속도 개선 */
    @Transactional(readOnly = true)
    public PostResponseDto.Response findByUserId(Long id) {
        Post post = postRepository.findByUser(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id: " + id));

        return new PostResponseDto.Response(post);
    }

    /* UPDATE (dirty checking 영속성 컨텍스트)
     *  User 객체를 영속화시키고, 영속화된 User 객체를 가져와 데이터를 변경하면
     * 트랜잭션이 끝날 때 자동으로 DB에 저장해준다. */
    @Transactional
    public void update(Long id, PostResponseDto.Request dto) {
        Post post = postRepository.findByPostId(id).orElseThrow(() ->
                new BadRequestException("해당 게시글이 존재하지 않습니다. id=" + id));

//            post.update(dto.getTitle(), dto.getContent());
        post.update(dto.getTitle(), dto.getContent(), dto.getTag(), dto.getCategory(), dto.getPic());
    }

    /* DELETE */
    @Transactional
    public void delete(Long id) {
        Post post = postRepository.findByPostId(id).orElseThrow(()-> new BadRequestException("해당 게시글이 존재하지 않습니다. id=" + id));

        postRepository.delete(post);
    }

//    /* Views Counting */
    @Transactional
    public int updateView(Long postId) {
        return postRepository.updateView(postId);
    }


    /* Paging and Sort */
    @Transactional(readOnly = true)
    public Page<Post> pageList(Pageable pageable) {
        return postRepository.findAll(pageable);
    }


    public List<PostResponseDto.readPostList> getPostListByCategory(String category) {
        List<Post> postList = postRepository.findAllByCategory(category);
        List<PostResponseDto.readPostList> response = new ArrayList<>();
        for(Post post :postList){
            int countLike = postLikeService.countLike(post);
            int countComment = commentService.countComment(post);
             response.add(new PostResponseDto.readPostList(post, countLike, countComment));
        }
        return response;
    }
//내가 작성한 post 조회
    public List<PostResponseDto.readPostList> getPostListByUser(String user) {
        List<Post> postList = postRepository.findAllByUser(user);
        List<PostResponseDto.readPostList> response = new ArrayList<>();
        for(Post post :postList){
            int countLike = postLikeService.countLike(post);
            int countComment = commentService.countComment(post);
            response.add(new PostResponseDto.readPostList(post, countLike, countComment));
        }
        return response;
    }



    public Post getPost(Long postId) {
        Post post = postRepository.findByPostId(postId).orElseThrow(()->new BadRequestException("해당 게시글이 존재하지 않습니다. id=" + postId));
        return post;
    }


}
