package com.kw.pet.dto;

import lombok.*;

import java.io.Serializable;


public class UserResponseDto {
    private String uuid;
    private String name;
    private String nickname;
    private String email;
    private String token;
    private String fcmtoken;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Response {
        private String uuid;
        private String name;
        private String nickname;
        private String email;
        private String token;
        private String fcmtoken;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class create{
        private String uuid;
        private String name;
        private String nickname;
        private String email;
        private String token;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class updateNickname {
        private String nickname;
    }


}
