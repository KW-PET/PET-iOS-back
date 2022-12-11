package com.kw.pet.controller;

import com.kw.pet.config.JwtService;
import com.kw.pet.domain.place.Place;
import com.kw.pet.domain.place.PlaceRepository;
import com.kw.pet.domain.placeLike.PlaceLike;
import com.kw.pet.domain.placeLike.PlaceLikeRepository;
import com.kw.pet.domain.user.User;
import com.kw.pet.domain.user.UserRepository;
import com.kw.pet.dto.*;
import com.kw.pet.service.PlaceService;
import com.kw.pet.service.UserService;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class PlaceController {

    private final PlaceService placeService;

    @Autowired
    PlaceLikeRepository placeLikeRepository;
    @Autowired
    PlaceRepository placeRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtService jwtService;
    @Autowired
    UserService userService;

    //장소 정보 가져오기 sort(1: 거리 순, 2: 추천 순)
    //TODO
    //authorization header로 user찾아서 likestatus확인해줘야함.
    @PostMapping("/place")
    public ResponseEntity place(@RequestBody PlaceResponseDto.Request placeResponseDto) throws IOException, ParseException {
        if (placeResponseDto.getSort() == 1) {
            HashMap<String, Double> lonLatToTm = placeService.calculatedLonLat(placeResponseDto.getLon(), placeResponseDto.getLat());
            List<PlaceDistanceAndLikecnt> placeList = placeService.getPlaceListByXposYpos(lonLatToTm.get("xpos"), lonLatToTm.get("ypos"));

            return ResponseEntity.ok(new JsonResponse(true, 200, "place", placeList));
        }
        else if(placeResponseDto.getSort() == 2){
            HashMap<String, Double> lonLatToTm = placeService.calculatedLonLat(placeResponseDto.getLon(), placeResponseDto.getLat());
            List<PlaceDistanceAndLikecnt> placeList = placeService.getPlaceListByCount(lonLatToTm.get("xpos"), lonLatToTm.get("ypos"));

            return ResponseEntity.ok(new JsonResponse(true, 200, "place", placeList));
        }
        else {
            return ResponseEntity.badRequest().body(new ErrorResponse(false, 404, "sort 값이 없습니다."));
        }

    }

    @PostMapping("/place/category")
    public ResponseEntity placeCategory(@RequestBody PlaceResponseDto.Request placeResponseDto) throws IOException, ParseException {
        HashMap<String, Double> lonLatToTm = placeService.calculatedLonLat(placeResponseDto.getLon(), placeResponseDto.getLat());
        List<PlaceDistanceAndLikecnt> categoryFilteredPlace = placeService.getPlaceListByCategory(lonLatToTm.get("xpos"), lonLatToTm.get("ypos"), placeResponseDto.getCategory());
        return ResponseEntity.ok(new JsonResponse(true, 200, "placeCategory", categoryFilteredPlace));
    }

    //TODO
    //authorization header로 user찾아서 likestatus확인해줘야함.
    @PostMapping("/place/like")
    public ResponseEntity placeLike(@RequestBody PlaceLikeResponseDto placeLikeResponseDto, HttpServletRequest request) throws IOException, ParseException {
        String userUUid = jwtService.resolveToken(request);
        User user = userService.getUser(userUUid);
        Place place = placeRepository.findByPlaceid(placeLikeResponseDto.getPlace_id());
        PlaceLike placeLike = PlaceLike.builder()
                .place(place)
                .user(user)
                .build();
        PlaceLike newPlaceLike = placeLikeRepository.save(placeLike);

        return ResponseEntity.ok(new JsonResponse(true, 200, "placeLike", newPlaceLike.getPlacelikeid()));
    }

    @PostMapping("/place/search")
    public ResponseEntity placeSearch(@RequestBody PlaceResponseDto.Request placeResponseDto) throws IOException, ParseException {
        HashMap<String, Double> lonLatToTm = placeService.calculatedLonLat(placeResponseDto.getLon(), placeResponseDto.getLat());
        List<PlaceDistanceAndLikecnt> nameFilteredPlace = placeRepository.findAllByName(lonLatToTm.get("xpos"), lonLatToTm.get("ypos"), placeResponseDto.getName());
        return ResponseEntity.ok(new JsonResponse(true, 200, "placeCategory", nameFilteredPlace));
    }


    @GetMapping("/place/change")
    public ResponseEntity placeChange() throws IOException{
        List<Place> placeList = placeRepository.findAllByLonLatIsNull();
        placeList.forEach(item -> {
            try {
                placeRepository.updateLonLat(placeService.calculatedXposYpos(item.getXpos(), item.getYpos()).get("xpos"), placeService.calculatedXposYpos(item.getXpos(), item.getYpos()).get("ypos"), item.getPlaceid());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        });

        return ResponseEntity.ok(new JsonResponse(true, 200, "placeChange", "변환 완료"));
    }
}
