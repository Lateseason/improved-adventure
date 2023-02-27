package com.softeem.filter;

import com.softeem.utils.JdbcUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "TransactionFilter", urlPatterns = "/*")
public class TransactionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            System.out.println("进入TransactionFilter......");
            filterChain.doFilter(servletRequest, servletResponse);
            System.out.println("回到TransactionFilter......");
            JdbcUtils.commitAndClose();// 提交事务
        } catch (Exception e) {
            System.out.println("回到TransactionFilter因为出现了异常");
            JdbcUtils.rollbackAndClose();//回滚事务
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {

    }
}
