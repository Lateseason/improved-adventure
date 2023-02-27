package com.softeem.dao.impl;

import com.softeem.bean.User;
import com.softeem.dao.UserDao;
import com.softeem.utils.BaseDao;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User queryUserByUserName(String username) throws SQLException {
        String sql = "select * from t_user where username = ?";
        return queryRunner.query(sql,new BeanHandler<User>(User.class),username);
    }

    @Override
    public User queryUserByUserNameAndPassword(String username, String password) throws SQLException {
        String sql = "select * from t_user where username = ? and password = ?";
        return queryRunner.query(sql,new BeanHandler<>(User.class),username,password);
    }

    @Override
    public User queryRootUser(String username, String password, Integer usertype) throws SQLException {
        String sql = "select * from t_user where username = ? and password = ? and usertype = ?";
        return queryRunner.query(sql,new BeanHandler<>(User.class),username,password,usertype);
    }

    @Override
    public List<User> findAll() throws SQLException {
        return null;
    }

    @Override
    public void save(User user) throws SQLException {
        String sql = "insert into t_user values(null,?,?,?)";
        Long id = queryRunner.insert(sql, new ScalarHandler<Long>(), user.getUsername(), user.getPassword(), user.getEmail());
        user.setId(id.intValue());
    }

    @Override
    public void updateById(User user) throws SQLException {

    }

    @Override
    public void deleteById(Integer id) throws SQLException {

    }

    @Override
    public User findById(Integer id) throws SQLException {
        return null;
    }

    @Override
    public List<User> page(Integer pageNumber) throws SQLException {
        return null;
    }

    @Override
    public Integer pageRecord() throws SQLException {
        return null;
    }
}
