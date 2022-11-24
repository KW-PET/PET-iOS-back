package com.kw.pet.service;

import com.kw.pet.domain.place.Place;
import com.kw.pet.domain.place.PlaceRepository;
import com.kw.pet.dto.PlaceDistanceAndLikecnt;
import com.kw.pet.dto.PlaceResponseDto;
import lombok.AllArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Service
public class PlaceService {
    private final PlaceRepository placeRepository;

    public HashMap<String, Double> calculatedLonLat(Double lon, Double lat) throws ParseException {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://dapi.kakao.com/v2/local/geo/transcoord.json";

        HashMap<String, Double> returnValue = new HashMap<String, Double>();
        HashMap<String, Object> result = new HashMap<String, Object>();

        HttpHeaders header = new HttpHeaders();
        header.set("Authorization", "KakaoAK d2f5c5d41f5b1e8ef33204e6eef4707d");
        HttpEntity<String> entity = new HttpEntity<>(header);


        UriComponents uri = UriComponentsBuilder.fromHttpUrl(url).queryParam("x", lon).queryParam("y", lat).queryParam("input_coord", "WGS84").queryParam("output_coord", "TM").build();

        ResponseEntity<String> resultMap = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, String.class);

        result.put("statusCode", resultMap.getStatusCodeValue()); //http status code를 확인
        result.put("header", resultMap.getHeaders()); //헤더 정보 확인
        result.put("body", resultMap.getBody()); //실제 데이터 정보 확인

        JSONParser parser = new JSONParser();
        JSONObject kakaoObject = (JSONObject) parser.parse(resultMap.getBody());
        JSONArray documents = (JSONArray) kakaoObject.get("documents");
        JSONObject document = (JSONObject) documents.get(0);
        Double calculatedXpos = (Double) document.get("x");
        Double calculatedYpos = (Double) document.get("y");

        returnValue.put("xpos", calculatedXpos);
        returnValue.put("ypos", calculatedYpos);
        return returnValue;
    }

    public List<PlaceDistanceAndLikecnt> getPlaceListByXposYpos(Double xpos, Double ypos){
        List<PlaceDistanceAndLikecnt> placeList = placeRepository.findAllByXposYpos(xpos, ypos);
        return placeList;
    }

    public List<PlaceDistanceAndLikecnt> getPlaceListByCount(Double xpos, Double ypos){
        List<PlaceDistanceAndLikecnt> placeList = placeRepository.findAllByCount(xpos, ypos);
        return placeList;
    }

    public List<PlaceDistanceAndLikecnt> getPlaceListByCategory(Double xpos, Double ypos, String category){
        List<PlaceDistanceAndLikecnt> placeList = placeRepository.findAllByCategory(xpos, ypos, category);
        return placeList;
    }

}
