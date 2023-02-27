package com.softeem.test;

import com.softeem.bean.User;
import com.softeem.dao.UserDao;
import com.softeem.dao.impl.UserDaoImpl;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class UserDaoImplTest {

    private UserDao userDao = new UserDaoImpl();

    @Test
    public void queryUserByUsername() {
        try {
            User user = userDao.queryUserByUserName("peijie");
            System.out.println("user = " + user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        try {
            User user = userDao.queryUserByUserNameAndPassword("admin" , "123");
            System.out.println("user = " + user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void save() {
        User user = new User(null,"peijie","123456","123@qq.com",null);
        try {
            userDao.save(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void findAll() {
    }

    @Test
    public void updateById() {
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void page() {
    }

    @Test
    public void pageRecord() {
    }
}