//package com.kw.pet.dto;
//
//import java.time.LocalDateTime;
//
//import com.fasterxml.jackson.annotation.JsonAutoDetect;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@JsonAutoDetect
//public class CommunityResponseDto {
//    private int postid; //게시글 id
//    private int uuid; //작성자 id(사용자 확인)
//    private LocalDateTime createdat; // 작성 날짜
//    private String title;  // 게시글 제목
//    private String content; //게시글 내용
//    private String category; //게시판 형식
//    private String tag; //게시글 태그
//    private String pic; //사진 Url
//    private int commentid; //댓글 id
//    private int replyid; //대댓글 id
//    private int like; // 좋아요 수
//    private int view; // 조회수
//    private boolean islike; // 좋아요 여부 -> 이걸 따로 빼야할 지 고민중
//}
