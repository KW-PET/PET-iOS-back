package com.kw.pet.dto;


import com.kw.pet.domain.comment.Comment;
import com.kw.pet.domain.post.Post;
import com.kw.pet.domain.user.User;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class CommentResponseDto {
    /** 댓글 Service 요청을 위한 DTO 클래스 */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class parentComment {
        private String comment;
        private Long post;
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class childComment {
        private String comment;
        private Long post;
        private Long parentId;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {
        private Long id;
        private String comment;
        private String createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
        private String modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
        private User user;
        private Post post;

        /* Dto -> Entity */
//        public Comment toEntity() {
//            Comment comments = Comment.builder()
//                    .commentId(id)
//                    .comment(comment)
//                    .user(user)
//                    .post(post)
//                    .build();
//
//            return comments;
////        }
//        }
    }

    /**
     * 댓글 정보를 리턴할 응답(Response) 클래스
     * Entity 클래스를 생성자 파라미터로 받아 데이터를 Dto로 변환하여 응답
     * 별도의 전달 객체를 활용해 연관관계를 맺은 엔티티간의 무한참조를 방지
     */
    @RequiredArgsConstructor
    @Getter
    public static class Response {
        private Long id;
        private String comment;
        private LocalDateTime createdDate;
        private LocalDateTime modifiedDate ;
        private String nickname;
        private List<childResponse> childComments = new ArrayList<>();
//        private User user;
//        private Long postId;
        /* Entity -> Dto*/
        public Response(Comment comment, List<Comment> childComment) {
            this.id = comment.getCommentId();
            this.comment = comment.getComment();
            this.nickname = comment.getUser().getNickname();
//            this.user = comment.getUser();
//            this.postId = comment.getPost().getPostId();
            this.createdDate = comment.getCreated_at();
            this.modifiedDate = comment.getModified_at();
            for(Comment child :childComment){
                this.childComments.add(new childResponse(child));
            }

        }
    }

    @RequiredArgsConstructor
    @Getter
    public static class childResponse {
        private Long id;
        private String comment;
        private LocalDateTime createdDate;
        private LocalDateTime modifiedDate ;
        private String nickname;
        //        private User user;
//        private Long postId;
        /* Entity -> Dto*/
        public childResponse(Comment comment) {
            this.id = comment.getCommentId();
            this.comment = comment.getComment();
            this.nickname = comment.getUser().getNickname();
//            this.user = comment.getUser();
//            this.postId = comment.getPost().getPostId();
            this.createdDate = comment.getCreated_at();
            this.modifiedDate = comment.getModified_at();
        }
    }

}
