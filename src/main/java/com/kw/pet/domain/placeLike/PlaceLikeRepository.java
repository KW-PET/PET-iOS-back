package com.kw.pet.domain.placeLike;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface PlaceLikeRepository extends JpaRepository<PlaceLike, Long> {
}
