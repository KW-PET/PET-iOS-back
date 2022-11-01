package com.kw.pet.controller;

import com.kw.pet.config.BaseResponse;
import com.kw.pet.domain.place.Place;
import com.kw.pet.domain.place.PlaceRepository;
import com.kw.pet.dto.PlaceResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

@RestController
public class PlaceController {

    @Autowired
    private PlaceRepository placeRepository;

    public PlaceController(PlaceRepository placeRepository) {this.placeRepository = placeRepository;}

    //장소 정보 가져오기 sort(1: 거리 순, 2: 추천 순)
    @PostMapping("/place")
    public BaseResponse<PlaceResponseDto> test1(@RequestBody PlaceResponseDto place){
        Place placeDTO = new Place();
        if (place.getSort() == 1){
        }else{

        }
        return new BaseResponse<>(place);
    }
//    /**
//     * 두 지점간의 거리 계산
//     *
//     * @param lat1 지점 1 위도
//     * @param lon1 지점 1 경도
//     * @param lat2 지점 2 위도
//     * @param lon2 지점 2 경도
//     * @param unit 거리 표출단위
//     * @return
//     */
//    public static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
//
//        double theta = lon1 - lon2;
//        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
//
//        dist = Math.acos(dist);
//        dist = rad2deg(dist);
//        dist = dist * 60 * 1.1515;
//
//        if (unit == "kilometer") {
//            dist = dist * 1.609344;
//        } else if(unit == "meter"){
//            dist = dist * 1609.344;
//        }
//
//        return (dist);
//    }
//
//
//    // This function converts decimal degrees to radians
//    private static double deg2rad(double deg) {
//        return (deg * Math.PI / 180.0);
//    }
//
//    // This function converts radians to decimal degrees
//    private static double rad2deg(double rad) {
//        return (rad * 180 / Math.PI);
//    }
//    public BaseResponse<PlaceDto> joinPlace(@RequestBody PlaceDto place){
//        Place placeDTO = new Place();
//        placeDTO.setName(placeRepository.findByPlaceid(1));
//        System.out.println("1번 장소" + placeDTO);
//
//        return new BaseResponse<>(place);
//    }
}
