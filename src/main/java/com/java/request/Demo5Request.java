package com.java.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//原始页面转发指定页面，转发地址栏不发生改变，只能转发服务器内部资源

@WebServlet("/Demo5request")
public class Demo5Request extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println( "Demo5request被访问了......" );

        //进行转发
        request.getRequestDispatcher( "/Demo6request" ).forward( request, response );
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get 获取请求参数直接调用当前类的doPost方法
        this.doPost( request, response );
    }
}
