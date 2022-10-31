//package com.kw.pet.domain.community;
//
//import com.kw.pet.domain.user.User;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//
//@Data
//@Entity
//@Table(name = "community")
//@NoArgsConstructor
////public class Community implements Serializable {
//public class Community {
//    @Id // primary key
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "community_id")
//    private int communityId;
//    private String content;
//    private String title;
//    private LocalDateTime createDate;
//    private String category;
//    private String tag;
//    private String pic;
//    private int like;
//    private int view;
//    private boolean islike;
////
////    @ManyToOne
////    @JoinColumn(name = "writer_id")
////    private User writerId;
////
////    @ManyToOne
////    @JoinColumn(name = "post_id")
////    private User postId;
//}
//
//
//
