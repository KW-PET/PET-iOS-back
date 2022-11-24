package com.kw.pet.domain.pet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface PetRepository extends JpaRepository<Pet, Long> {
}
