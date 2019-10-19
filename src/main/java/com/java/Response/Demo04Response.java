package com.java.Response;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 字节输出流，解决乱码
 */
@WebServlet("/demo04Response")
public class Demo04Response extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //简单设置编码
        resp.setContentType( "text/html;charset=utf-8" );

        //1.获取字节输出流，默认编码GBK
        ServletOutputStream sos = resp.getOutputStream();

        //2.输出数据
        sos.write( "乱码解决 OK!".getBytes( "utf-8" ) );

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost( req, resp );
    }
}
