//package com.kw.pet.dto;
//
//import com.kw.pet.domain.comment.Comment;
//import com.kw.pet.domain.post.Post;
//import com.kw.pet.domain.reply.Reply;
//import com.kw.pet.domain.user.User;
//import lombok.*;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//
//
//public class ReplyResponseDto {
//    /** 댓글 Service 요청을 위한 DTO 클래스 */
//    @Data
//    @AllArgsConstructor
//    @NoArgsConstructor
//    @Builder
//    public static class createReply {
//        private String reply;
//        private Long post;
//    }
//
//    @Data
//    @AllArgsConstructor
//    @NoArgsConstructor
//    public static class Request {
//        private Long id;
//        private String reply;
//        private String createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
//        private String modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
//        private User user;
//        private Post post;
//
//        /* Dto -> Entity */
////        public Reply toEntity() {
////            Reply reply = Reply.builder()
////                    .replyId(id)
////                    .reply(reply)
////                    .user(user)
////                    .post(post)
////                    .build();
////
////            return reply;
//////        }
////        }
//    }
//
//    /**
//     * 대댓글 정보를 리턴할 응답(Response) 클래스
//     * Entity 클래스를 생성자 파라미터로 받아 데이터를 Dto로 변환하여 응답
//     * 별도의 전달 객체를 활용해 연관관계를 맺은 엔티티간의 무한참조를 방지
//     */
//    @RequiredArgsConstructor
//    @Getter
//    public static class Response {
//        private Long id;
//        private String reply;
//        private LocalDateTime createdDate;
//        private LocalDateTime modifiedDate ;
//        private String nickname;
//        private User user;
//        private Long postId;
//        /* Entity -> Dto*/
//        public Response(Reply reply) {
//            this.id = reply.getReplyId();
//            this.reply = reply.getReply();
//            this.nickname = reply.getUser().getNickname();
//            this.user = reply.getUser();
//            this.postId = reply.getPost().getPostId();
//            this.createdDate = reply.getCreatedAt();
//            this.modifiedDate = reply.getModifiedAt();
//        }
//    }
//}
//
