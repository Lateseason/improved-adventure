package com.softeem.dao;

import com.softeem.bean.Admin;
import com.softeem.utils.BaseInterface;

import java.sql.SQLException;

public interface AdminDao extends BaseInterface<Admin> {
    public Admin queryAdminByUserAndPassword(Admin admin) throws SQLException;
}
