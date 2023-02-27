package com.softeem.test;

import com.softeem.bean.OrderItem;
import com.softeem.dao.OrderItemDao;
import com.softeem.dao.impl.OrderItemDaoImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class OrderItemDaoImplTest {

    @Test
    public void findAll() {
    }

    @Test
    public void save() throws SQLException {
        OrderItemDao orderItemDao = new OrderItemDaoImpl();
        orderItemDao.save(new OrderItem(null,"java 从入门到精通", 1,new BigDecimal(100),new BigDecimal(100),"1234567891"));
        orderItemDao.save(new OrderItem(null,"javaScript 从入门到精通", 2,new BigDecimal(100),new BigDecimal(200),"1234567891"));
        orderItemDao.save(new OrderItem(null,"Netty 入门", 1,new BigDecimal(100),new BigDecimal(100),"1234567891"));
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
    public void page() {
    }

    @Test
    public void pageRecord() {
    }

    @Test
    public void testPage() throws SQLException {
        OrderItemDao orderItemDao = new OrderItemDaoImpl();
        List<OrderItem> page = orderItemDao.page(1, "16599511094961");
        for (OrderItem orderItem : page) {
            System.out.println("orderItem = " + orderItem);
        }
    }

    @Test
    public void testPageRecord() throws SQLException {
        OrderItemDao orderItemDao = new OrderItemDaoImpl();
        Integer integer = orderItemDao.pageRecord("16599511094961");
        System.out.println("integer = " + integer);
    }
}