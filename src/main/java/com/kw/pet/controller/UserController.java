package com.kw.pet.controller;

import com.kw.pet.config.BaseException;
import com.kw.pet.config.BaseResponse;
import com.kw.pet.config.JwtService;
import com.kw.pet.domain.pet.Pet;
import com.kw.pet.domain.pet.PetRepository;
import com.kw.pet.domain.user.User;
import com.kw.pet.dto.*;
import com.kw.pet.service.UserService;
import com.kw.pet.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;

import static com.kw.pet.config.BaseResponseStatus.USERS_EMPTY_USER_ID;



@RestController
@RequiredArgsConstructor
public class UserController {

    private final PostService postService;
    private final UserService userService;
    private final JwtService jwtService;
    private final PetRepository petRepository;


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

    //닉네임 설정
    @PostMapping("/updateNickname")
    public ResponseEntity updateNickname(@RequestBody UserResponseDto.updateNickname nickname, HttpServletRequest request){
        String userUuid = jwtService.resolveToken(request);
        System.out.println("userUuid : "+userUuid);
        userService.updateNickname(nickname.getNickname(), userUuid);
        return ResponseEntity.ok(new JsonResponse(true, 200, "updateNickname",null));
    }
    //JWT token으로 사용자 정보 받아오기
    @GetMapping("/user")
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

    @GetMapping("/user/pet")
    public ResponseEntity getPet(@RequestParam Map<String, Integer> param){
        List<Pet> pets = petRepository.findAllByUser_userId(Integer.parseInt(String.valueOf(param.get("user_id"))));

        return ResponseEntity.ok(new JsonResponse(true, 200, "getPet", pets));
    }



}
