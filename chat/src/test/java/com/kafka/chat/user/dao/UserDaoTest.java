package com.kafka.chat.user.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    void checkUserExistTest() {
        Boolean isExist = userDao.checkUserExist("test-username");

        assertEquals(isExist, false);
    }


    @Test
    @Transactional
    void insertUserTest(){
        Long userId = userDao.insertUser("test-username", "test-password");

        Boolean isExist = userDao.checkUserExist("test-username");
        assertEquals(isExist, true);

    }
}