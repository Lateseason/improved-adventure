package com.softeem.service.impl;

import com.softeem.bean.Admin;
import com.softeem.dao.AdminDao;
import com.softeem.dao.impl.AdminDaoImpl;
import com.softeem.service.AdminService;

import java.sql.SQLException;

public class AdminServiceImpl implements AdminService {
    private AdminDao adminDao = new AdminDaoImpl();
    @Override
    public Admin login(Admin admin) throws SQLException {
        return adminDao.queryAdminByUserAndPassword(admin);
    }
}
