package com.java.servlet;

import com.java.domain.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 失败页面
 */
@WebServlet("/successServlet")
public class SuccessServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取request作用域中共享的user对象
        Users users = (Users) req.getAttribute( "user" );

        if (users != null) {
            //给页面写一句话
            //设置编码
            resp.setContentType( "text/html;charset=utf-8" );
            //输出
            resp.getWriter().write( "登录成功！" + users.getUid() + "，欢迎您" );
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost( req, resp );
    }
}
