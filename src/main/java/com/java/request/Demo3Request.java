package com.java.request;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/javaee/Demo3request")
public class Demo3Request extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求消息体--请求参数

        //1.获取字符流
        //BufferedReader br = request.getReader();
        ServletInputStream inputStream = request.getInputStream();
        byte[] bytes =new byte[1024];
        int len = 0;
        while ((len =inputStream.read(bytes)) != -1){
            System.out.println(new String( bytes,0,len ));
        }
        //2.读取数据
        /*String line = null;
        while ((line = br.readLine()) != null) {
            System.out.println( line );
        }*/

        System.out.println("测试代码是否起效果");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
