package com.kw.pet.domain.pet;

import com.kw.pet.domain.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface PetRepository extends JpaRepository<Pet, Long> {

}
