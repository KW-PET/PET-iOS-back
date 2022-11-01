package com.kw.pet.domain.place;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Integer> {
    Place findByPlaceid(int placeid);
    List<Place> findByXposLessThan(double xpos);

}