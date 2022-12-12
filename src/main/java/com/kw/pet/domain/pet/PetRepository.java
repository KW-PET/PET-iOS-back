package com.kw.pet.domain.pet;

import com.kw.pet.domain.comment.Comment;
import com.kw.pet.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findAllByUser_uuid(String userUUid);
    List<Pet> findAllByUser_userId(int userId);
}
