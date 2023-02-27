package com.softeem.servlet;

import com.google.code.kaptcha.servlet.KaptchaServlet;
import com.softeem.bean.User;
import com.softeem.service.UserService;
import com.softeem.service.impl.UserServiceImpl;
import com.softeem.utils.BaseServlet;
import com.softeem.utils.CookieUtils;
import com.softeem.utils.WebUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

@WebServlet(name = "UserServlet", value = "/UserServlet")
public class UserServlet extends BaseServlet {

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        User user = new User();
        WebUtils.copyParamToBean(parameterMap, user);

        UserService userService = new UserServiceImpl();
        try {
            User myuser = userService.login(user);
            if (myuser != null) {
                Cookie nameCookie = new Cookie("username", myuser.getUsername());
                Cookie passCookie = new Cookie("password", myuser.getPassword());
                nameCookie.setMaxAge(60 * 60 * 24 * 7);
                passCookie.setMaxAge(60 * 60 * 24 * 7);
                response.addCookie(nameCookie);
                response.addCookie(passCookie);

                HttpSession session = request.getSession();
                session.setAttribute("user", myuser);
                request.setAttribute("msg", "欢迎回来 !");
                if (request.getParameter("myurl") != null && !request.getParameter("myurl").equals("")) {
                    request.getRequestDispatcher(request.getParameter("myurl")).forward(request, response);
                } else {
                    request.getRequestDispatcher("/pages/user/success.jsp").forward(request, response);
                }

            } else {
                request.setAttribute("msg", "账号名或登录密码不正确");
                request.setAttribute("username", user.getUsername());
                request.setAttribute("password", user.getPassword());
                request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void rootLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        User user = new User();
        WebUtils.copyParamToBean(parameterMap, user);

        UserService userService = new UserServiceImpl();
        try {
            User myuser = userService.rootLogin(user);
            if (myuser != null) {
                Cookie nameCookie = new Cookie("username", myuser.getUsername());
                Cookie passCookie = new Cookie("password", myuser.getPassword());
                nameCookie.setMaxAge(60 * 60 * 24 * 7);
                passCookie.setMaxAge(60 * 60 * 24 * 7);
                response.addCookie(nameCookie);
                response.addCookie(passCookie);

                HttpSession session = request.getSession();
                session.setAttribute("user", myuser);
                request.setAttribute("msg", "欢迎回来 !");
                if (request.getParameter("myurl") != null && !request.getParameter("myurl").equals("")) {
                    request.getRequestDispatcher(request.getParameter("myurl")).forward(request, response);
                } else {
                    request.getRequestDispatcher("/pages/manager/manager.jsp").forward(request, response);
                }

            } else {
                request.setAttribute("msg", "登录密码不正确或权限不足");
                request.setAttribute("username", user.getUsername());
                request.setAttribute("password", user.getPassword());
                request.getRequestDispatcher("/pages/manager/login.jsp").forward(request, response);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        // 获取Session 中的验证码
        String token = (String) session.getAttribute(KAPTCHA_SESSION_KEY);
        // 删除 Session 中的验证码
        request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        String code = request.getParameter("code");
        System.out.println("用户输入的验证码为: " + code);
        System.out.println("session中的验证码为: " + token);

        Map<String, String[]> parameterMap = request.getParameterMap();
        User user = new User();
        WebUtils.copyParamToBean(parameterMap, user);

        request.setAttribute("u", user);
        UserService userService = new UserServiceImpl();
        try {
            if (token.equalsIgnoreCase(code)) {

                if (!userService.existUsername(user.getUsername())) {
                    userService.registerUser(user);
                    session.setAttribute("user", user);
                    request.setAttribute("msg", "注册成功!");
                    request.getRequestDispatcher("/pages/user/success.jsp").forward(request, response);
                } else {
                    request.setAttribute("msg", "用户名已存在请更换");
                    request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("msg", "验证码不正确");
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();//立刻无效
//        session.removeAttribute("user");
        Cookie nameCookie = CookieUtils.findCookie("username", request.getCookies());
        Cookie passCookie = CookieUtils.findCookie("password", request.getCookies());
        if (nameCookie != null) {
            nameCookie.setMaxAge(0);
            response.addCookie(nameCookie);
        }
        if (passCookie != null) {
            passCookie.setMaxAge(0);
            response.addCookie(passCookie);
        }
        response.sendRedirect("index.jsp");
    }
}
