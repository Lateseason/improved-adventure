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

public class PeiService {
    private BookDao bookDao = new BookDaoImpl() ;
    private OrderDao orderDao = new OrderDaoImpl();
    private UserDao userDao = new UserDaoImpl() ;

    //业务层方法
   /* public void peiTest(){
        try {
            //打开事务
            Connection conn = null ;
            conn.setAutoCommit(false);//设置手动提交

            bookDao.updateById(new Book(),conn);//成功

            orderDao.updateById(new Order(),conn);//成功

            userDao.save(new User(),conn);//异常
            //事务提交
            //conn.close()
        } catch (SQLException e) {
            e.printStackTrace();
            //事务回滚
            //conn.close()
        }
    }*/
}
