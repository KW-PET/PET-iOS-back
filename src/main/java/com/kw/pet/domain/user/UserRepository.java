package com.kw.pet.domain.user;


import com.kw.pet.config.BaseException;
import com.kw.pet.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findById(int userId) throws BaseException;
    User findByUuid(String uuid);
}