package com.softeem.test;

import com.softeem.bean.Admin;
import com.softeem.dao.AdminDao;
import com.softeem.dao.impl.AdminDaoImpl;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class AdminDaoImplTest {

    @Test
    public void queryAdminByUserAndPassword() throws SQLException {
        AdminDao adminDao = new AdminDaoImpl();
        Admin admin = new Admin();
        admin.setUsername("root");
        admin.setPassword("123456");
        Admin admin1 = adminDao.queryAdminByUserAndPassword(admin);
        System.out.println("admin1 = " + admin1);
    }
}