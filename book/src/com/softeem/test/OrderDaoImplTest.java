package com.softeem.test;

import com.softeem.bean.Order;
import com.softeem.dao.OrderDao;
import com.softeem.dao.impl.OrderDaoImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.*;

public class OrderDaoImplTest {

    @Test
    public void findAll() {
    }

    @Test
    public void save() throws SQLException {
        OrderDao orderDao = new OrderDaoImpl();
        orderDao.save(new Order("1234567891",new Timestamp(System.currentTimeMillis()),new BigDecimal(100),0, 1));

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
    public void page() throws SQLException {
        OrderDao orderDao = new OrderDaoImpl();
        List<Order> page = orderDao.page(1);
        for (Order order : page) {
            System.out.println("order = " + order);
        }
    }

    @Test
    public void pageRecord() throws SQLException {
        OrderDao orderDao = new OrderDaoImpl();
        Integer integer = orderDao.pageRecord();
        System.out.println("integer = " + integer);
    }

}