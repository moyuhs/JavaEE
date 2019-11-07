package com.java.cookie;

import cn.hutool.core.date.DateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 使用cookie写出访问提示
 * 在服务器中的Servlet判断是否有一个名为lastTime的cookie
 * 1. 有：不是第一次访问
 * 1. 响应数据：欢迎回来，您上次访问时间为:2018年6月10日11:50:20
 * 2. 写回Cookie：lastTime=2019年6月10日11:50:01
 * 2. 没有：是第一次访问
 * 1. 响应数据：您好，欢迎您首次访问
 * 2. 写回Cookie：lastTime=2019年6月10日11:50:01
 */
@WebServlet("/caseCookie")
public class Case_Cookie extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置响应的消息体的数据格式以及编码
        response.setContentType( "text/html;charset=utf-8" );
        //1.获取cookie
        Cookie[] cookies = request.getCookies();
        //没有cookie为lastTime
        boolean flag = false;
        //2.遍历cookies数组
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                //3.获取cookie的名称
                String name = cookie.getName();
                //4.判断是否是：lastTime
                if ("lastTime".equals( name )) {
                    //有该cookie,不是第一次访问
                    flag = true;

                    //响应数据
                    //获取cookie的value，时间
                    String value = cookie.getValue();
                    //URL解码
                    value = URLDecoder.decode( value, "utf-8" );
                    response.getWriter().write( "<h1>欢迎回来，您上次访问时间为:" + value + "</h1>" );

                    //重新设置cookie的value
                    //获取当前时间的字符串，重新设置cookie的值，重新发送cookie
                    String nowDate = DateUtil.now();
                    //URL编码解决特殊字符
                    nowDate = URLEncoder.encode( nowDate, "utf-8" );
                    cookie.setValue( nowDate );

                    //设置cookie的存活时间一个月
                    cookie.setMaxAge( 60 * 60 * 24 * 30 );
                    response.addCookie( cookie );

                    break;
                }
            }
        }
        //没有cookie,第一次访问
        if (cookies == null || cookies.length == 0 || !flag) {

            //获取当前时间的字符串，重新设置cookie的值，重新发送cookie
            String nowDate = DateUtil.now();
            nowDate = URLEncoder.encode( nowDate, "utf-8" );
            Cookie cookie = new Cookie( "lastTime", nowDate );
            //设置cookie的存活时间一个月
            cookie.setMaxAge( 60 * 60 * 24 * 30 );
            response.addCookie( cookie );

            //响应数据
            response.getWriter().write( "<h1>您好，欢迎您首次访问</h1>" );
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost( request, response );
    }
}
