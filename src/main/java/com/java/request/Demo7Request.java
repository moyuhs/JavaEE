package com.java.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//转发后的页面，获取数据

@WebServlet("/Demo7request")
public class Demo7Request extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println( "Demo7request被访问了......" );

        //获取对应的值getAttribute
        Object msg = request.getAttribute( "msg" );
        System.out.println( msg );
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get 获取请求参数直接调用当前类的doPost方法
        this.doPost( request, response );
    }
}
