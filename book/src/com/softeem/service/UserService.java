package com.softeem.service;

import com.softeem.bean.User;

import java.sql.SQLException;

public interface UserService {
    void registerUser(User user) throws SQLException;

    User login(User user) throws SQLException;

    User rootLogin(User user) throws SQLException;

    boolean existUsername(String username)throws SQLException;
}
