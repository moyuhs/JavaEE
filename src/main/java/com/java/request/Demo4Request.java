package com.java.request;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

@WebServlet("/javaee/Demo4request")
public class Demo4Request extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //post 获取请求参数

        //post读取数据通过流的方式，可能出现乱码，提前设置编码格式
        request.setCharacterEncoding( "utf-8" );
        //根据参数名称获取参数
        String username = request.getParameter( "username" );
        System.out.println( "Post方式" + username );

        //根据参数名称获取参数值的数组
        String[] hobbies = request.getParameterValues( "hobby" );
        /*for (String hobby : hobbies) {
            System.out.println(hobby);
        }*/

        //获取所有请求的参数名称
        Enumeration<String> parameterNames = request.getParameterNames();
        /*while(parameterNames.hasMoreElements()){
            String name = parameterNames.nextElement();
            System.out.println(name);
            String value = request.getParameter(name);
            System.out.println(value);
            System.out.println("----------------");
        }*/

        // 获取所有参数的map集合
        Map<String, String[]> parameterMap = request.getParameterMap();
        //遍历
        //Set<String> keyset = parameterMap.keySet();
        for (String name : parameterMap.keySet()) {
            //获取键获取值
            String[] values = parameterMap.get( name );
            System.out.println( name );
            for (String value : values) {
                System.out.println( value );
            }
            System.out.println( "-----------------" );
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get 获取请求参数
        /*String pwd = request.getParameter( "pwd" );
        System.out.println("Get方式"+ pwd );*/
        this.doPost( request, response );
    }
}
