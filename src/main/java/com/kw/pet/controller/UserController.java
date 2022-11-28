package com.kw.pet.controller;

import com.kw.pet.config.BaseException;
import com.kw.pet.config.BaseResponse;
import com.kw.pet.config.JwtService;
import com.kw.pet.domain.user.User;
import com.kw.pet.dto.JsonResponse;
import com.kw.pet.dto.PostResponseDto;
import com.kw.pet.dto.UserResponseDto;
import com.kw.pet.service.UserService;
import com.kw.pet.service.PostService;
import com.kw.pet.util.UuidUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static com.kw.pet.config.BaseResponseStatus.USERS_EMPTY_USER_ID;



@RestController
@RequiredArgsConstructor
public class UserController {

    private final PostService postService;
    private final UserService userService;
    private final JwtService jwtService;


    @PostMapping("/signup") // 회원가입
    public ResponseEntity<Object> joinUser(@RequestBody UserResponseDto.create user)  {
        //username , userToken , email, post로 받음
        //  try{
        System.out.println("유저" + user);
        User newUser = User.builder()
                .name(user.getName())
                .nickname(user.getNickname())
                .token(user.getToken())
                .email(user.getEmail())
                .uuid(user.getUuid()).build();

        User response = userService.joinUser(newUser);

        System.out.println("유저" + response);

        return ResponseEntity.ok(new JsonResponse(true, 200, "joinUser", response));
        //  }
       /* catch (BaseException exception){
            System.out.println("회원가입오류");
            return new BaseResponse<>(exception.getStatus());
        }
    }*/
    }


////uuid로 사용자 조회
//    @GetMapping("/mypage/{userUuid}") //사용자조회 - 마이페이지로 변경
//    public Object readUser(@PathVariable String userUuid){
//        System.out.println("userID"+userUuid);
//        try{
//            User user = userService.getUser(userUuid);
//            if(user == null ){
//                System.out.println("해당 user는 회원가입되지 않은 유저입니다.");
//                throw new BaseException(USERS_EMPTY_USER_ID);
//            }
//            System.out.println(user.getName());
//            System.out.println("mypage 입장 성공");
//            return ResponseEntity.ok(new JsonResponse(true, 200, "mypage 입장", user));
//        }
//        catch (BaseException exception){
//            System.out.println("user오류");
//            return new BaseResponse<>(exception.getStatus());
//        }
//    }
//useridx로 사용자 조회
//    @GetMapping("/user/{userIdx}") //사용자조회 - 마이페이지
//    public Object userMypage(@PathVariable int userIdx){
//        System.out.println("userID"+userIdx);
//        try{
//            User user = userService.getUserIdx(userIdx);
//            if(user == null ){
//                System.out.println("해당 user는 회원가입되지 않은 유저입니다.");
//                throw new BaseException(USERS_EMPTY_USER_ID);
//            }
//            System.out.println(user.getName());
//            System.out.println("user 정보 받아옴");
//            return ResponseEntity.ok(new JsonResponse(true, 200, "user 정보 받아옴", user));
//        }
//        catch (BaseException exception){
//            System.out.println("user오류");
//            return new BaseResponse<>(exception.getStatus());
//        }
//    }


    //닉네임 설정
    @PostMapping("/updateNickname")
    public ResponseEntity updateNickname(@RequestBody UserResponseDto.updateNickname nickname, HttpServletRequest request){
        String userUuid = jwtService.resolveToken(request);
        System.out.println("userUuid : "+userUuid);
        userService.updateNickname(nickname.getNickname(), userUuid);
        return ResponseEntity.ok(new JsonResponse(true, 200, "updateNickname",null));
    }
    //JWT token으로 사용자 정보 받아오기
    @GetMapping("/user") //사용자조회 - 마이페이지로 변경
    public Object userMypage(HttpServletRequest request){
        String userUuid = jwtService.resolveToken(request);
        System.out.println("userUuid : "+userUuid);
        try{
            User user = userService.getUser(userUuid);
            if(user == null ){
                System.out.println("해당 user는 회원가입되지 않은 유저입니다.");
                throw new BaseException(USERS_EMPTY_USER_ID);
            }
            System.out.println(user.getName());
            System.out.println("user 정보 받아옴");
            return ResponseEntity.ok(new JsonResponse(true, 200, "user 정보 받아옴", user));
        }
        catch (BaseException exception){
            System.out.println("user오류");
            return new BaseResponse<>(exception.getStatus());
        }
    }



}
