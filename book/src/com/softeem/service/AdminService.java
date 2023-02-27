package com.softeem.service;

import com.softeem.bean.Admin;

import java.sql.SQLException;

public interface AdminService {
    public Admin login(Admin admin) throws SQLException;
}
