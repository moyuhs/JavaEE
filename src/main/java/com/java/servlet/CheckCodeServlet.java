package com.java.servlet;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.lang.Console;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;

/**
 * @Description :使用HuTool创建验证码
 * @Author：Haotian
 * @Date：2019/11/4 13:41
 */
@WebServlet("/checkCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //服务器通知浏览器不要缓存
        response.setHeader( "pragma", "no-cache" );
        response.setHeader( "cache-control", "no-cache" );
        response.setHeader( "expires", "0" );
        //获取输出流
        ServletOutputStream outputStream = response.getOutputStream();
        //使用CaptchaUtil创建验证码
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha( 100, 40, 4, 40 );
        //获取验证码
        String checkCode = lineCaptcha.getCode();
        //将验证码放入HttpSession中
        request.getSession().setAttribute( "CHECKCODE_SERVER", checkCode );
        //将验证码输出到浏览器
        lineCaptcha.write( outputStream );
        //关闭输出流
        outputStream.close();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost( request, response );
    }
}
