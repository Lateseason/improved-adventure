package com.softeem.service.impl;

import com.softeem.bean.User;
import com.softeem.dao.UserDao;
import com.softeem.dao.impl.UserDaoImpl;
import com.softeem.service.UserService;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public void registerUser(User user) throws SQLException {
        userDao.save(user);
    }

    @Override
    public User login(User user) throws SQLException {
        return userDao.queryUserByUserNameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public User rootLogin(User user) throws SQLException {
        return userDao.queryRootUser(user.getUsername(),user.getPassword(),user.getUsertype())     ;
    }

    @Override
    public boolean existUsername(String username) throws SQLException {
        return userDao.queryUserByUserName(username) != null;
    }
}
