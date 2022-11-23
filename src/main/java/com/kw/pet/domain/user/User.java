package com.kw.pet.domain.user;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.kw.pet.domain.BaseTime;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Table(name = "user")
@NoArgsConstructor
@Entity
//public class User implements Serializable {
public class User extends BaseTime {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;
//    private Long userId;

    private String uuid;
    private String name;
    private String nickname;
    private String email;
    private String token; //access_token
    private String fcm_token;

    @Builder
    public User(String uuid, String name, String nickname, String email, String token, String fcm_token) {
        this.uuid = uuid;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.token = token;
        this.fcm_token = fcm_token;
    }
}
