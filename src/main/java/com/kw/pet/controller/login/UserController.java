package com.kw.pet.controller.login;

import static com.kw.pet.config.BaseResponseStatus.USERS_EMPTY_USER_ID;

import com.kw.pet.config.BaseException;
import com.kw.pet.config.BaseResponse;
import com.kw.pet.domain.user.UserRepository;
import com.kw.pet.domain.user.User;

import com.kw.pet.dto.UserResponseDto;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/signup") // 회원가입
    public BaseResponse<UserResponseDto> joinUser(@RequestBody UserResponseDto user)  {
        //username , userToken , email, post로 받음
        //  try{
        System.out.println("유저" + user);
        User userDTO = new User();
        userDTO.setName(user.getName());
        userDTO.setNickname(user.getNickname());
        userDTO.setToken(user.getToken());
        userDTO.setFcmtoken(user.getFcmtoken());
        userDTO.setEmail(user.getEmail());
        userDTO.setUuid(user.getUuid());
        userRepository.save(userDTO);
        System.out.println("유저" + userDTO);

        return new BaseResponse<>(user);
        //  }
       /* catch (BaseException exception){
            System.out.println("회원가입오류");
            return new BaseResponse<>(exception.getStatus());
        }
    }*/
    }



    @GetMapping("/mypage") //사용자조회 - 마이페이지로 변경
    public BaseResponse<User> readPost(Model model, @RequestParam(value = "userId") int userID){
        System.out.println("userID"+userID);
        try{
            User user = userRepository.findById(userID);
            if(user == null ){System.out.println("마이페이지 USERS_EMPTY_USER_ID" ); throw new BaseException(USERS_EMPTY_USER_ID);}
            System.out.println(user.getName());
            System.out.println("실행잘됨?");
            return new BaseResponse<>(user);
        }
        catch (BaseException exception){
            System.out.println("user오류");
            return new BaseResponse<>(exception.getStatus());
        }
    }

}
