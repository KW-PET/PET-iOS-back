package com.kw.pet.domain.post;

import com.kw.pet.domain.BaseTime;
import com.kw.pet.domain.comment.Comment;
import com.kw.pet.domain.user.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "post")
@NoArgsConstructor
public class Post extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = true)
    private String tag;

    @Column(nullable = false)
    private String category;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int view;


    @Column(length = 500)
    private String pic;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;



    @Builder
    public Post(String title, String content, String writer, int view, User user, String tag, String pic, String category) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.tag = tag;
        this.category = category;
        this.view = view;
        this.pic = pic;
        this.user = user;
    }

    /* 게시글 수정 */
    public void update(String title, String content, String tag, String category, String pic) {
        this.title = title;
        this.content = content;
        this.tag = tag;
        this.category = category;
        this.pic = pic;
    }

}
