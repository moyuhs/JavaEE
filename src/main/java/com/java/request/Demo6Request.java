package com.java.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//转发后的页面，储存一段数据

@WebServlet("/Demo6request")
public class Demo6Request extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println( "Demo6request被访问了......" );

        //储存数据setAttribute
        request.setAttribute( "msg", "Hello it's me" );

        //进行转发
        request.getRequestDispatcher( "/Demo7request" ).forward( request, response );
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get 获取请求参数直接调用当前类的doPost方法
        this.doPost( request, response );
    }
}
