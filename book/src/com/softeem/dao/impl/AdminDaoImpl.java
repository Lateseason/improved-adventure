package com.softeem.dao.impl;

import com.softeem.bean.Admin;
import com.softeem.dao.AdminDao;
import com.softeem.utils.BaseDao;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;
import java.util.List;

public class AdminDaoImpl extends BaseDao implements AdminDao {
    @Override
    public Admin queryAdminByUserAndPassword(Admin admin) throws SQLException {
        return queryRunner.query("select * from t_admin where username = ? and password = ?",
                new BeanHandler<>(Admin.class, getProcessor()), admin.getUsername(), admin.getPassword());
    }

    @Override
    public List<Admin> findAll() throws SQLException {
        return null;
    }

    @Override
    public void save(Admin admin) throws SQLException {

    }

    @Override
    public void updateById(Admin admin) throws SQLException {

    }

    @Override
    public void deleteById(Integer id) throws SQLException {

    }

    @Override
    public Admin findById(Integer id) throws SQLException {
        return null;
    }

    @Override
    public List<Admin> page(Integer pageNumber) throws SQLException {
        return null;
    }

    @Override
    public Integer pageRecord() throws SQLException {
        return null;
    }
}
