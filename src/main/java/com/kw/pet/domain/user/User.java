package com.kw.pet.domain.user;

import com.kw.pet.config.BaseException;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String uuid;
    private String name;
    private String nickname;
    private String email;
    private String token;
    private String fcmtoken;


}
