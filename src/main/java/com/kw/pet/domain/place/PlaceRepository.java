package com.kw.pet.domain.place;

import com.kw.pet.dto.PlaceAndLikecnt;
import com.kw.pet.dto.PlaceDistanceAndLikecnt;
import com.kw.pet.dto.PlaceResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place, Integer> {
    Place findByPlaceid(int placeid);

    @Query(value = "SELECT * from place where lon is null", nativeQuery = true)
    List<Place> findAllByLonLatIsNull();

    @Query(value = "SELECT *, round((sqrt( power(( xpos - :xpos), 2) + power(( ypos - :ypos), 2)) / 1000), 3) AS distance , (select count(*) from placelike where place.place_id = place_id) AS like_cnt FROM place HAVING distance <= 5 ORDER BY distance", nativeQuery = true)
    List<PlaceDistanceAndLikecnt> findAllByXposYpos(@Param("xpos") Double xpos, @Param("ypos") Double ypos);

    @Query(value = "SELECT *, round((sqrt( power(( xpos - :xpos), 2) + power(( ypos - :ypos), 2)) / 1000), 3) AS distance , (select count(*) from placelike where place.place_id = place_id) AS like_cnt FROM place HAVING distance <= 5 ORDER BY like_cnt DESC", nativeQuery = true)
    List<PlaceDistanceAndLikecnt> findAllByCount(@Param("xpos") Double xpos, @Param("ypos") Double ypos);

    @Query(value = "SELECT *, round((sqrt( power(( xpos - :xpos), 2) + power(( ypos - :ypos), 2)) / 1000), 3) AS distance , (select count(*) from placelike where place.place_id = place_id) AS like_cnt FROM place WHERE name LIKE CONCAT('%',:name,'%') HAVING distance <= 5 ORDER BY distance", nativeQuery = true)
    List<PlaceDistanceAndLikecnt> findAllByName(@Param("xpos") Double xpos, @Param("ypos") Double ypos, @Param("name") String name);

    @Query(value = "SELECT *, round((sqrt( power(( xpos - :xpos), 2) + power(( ypos - :ypos), 2)) / 1000), 3) AS distance , (select count(*) from placelike where place.place_id = place_id) AS like_cnt FROM place WHERE category = :category HAVING distance <= 5 ORDER BY distance", nativeQuery = true)
    List<PlaceDistanceAndLikecnt> findAllByCategory(Double xpos, Double ypos, String category);

    @Query(value = "select p.place_id, p.address, p.category, p.name, p.phone, p.lat, p.lon, (select count(*) from placelike where p.place_id = place_id) AS like_cnt from place p join placelike pl on p.place_id = pl.place_id where pl.user_id = :user_id", nativeQuery = true)
    List<PlaceAndLikecnt> findMyLikePlace(int user_id);

    @Transactional
    @Modifying
    @Query("update place set lon = :xpos, lat = :ypos where place_id = :placeid")
    int updateLonLat(Double xpos, Double ypos, Integer placeid);
}