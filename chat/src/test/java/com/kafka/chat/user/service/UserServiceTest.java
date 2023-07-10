package com.kafka.chat.user.service;

import com.kafka.chat.user.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    @Transactional
    void signUpTest() {
        Boolean isUserInserted = userService.signUp(new UserDto("test-username", "test-password"));

        assertTrue(isUserInserted);
    }
}