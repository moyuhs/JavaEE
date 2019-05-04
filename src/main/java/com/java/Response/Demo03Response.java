package com.java.Response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 字符输出流，解决乱码
 */
@WebServlet("/demo03Response")
public class Demo03Response extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取流对象之前，设置默认编码：ISO-8859-1 设置为：UTF-8
        //resp.setCharacterEncoding( "utf-8" );

        //告诉浏览器，服务端发送的消息数据编码,建议浏览器使用该编码解码
        //resp.setHeader( "content-type","text/html;charset=utf-8" );

        //简单设置编码
        resp.setContentType( "text/html;charset=utf-8" );

        //1.获取字符输出流
        PrintWriter pw = resp.getWriter();

        //2.输出数据
        pw.write( "乱码解决 OK!" );

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost( req, resp );
    }
}
