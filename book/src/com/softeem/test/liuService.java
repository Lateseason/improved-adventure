package com.softeem.test;

import com.softeem.bean.Book;
import com.softeem.bean.Order;
import com.softeem.bean.User;
import com.softeem.dao.BookDao;
import com.softeem.dao.OrderDao;
import com.softeem.dao.UserDao;
import com.softeem.dao.impl.BookDaoImpl;
import com.softeem.dao.impl.OrderDaoImpl;
import com.softeem.dao.impl.UserDaoImpl;

import java.sql.Connection;
import java.sql.SQLException;

public class liuService {
    private BookDao bookDao = new BookDaoImpl();

    private OrderDao orderDao = new OrderDaoImpl();

    private UserDao userDao = new UserDaoImpl();

    //业务层方法
    public void liuTest() {
        try {
            //打开事务
            Connection conn = null;
            conn.setAutoCommit(false);

            bookDao.updateById(new Book());
            orderDao.updateById(new Order());
            userDao.save(new User());
            //事务提交
            //conn.close
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
