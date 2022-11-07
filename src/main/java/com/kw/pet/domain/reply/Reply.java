//package com.kw.pet.domain.reply;
//
//import com.kw.pet.domain.BaseTime;
//import com.kw.pet.domain.post.Post;
//import com.kw.pet.domain.user.User;
//import com.kw.pet.dto.CommentResponseDto;
//import lombok.*;
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.annotation.LastModifiedDate;
//
//import javax.persistence.*;
//
//@Data
//@Entity
//@Table(name = "reply")
//@NoArgsConstructor
//public class Reply extends BaseTime {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name ="reply_id")
//    private Long replyId;
//
//    @Column(nullable = false)
//    private String reply; // 대댓글 내용
//
//    @ManyToOne
//    @JoinColumn(name = "post_id")
//    private Post post;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user; // 작성자
//
//
////    @Builder
////    public Reply(Long replyId, String reply, Post post, User user) {
////        this.replyId = replyId;
////        this.reply = reply;
////        this.post = post;
////        this.user = user;
////    }
//
//    public Reply(String reply, Post post, User user) {
//        this.reply = reply;
//        this.post = post;
//        this.user = user;
//    }
//
//    /* 댓글 수정 */
//    public void update(String reply) {
//        this.reply = reply;
//    }
//}
