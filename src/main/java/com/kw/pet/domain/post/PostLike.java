package com.kw.pet.domain.post;

import com.kw.pet.domain.user.User;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Table(name = "postlike")
@Entity
public class PostLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postlike_id")
    private Long postlikeId;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public PostLike(Post post, User user) {
        this.post = post;
        this.user = user;
    }


}
