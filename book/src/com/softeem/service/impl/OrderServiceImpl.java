package com.softeem.service.impl;

import com.softeem.bean.Book;
import com.softeem.bean.CartItem;
import com.softeem.bean.Order;
import com.softeem.bean.OrderItem;
import com.softeem.dao.BookDao;
import com.softeem.dao.OrderDao;
import com.softeem.dao.OrderItemDao;
import com.softeem.dao.impl.BookDaoImpl;
import com.softeem.dao.impl.OrderDaoImpl;
import com.softeem.dao.impl.OrderItemDaoImpl;
import com.softeem.service.Cart;
import com.softeem.service.OrderService;
import com.softeem.utils.Page;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private BookDao bookDao = new BookDaoImpl();
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();


    @Override
    public String createOrder(Cart cart, Integer userId) throws SQLException {
        //添加一个订单数据到数据库中的order表中
        String orderId = "" + System.currentTimeMillis() + userId;
        Order order = new Order();
        order.setOrderId(orderId);
        order.setCreateTime(new Timestamp(System.currentTimeMillis()));
        order.setPrice(cart.getTotalPrice());
        order.setStatus(0);
        order.setUserId(userId);
        orderDao.save (order);


        //此订单中至少有一个单项,至多会有N个,所以要将订单项目都添加到orderItem表中
        Map<Integer, CartItem> items = cart.getItems();
        for (Map.Entry<Integer, CartItem> entry : items.entrySet()) {
            //获取每一个购物车中的商品项
            CartItem cartItem = entry.getValue();
            //转换为每一个订单项
            OrderItem orderItem = new OrderItem(null, cartItem.getName(), cartItem.getCount(), cartItem.getPrice(), cartItem.getTotalPrice(), orderId);
            // 保存订单项到数据库
            orderItemDao.save(orderItem);
            //更新库存和销量
            Book book = bookDao.findById(entry.getValue().getId());//通过图书的主键ID后返回一个图书对象 book
            book.setSales(book.getSales() + entry.getValue().getCount());//设置销量
            book.setStock(book.getStock() - entry.getValue().getCount());//设置库存
            bookDao.updateById(book);
        }
        // 清空购物车
        cart.clear();
        System.out.println("orderId = " + orderId);
        return orderId;
    }


    @Override
    public Page<Order> page(Integer pageNo, Integer pageSize) throws SQLException {
        Page<Order> page = new Page<>();
        Integer totalCount = orderDao.pageRecord();
        System.out.println("totalCount = " + totalCount);
        page.setPageTotalCount(totalCount);
        page.setPageTotal((totalCount + pageSize - 1) / pageSize);
        page.setPageNo(pageNo);
        page.setItems(orderDao.page(page.getPageNo()));
        return page;
    }

    @Override
    public Page<OrderItem> OrderItempage(Integer pageNo, Integer pageSize, String orderId) throws SQLException {
        Page<OrderItem> page = new Page<>();
        Integer totalCount = orderItemDao.pageRecord(orderId);
        System.out.println("totalCount = " + totalCount);
        page.setPageTotalCount(totalCount);
        page.setPageTotal((totalCount + pageSize - 1) / pageSize);
        page.setPageNo(pageNo);
        page.setItems(orderItemDao.page(page.getPageNo(), orderId));
        return page;
    }
}
