package com.java.servlet;

import com.java.dao.UserDao;
import com.java.domain.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 登录逻辑
 * BeanUtils获取数据时，需要注意页面name属性和实体类对应，否者获取页面数据为null.
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.设置编码
        req.setCharacterEncoding( "utf-8" );
        /*2.获取请求参数
        String username = req.getParameter( "username" );
        String pwd = req.getParameter( "pwd" );
        //3.封装user对象
        User loginuser = new User();
        loginuser.setUid( username );
        loginuser.setUpwd( pwd );*/

        //2.获取请求参数
        Map<String, String[]> map = req.getParameterMap();

        //测试是否能获取数据
       /* for (String s : map.keySet()) {
            System.out.println(s);
            String[] strings = map.get( s );
            for (String string : strings) {
                System.out.println(string);
            }
            System.out.println("------------------------");
        }*/

        //3.获取User对象
        User loginuser = new User();
        //3.2使用BeanUtils封装
        try {
            BeanUtils.populate( loginuser, map );
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //4.调用UserDao的login方法
        UserDao dao = new UserDao();
        User user = dao.login( loginuser );

        //5.判断user
        if (user == null) {
            //登录失败
            req.getRequestDispatcher( "/failServlet" ).forward( req, resp );
        } else {
            //登录成功
            //储存数据
            req.setAttribute( "user", user );
            req.getRequestDispatcher( "/successServlet" ).forward( req, resp );
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost( req, resp );
    }
}
