package com.kw.pet.domain.post;

import com.kw.pet.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import java.util.Optional;


public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByUser(Long id);

    Optional<Post> findByPostId(Long postId);




    @Query(value = "select p from Post p where p.category = ?1 order by p.postId desc")
    List<Post> findAllByCategory(String category);


    //์กฐํ์
    @Modifying
    @Query("update Post p set p.view = p.view + 1 where p.postId = :postId")
    int updateView(Long postId);

    @Query(value = "select p from Post p where p.user.uuid = ?1 order by p.postId desc")
    List<Post> findAllByUser(String userUuid);



}

