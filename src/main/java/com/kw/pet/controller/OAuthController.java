package com.kw.pet.controller;


import com.kw.pet.config.BaseException;
import com.kw.pet.config.JwtService;
import com.kw.pet.dto.JsonResponse;
import com.kw.pet.service.OAuthService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@RequestMapping("/kakao")
public class OAuthController {
    //코드 받아오기
    /**
     * 카카오 callback
     * [GET] /kakao/login/callback
     */
    private final OAuthService oauthService;
    private final JwtService jwtService;

    //code 넘겨받기
    //localhost:8080/kakao/login
//    @GetMapping("/login")
//    public ResponseEntity<JsonResponse> kakaoCallback(@RequestParam String code) throws BaseException {
//
////        User user = new User();
//
//        System.out.println("code : " + code);
//        String access_Token = oauthService.getKakaoAccessToken(code);
////        System.out.println("controller   : " + access_Token);
//
//        HashMap<String, Object> userInfo =  oauthService.getUserInfo(access_Token);
////        System.out.println("login Controller : " + userInfo);
//
//        String uuid = (String) userInfo.get("uuid");
//
////        //TODO : OAuthService
////        //userRepository에 uuid가 있는지 확인하고, 있으면 넘기고 없으면 저장
////        User user = userRepository.findByUuid(uuid);
////        if(user == null) {
////            //insert
////            user.setUuid((String) userInfo.get("uuid"));
////            user.setName((String) userInfo.get("nickname"));
////            user.setToken(access_Token);
////            userRepository.save(user);
////        }else{
////            //update
////            user.setToken(access_Token);
////            userRepository.save(user);
////        }
//        String token = jwtService.createToken(uuid);
//        return ResponseEntity.ok(new JsonResponse(true, 200, "kakao", token));
//
//        //    클라이언트의 이메일이 존재할 때 세션에 해당 이메일과 토큰 등록
////        if (userInfo.get("email") != null) {
////            session.setAttribute("userId", userInfo.get("email"));
////            session.setAttribute("access_Token", access_Token);
////        }
//    }

    //access_token 받아오기
    @GetMapping("/login")
    public ResponseEntity<JsonResponse> kakaoCallback(@RequestParam String access_Token) throws BaseException {
        HashMap<String, Object> userInfo =  oauthService.getUserInfo(access_Token);
        String uuid = (String) userInfo.get("uuid");
        String token = jwtService.createToken(uuid);
        return ResponseEntity.ok(new JsonResponse(true, 200, "kakao", token));

    }

    //로그아웃
    @GetMapping("/logout")
    public Object getLogout(@PathParam(value = "access_token") String access_token){
//        oauthService.getFriends(access_token);
        System.out.println("accessToken :"+access_token);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity(headers);
        ResponseEntity<String> response;
        headers.add("Authorization", "Bearer " + access_token);

//        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        String redirect_uri="https://kapi.kakao.com/v1/user/logout";

        response=restTemplate.exchange(redirect_uri, HttpMethod.GET,request,String.class);

        return response;
    }
}