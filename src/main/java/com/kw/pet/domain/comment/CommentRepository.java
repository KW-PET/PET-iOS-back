package com.kw.pet.domain.comment;

import com.kw.pet.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = "select c from Comment where c.post_id = ?1")
    List<Comment> findAllByPostId(Post post);
    /* 게시글 댓글 목록 가져오기 */
//    List<Comment> getCommentByPostOrderById(Post post);
}