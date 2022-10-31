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

    private String uuid;
    private String name;
    private String nickname;
    private String email;
    private String token;
    private String fcmtoken;

    @Builder
    public User(String uuid, String name, String nickname, String email, String token, String fcmtoken) {
        this.uuid = uuid;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.token = token;
        this.fcmtoken = fcmtoken;
    }
}
