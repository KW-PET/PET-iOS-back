package com.kw.pet.controller.login;


import com.kw.pet.config.BaseException;
import com.kw.pet.service.OAuthService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.websocket.server.PathParam;
import java.util.HashMap;

@RestController
@AllArgsConstructor
@RequestMapping("/kakao")
public class OAuthController {
    //코드 받아오기
    /**
     * 카카오 callback
     * [GET] /login/kakao/callback
     */


    @Autowired
    private OAuthService oauthService;


    //localhost:8080/kakao/login
    @ResponseBody
    @GetMapping("/login")
    public ResponseEntity<String> kakaoCallback(@RequestParam String code) throws BaseException {

//        User user = new User();

        System.out.println("code : " + code);
        String access_Token = oauthService.getKakaoAccessToken(code);
        System.out.println("controller   : " + access_Token);

        HashMap<String, Object> userInfo =  oauthService.getUserInfo(access_Token);
        System.out.println("login Controller : " + userInfo);


        String uuid = (String) userInfo.get("uuid");

//        //TODO : OAuthService
//        //userRepository에 uuid가 있는지 확인하고, 있으면 넘기고 없으면 저장
//        User user = userRepository.findByUuid(uuid);
//        if(user == null) {
//            //insert
//            user.setUuid((String) userInfo.get("uuid"));
//            user.setName((String) userInfo.get("nickname"));
//            user.setToken(access_Token);
//            userRepository.save(user);
//        }else{
//            //update
//            user.setToken(access_Token);
//            userRepository.save(user);
//        }

        return ResponseEntity.ok("사용자 정보 받아옴");

        //    클라이언트의 이메일이 존재할 때 세션에 해당 이메일과 토큰 등록
//        if (userInfo.get("email") != null) {
//            session.setAttribute("userId", userInfo.get("email"));
//            session.setAttribute("access_Token", access_Token);
//        }
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(@RequestParam String code) throws BaseException {

        String access_Token = null;
        HashMap<String, Object> logout =  oauthService.getLogout(access_Token);
        System.out.println("logout 되었음");

        return ResponseEntity.ok("로그아웃");
    }

    @GetMapping("/getfriends")
    public Object getFriends(@PathParam(value = "access_token") String access_token){
//        oauthService.getFriends(access_token);
        System.out.println("accessToken :"+access_token);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity(headers);
        ResponseEntity<String> response;
        headers.add("Authorization", "Bearer " + access_token);

//        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        String redirect_uri="https://kapi.kakao.com/v1/api/talk/friends";

        response=restTemplate.exchange(redirect_uri, HttpMethod.GET,request,String.class);

        return response;
    }
}