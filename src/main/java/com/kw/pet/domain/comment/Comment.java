package com.kw.pet.domain.comment;

import com.kw.pet.domain.BaseTime;
import com.kw.pet.domain.post.Post;
import com.kw.pet.domain.user.User;
import com.kw.pet.dto.CommentResponseDto;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;

@Data
@Entity
@Table(name = "comment")
@NoArgsConstructor
public class Comment extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="comment_id")
    private Long commentId;

    @Column(nullable = true)
    private Long parentCommentId;

    @Column(nullable = false)
    private Boolean isParent;

    @Column(nullable = false)
    private String comment; // 댓글 내용

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // 작성자


//    @Builder
//    public Comment(Long commentId, String comment, Post post, User user) {
//        this.commentId = commentId;
//        this.comment = comment;
//        this.post = post;
//        this.user = user;
//    }


    @Builder
    public Comment(Long parentCommentId, Boolean isParent, String comment, Post post, User user) {
        this.parentCommentId = parentCommentId;
        this.isParent = isParent;
        this.comment = comment;
        this.post = post;
        this.user = user;
    }

    /* 댓글 수정 */
    public void update(String comment) {
        this.comment = comment;
    }
}
