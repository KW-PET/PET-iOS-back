package com.kw.pet.domain.post;

import com.kw.pet.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

    Optional<PostLike> findByUserAndPost(User user, Post post);

    @Query(value = "select l from PostLike l where l.user.uuid = ?1 ")
    List<PostLike> findLikeByUser(String userUuid);






    PostLike findByPost(Post post);

    Integer countByPost(Post post);

}
