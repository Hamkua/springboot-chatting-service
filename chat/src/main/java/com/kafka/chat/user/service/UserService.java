package com.kafka.chat.user.service;

import com.kafka.chat.user.dao.UserDao;
import com.kafka.chat.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;
    private final BCryptPasswordEncoder passwordEncoder;

    public Boolean signUp(UserDto userDto){
        String username = userDto.getUsername();
        Boolean isExist = userDao.checkUserExist(username);
        if(isExist){
            return false;
        }

        String encodedPassword = passwordEncoder.encode(userDto.getPassword());

        Long userId = userDao.insertUser(username, encodedPassword);
        return true;
    }
}
