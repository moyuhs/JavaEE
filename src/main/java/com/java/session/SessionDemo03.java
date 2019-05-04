package com.java.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * session持久化
 * 将session的id保存在cookie中，通过设置最大存活时间，让session持久不变
 */
@WebServlet("/sessionDemo03")
public class SessionDemo03 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取session
        HttpSession session = request.getSession();
        //2.创建cookie储存session的id
        Cookie c = new Cookie( "JSESSIONID", session.getId() );
        //3.设置持久化时间
        c.setMaxAge( 60 * 60 );
        response.addCookie( c );
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost( request, response );
    }
}
