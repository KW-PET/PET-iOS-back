package com.kw.pet.service;

import com.kw.pet.domain.user.User;
import com.kw.pet.domain.user.UserRepository;
import com.kw.pet.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public User joinUser(User userDTO) {
        User user = userRepository.save(userDTO);
        return user;
    }

    public User getUser(String userUUID) {
        User user = userRepository.findByUuid(userUUID);
        return user;
    }

    public void updateNickname(String nickname, String userUuid) {
        User user = userRepository.findByUuid(userUuid);
        user.setNickname(nickname);
        userRepository.save(user);
    }
}
