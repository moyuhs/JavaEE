package com.java.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Cookie存活时间
 */
@WebServlet("/demo03Cookie")
public class Demo03Cookie extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.创建cookie对象
        Cookie cookie = new Cookie( "msg", "setMaxAge" );
        //设置cookie为当前服务器下都能共享
        cookie.setPath( "/" );
        //2.1设置cookie的存活时间
        //将cookie持久化到硬盘，30秒后会自动删除cookie文件
        cookie.setMaxAge( 30 );
        //3.发送cookie
        response.addCookie( cookie );
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost( request, response );
    }
}
