package com.kafka.chat.user.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
@RequiredArgsConstructor
public class UserDao {

    private final JdbcTemplate jdbcTemplate;


    public Long insertUser(String username, String encodedPassword){
        String query = "insert into user_table(username, password) values(?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(query, new String[]{"user_id"});
                pstmt.setString(1, username);
                pstmt.setString(2, encodedPassword);

                return pstmt;
            }
        }, keyHolder);

        Number key = keyHolder.getKey();
        Long userId = key.longValue();

        return userId;
    }

    public Boolean checkUserExist(String username){
        String query = "select case when count(*) = 1 then 1 else 0 end from USER_TABLE where username = ?";

        return this.jdbcTemplate.queryForObject(query, Boolean.class, username);
    }

}
