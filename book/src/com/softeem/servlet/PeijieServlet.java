package com.softeem.servlet;

import com.softeem.utils.BaseServlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "PeijieServlet", value = "/PeijieServlet")
public class PeijieServlet extends BaseServlet {

    protected void mytest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("我是PeijieServlet.mytest方法执行完成....");


        int i = 10 / 0;

    }


}
