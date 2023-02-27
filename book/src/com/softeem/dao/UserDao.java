package com.softeem.dao;

import com.softeem.bean.User;
import com.softeem.utils.BaseInterface;

import java.sql.SQLException;

public interface UserDao extends BaseInterface<User> {
    User queryUserByUserName(String username) throws SQLException;
    User queryUserByUserNameAndPassword(String username,String password) throws SQLException;
    User queryRootUser(String username,String password,Integer usertype) throws SQLException;
}
