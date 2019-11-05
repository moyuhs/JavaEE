package com.java.servlet;

import cn.hutool.http.useragent.UserAgentUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * 文件下载
 */
@WebServlet("/downloadServlet")
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding( "UTF-8" );
        resp.setCharacterEncoding( "UTF-8" );
        //1.获取请求参数，文件名称
        String filename = req.getParameter( "filename" );
        //2.使用字节输入流加载文件进内存
        //2.1 找到文件服务器路径
        ServletContext servletContext = this.getServletContext();
        String realPath = servletContext.getRealPath( "/img/" + filename );
        //2.2 用字节流关联
        FileInputStream fis = new FileInputStream( realPath );

        //3.设置response的响应头
        //3.1 设置响应头类型：content-type
        //获取文件的mime类型
        String mimeType = servletContext.getMimeType( filename );
        resp.setHeader( "content-type", mimeType );

        //文件名中文乱码处理
        String header = req.getHeader( "User-Agent" );
        String browser = UserAgentUtil.parse( header ).getBrowser().toString();
        if ("MSIE".equals( browser ) || "Trident".equals( browser )) {
            //IE浏览器处理
            filename = URLEncoder.encode( filename, "UTF-8" );
        } else {
            //非IE处理
            filename = new String( filename.getBytes( "UTF-8" ), "ISO-8859-1" );
        }

        //3.2 设置响应头的打开方式：content-disposition
        resp.setHeader( "content-disposition", "attachment;filename=" + filename );

        //4.将输入流的数据写到输出流中
        ServletOutputStream sos = resp.getOutputStream();
        byte[] bytes = new byte[1024 * 8];
        int len = 0;
        while ((len = fis.read( bytes )) != -1) {
            sos.write( bytes, 0, len );
        }
        fis.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost( req, resp );
    }
}
