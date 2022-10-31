package com.kw.pet.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByUser(Long id);

    Optional<Post> findByPostId(Long postId);

    @Query(value = "select p from Post p where p.category = ?1")
    List<Post> findAllByCategory(String category);

//    @Query(value = "select p from Post p where p.post_id = ?1")
//    List<Post> findALLByPostId(Long postId);

//    @Modifying
//    @Query("update Post p set p.view = p.view + 1 where p.id = :id")
//    int updateView(Long id);
//
//    Page<Post> findByTitleContaining(String keyword, Pageable pageable);
//
//    Optional<Post> findByUser(Long id);
}

