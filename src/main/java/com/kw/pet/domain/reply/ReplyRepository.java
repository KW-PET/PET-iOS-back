//package com.kw.pet.domain.reply;
//
//import com.kw.pet.domain.comment.Comment;
//import com.kw.pet.domain.post.Post;
//import com.kw.pet.domain.reply.Reply;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import java.util.List;
//
//public interface ReplyRepository extends JpaRepository<Reply, Long> {
//    @Query(value = "select r from Reply r where r.post.postId = ?1 && r.comment.commentId = ?1")
//    List<Reply> findAllByPostId(Long post);
//    /* 게시글 대댓글 목록 가져오기 */
////    List<Reply> getReplyByPostOrderById(Post post);
//}