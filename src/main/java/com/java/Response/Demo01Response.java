package com.java.Response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Response重定向,地址栏会发生改变
 */
@WebServlet("/demo01Response")
public class Demo01Response extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println( "demo01Response" );
        //访问demo01Response，会自动跳转到/demo02Response资源
        /*1.设置状态码为302
        resp.setStatus( 302 );
        //2.设置响应头location
        resp.setHeader( "location", "/demo02Response" );*/

        //简单的重定向
        resp.sendRedirect( "/demo02Response" );
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost( req, resp );
    }
}
