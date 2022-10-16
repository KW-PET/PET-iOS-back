package com.kw.pet.dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class UserResponseDto {
    private String uuid;
    private String name;
    private String nickname;
    private String email;
    private String location;
    private String token;
    private String fcmtoken;

}
