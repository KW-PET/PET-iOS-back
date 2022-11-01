package com.kw.pet.service;


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

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    /* CREATE */
    @Transactional
    public Long save(PostResponseDto.Request dto, User user) {
        /* User 정보를 가져와 dto에 담아준다. */
        log.info("PostsService save() 실행");
        Post post = Post.builder()
                .content(dto.getContent())
                .title(dto.getTitle())
                .user(user)
                .writer(user.getNickname())
                .tag(dto.getTag())
                .category(dto.getCategory())
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
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + id));

//            post.update(dto.getTitle(), dto.getContent());
        post.update(dto.getTitle(), dto.getContent(), dto.getTag(), dto.getCategory());
    }

    /* DELETE */
    @Transactional
    public void delete(Long id) {
        Post post = postRepository.findByPostId(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + id));

        postRepository.delete(post);
    }

//    /* Views Counting */
//    @Transactional
//    public int updateView(Long id) {
//        return postRepository.updateView(id);
//    }


    /* Paging and Sort */
    @Transactional(readOnly = true)
    public Page<Post> pageList(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    public PostResponseDto.Response read(Long postId) {
        Post post = postRepository.findByPostId(postId).orElseThrow(()->new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id: " + postId));
        return new PostResponseDto.Response(post);
    }

    public List<Post> getPostListByCategory(String category) {
        List<Post> postList = postRepository.findAllByCategory(category);
        return postList;
    }

    public Optional<Post> getPostList(Long postId) {
        Optional<Post> postLists = postRepository.findByPostId(postId);
        return postLists;
    }

//    public List<Post> getPostList(Long postId) {
//        List<Post> postLists = postRepository.findALLByPostId(postId);
//        return postLists;
//    }


//    /* search */
//    @Transactional(readOnly = true)
//    public Page<Post> search(String keyword, Pageable pageable) {
//        Page<Post> postsList = postRepository.findByTitleContaining(keyword, pageable);
//        return postsList;
//    }
}
