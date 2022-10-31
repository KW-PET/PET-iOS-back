package com.kw.pet.config;

import lombok.Getter;

/**
 * 에러 코드 관리
 */
@Getter
public enum BaseResponseStatus {
    /**
     * 1000 : 요청 성공
     */
    SUCCESS(true, 1000, "요청에 성공하였습니다."),


    /**
     * 2000 : Request 오류
     */
    // Common

    // users
    USERS_EMPTY_USER_ID(false, 2010, "유저 아이디 값을 확인해주세요."),
    //community
    COMMUNITY_EMPTY_POST_ID(false, 2020,"유효하지 않은 PostId 입니다."),
    COMMUNITY_EMPTY_USER_POST(false, 2021,"user의 게시글이 없습니다"),

    /**
     * 3000 : Response 오류
     */
    // Common
    RESPONSE_ERROR(false, 3000, "값을 불러오는데 실패하였습니다."),




    /**
     * 4000 : Database, Server 오류
     */
    DATABASE_ERROR(false, 4000, "데이터베이스 연결에 실패하였습니다."),
    SERVER_ERROR(false, 4001, "서버와의 연결에 실패하였습니다.");



    private final boolean isSuccess;
    private final int code;
    private final String content;

    BaseResponseStatus(boolean isSuccess, int code, String content) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.content = content;
    }
}
