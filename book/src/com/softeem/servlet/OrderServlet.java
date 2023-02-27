package com.softeem.servlet;

import com.softeem.bean.Order;
import com.softeem.bean.OrderItem;
import com.softeem.bean.User;
import com.softeem.service.Cart;
import com.softeem.service.OrderService;
import com.softeem.service.impl.OrderServiceImpl;
import com.softeem.utils.BaseServlet;
import com.softeem.utils.Page;
import com.softeem.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "OrderServlet", value = "/OrderServlet")
public class OrderServlet extends BaseServlet {

    private OrderService orderService = new OrderServiceImpl();

    protected void listOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
        System.out.println("pageNo = " + pageNo);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), 4);
        System.out.println("pageSize = " + pageSize);
        try {
            Page<Order> page = orderService.page(pageNo, pageSize);
            System.out.println("page = " + page);
            page.setUrl("OrderServlet?action=listOrder&");
            request.setAttribute("page", page);
            request.getRequestDispatcher("/pages/order/order.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void listOrderItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
        System.out.println("pageNo = " + pageNo);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), 4);
        System.out.println("pageSize = " + pageSize);
        String orderId = request.getParameter("orderId");
        System.out.println("orderId = " + orderId);
        try {
            Page<OrderItem> page = orderService.OrderItempage(pageNo, pageSize, orderId);
            System.out.println("page = " + page);
            page.setUrl("OrderServlet?action=listOrderItem&orderId=" + orderId + "&");
            request.setAttribute("page", page);
            request.getRequestDispatcher("/pages/order/orderItem.jsp").forward(request, response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 生成订单
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession session = request.getSession();
        // 先获取Cart 购物车对象
        Cart cart = (Cart) session.getAttribute("cart");
        // 获取Userid
        User user = (User) session.getAttribute("user");

        if (user == null) {
            request.setAttribute("msg", "登录超时请重新登录!");
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
        }
        Integer userId = user.getId();
        //	调用orderService.createOrder(Cart,Userid);生成订单

        String orderId = orderService.createOrder(cart, userId);
        //request.setAttribute("orderId", orderId);
        //请求转发到/pages/cart/checkout.jsp
        //request.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req, resp);
        session.setAttribute("orderId", orderId);
        //response.sendRedirect(request.getContextPath() + "/pages/cart/checkout.jsp");
        response.sendRedirect("pages/cart/checkout.jsp");

    }
}
