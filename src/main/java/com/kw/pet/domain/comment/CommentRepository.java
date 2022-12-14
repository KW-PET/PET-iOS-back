package com.kw.pet.domain.comment;

import com.kw.pet.domain.post.Post;
import com.kw.pet.domain.post.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = "select c from Comment c where c.post.postId = ?1")
    List<Comment> findAllByPostId(Long post);

    int countByPost(Post post);


    @Query(value = "select c from Comment c where c.post.postId = ?1 and c.isParent=true")
    List<Comment> findAllCommentParent(Long postId);

    @Query(value = "select c from Comment c where c.parentCommentId = ?1 and c.isParent=false")
    List<Comment> findAllByParentComment(Long commentId);

}
