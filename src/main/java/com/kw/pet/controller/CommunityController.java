//package com.kw.pet.controller;
//
//import static com.kw.pet.config.BaseResponseStatus.*;
//import com.kw.pet.config.BaseException;
//import com.kw.pet.config.BaseResponse;
//import com.kw.pet.domain.community.Community;
//import com.kw.pet.dto.CommunityResponseDto;
//import com.kw.pet.domain.user.User;
//import com.kw.pet.domain.community.CommunityRepository;
//import com.kw.pet.domain.user.UserRepository;
//import java.time.LocalDateTime;
//import java.util.List;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class CommunityController {
//    final Logger logger = LoggerFactory.getLogger(this.getClass());
//    @Autowired
//    private CommunityRepository communityRepository;
//
//
//    @Autowired
//    private UserRepository userRepository;
//
//    //일단 보류!!!
//    //커뮤니티 게시글 작성
//    @PostMapping("/community")
//    public BaseResponse<Community> postContent (@RequestBody CommunityResponseDto communnityResponseDto) {
//        try {
//            Community community = new Community();
//            System.out.println(communnityResponseDto);
//            User post = userRepository.findById(communnityResponseDto.getPostid());
//            User writer = userRepository.findById(communnityResponseDto.getUuid());
//            if(writer == null ){System.out.println("작성자 확인불가"); throw new BaseException(USERS_EMPTY_USER_ID);}
//            community.setPostId(post);
//            community.setWriterId(writer);
//            community.setContent(communnityResponseDto.getContent());
//            community.setCreateDate(LocalDateTime.now());
//            communityRepository.save(community);
//            return new BaseResponse<>(community);
//        }
//        catch (BaseException exception){
//            return new BaseResponse<>(exception.getStatus());
//        }
//    }
//    // 커뮤니티 게시글 내용 보기
//    @GetMapping("/community/:postid")
//    public BaseResponse<List<Community>> getContent(Model model, @RequestParam(value = "PostId") int PostID){
//
//        try {
//            User user = userRepository.findById(PostID);
//            if(user ==null ){System.out.println("작성자 확인 불가"); throw new BaseException(USERS_EMPTY_USER_ID);}
//            List<Community> content = communityRepository.findByPostId(PostID);
//            if(content == null ){System.out.println("유저가 작성한 게시글 없음" ); throw new BaseException(COMMUNITY_EMPTY_USER_POST);}
//            System.out.println("내용 : " + content);
//            return new BaseResponse<>(content);
//        }
//        catch (BaseException exception){
//            return new BaseResponse<>(exception.getStatus());
//        }
//    }
//    //커뮤니티 게시글 삭제
//    @DeleteMapping("/delete")
//    public BaseResponse<Community> delete (@RequestParam(value = "postId") int postId){
//
//        try {
//            Community community = communityRepository.findById(postId);
//            if(community == null ){System.out.println("유효하지 않은 postid"); throw new BaseException(COMMUNITY_EMPTY_POST_ID);}
//            communityRepository.delete(community);
//            return new BaseResponse<>(community);
//        }
//        catch (BaseException exception){
//            return new BaseResponse<>(exception.getStatus());
//        }
//    }
//}
