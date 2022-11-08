package com.kw.pet.domain.post;

import com.kw.pet.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    Optional<Integer> countByPost(Post post);
    //    Optional<PostLike> findByUserAndPost(Post post, User user);
    Optional<PostLike> findByUserAndPost(Long postId, User user);
}
