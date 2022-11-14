package com.kw.pet.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.kw.pet.domain.post.Post;
import com.kw.pet.domain.user.User;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

public class PostResponseDto {

    @Data
    @AllArgsConstructor
    public static class readPost{
        private Post post;
        private int countLike;
        private int countComment;
        private Object comments;
    }

    @Data
    @AllArgsConstructor
    public static class readPostList{
        private Post post;
        private int countLike;
        private int countComment;
    }

    /** 게시글의 등록과 수정을 처리할 요청(Request) 클래스 */
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
//    @JsonAutoDetect
    public static class Request {
        private String title;
        private String content;
        private String tag;
        private String category;
        private String pic;

        /* Dto -> Entity */
//        public Post toEntity() {
//            Post post = Post.builder()
//                    .title(title)
//                    .writer(writer)
//                    .content(content)
//                    //.view(0)
//                    .user(user)
//                    .build();
//            return post;
//        }
    }

    /**
     * 게시글 정보를 리턴할 응답(Response) 클래스
     * Entity 클래스를 생성자 파라미터로 받아 데이터를 Dto로 변환하여 응답
     * 별도의 전달 객체를 활용해 연관관계를 맺은 엔티티간의 무한참조를 방지
     */
    @Getter
    public static class Response {
        private Long id;
        private String title;
        private String writer;
        private String content;
        private String tag;
        private String category;
        //private final String createdDate, modifiedDate;
        private int view;

        private String pic;

        private Long likeCount; //좋아요 수

        private Long userId;
//        private final List<CommentResponseDto.Response> comment;

        /* Entity -> Dto*/
        public Response(Post post) {
            this.id = post.getPostId();
            this.title = post.getTitle();
            this.writer = post.getWriter();
            this.content = post.getContent();
            this.tag = post.getTag();
            this.category = post.getCategory();
            this.view = post.getView();
            this.pic = post.getPic();
            this.userId = Long.valueOf(post.getUser().getUserId());
//            this.comment = post.getComment().stream().map(CommentResponseDto.Response::new).collect(Collectors.toList());
        }
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class community {
        private String category;
    }


}
