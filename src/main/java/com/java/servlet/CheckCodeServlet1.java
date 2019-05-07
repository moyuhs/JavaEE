package com.java.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * 网页验证码实现
 */
@WebServlet("/checkCodeServlet1")
public class CheckCodeServlet1 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int width = 100;//验证码宽度
        int height = 50;//验证吗高度

        //1.创建对象，在内存中放图片（验证码图片对象）
        BufferedImage image = new BufferedImage( width, height, BufferedImage.TYPE_INT_RGB );

        //2.美化图片
        //2.1 填充背景色
        Graphics g = image.getGraphics();//画笔对象
        //g.setColor( Color.PINK );
        g.setColor( new Color( 204, 255, 204 ) );//设置画笔颜色
        g.fillRect( 0, 0, width, height );//设置填充区域为当前宽高

        //2.2 画边框
        g.setColor( new Color( 204, 204, 255 ) );
        g.drawRect( 0, 0, width - 2, height - 2 );

        //2.3 画验证码
        String str = "QAZXSWEDCVFRTGBNHYUJMKIOLPqazxswedcvfrtgbnhyujmkiolp0123456789";
        //生成随机角标
        Random ran = new Random();

        //存放用于判断的验证码
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= 5; i++) {
            int index = ran.nextInt( str.length() );
            //获取字符
            char c = str.charAt( index );//随机字符
            //设置字体样式
            g.setFont( new Font( "Microsoft YaHei", Font.BOLD, 24 ) );
            sb.append( c );
            //写验证码
            g.drawString( c + "", width / 6 * i, height / 2 );
        }
        String checkCode_session = sb.toString();
        //将验证码存放入session
        req.getSession().setAttribute( "checkCode_session", checkCode_session );

        //2.4画干扰线
        g.setColor( new Color( 102, 204, 255 ) );

        //随机生成坐标点
        for (int i = 0; i < 10; i++) {
            int x1 = ran.nextInt( width );
            int x2 = ran.nextInt( width );
            int y1 = ran.nextInt( height );
            int y2 = ran.nextInt( height );
            g.drawLine( x1, x2, y1, y2 );
        }

        //3.将图片输出到页面展示
        ImageIO.write( image, "jpg", resp.getOutputStream() );

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost( req, resp );
    }
}
