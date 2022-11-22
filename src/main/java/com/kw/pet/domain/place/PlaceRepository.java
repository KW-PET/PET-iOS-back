package com.kw.pet.domain.place;

import com.kw.pet.dto.PlaceDistanceAndLikecnt;
import com.kw.pet.dto.PlaceResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place, Integer> {
    Place findByPlaceid(int placeid);

    @Query(value = "SELECT *, round((sqrt( power(( xpos - :xpos), 2) + power(( ypos - :ypos), 2)) / 1000), 3) AS distance , (select count(*) from placelike where place.place_id = place_id) AS like_cnt FROM place HAVING distance <= 5 ORDER BY distance", nativeQuery = true)
    List<PlaceDistanceAndLikecnt> findAllByXposYpos(@Param("xpos") Double xpos, @Param("ypos") Double ypos);

    @Query(value = "SELECT *, round((sqrt( power(( xpos - :xpos), 2) + power(( ypos - :ypos), 2)) / 1000), 3) AS distance , (select count(*) from placelike where place.place_id = place_id) AS like_cnt FROM place HAVING distance <= 5 ORDER BY like_cnt DESC", nativeQuery = true)
    List<PlaceDistanceAndLikecnt> findAllByCount(@Param("xpos") Double xpos, @Param("ypos") Double ypos);

    @Query(value = "SELECT *, round((sqrt( power(( xpos - :xpos), 2) + power(( ypos - :ypos), 2)) / 1000), 3) AS distance , (select count(*) from placelike where place.place_id = place_id) AS like_cnt FROM place WHERE category = :category HAVING distance <= 5 ORDER BY distance", nativeQuery = true)
    List<PlaceResponseDto> findAllByCategory(Double xpos, Double ypos, String category);
}