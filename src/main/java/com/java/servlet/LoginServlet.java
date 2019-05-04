package com.java.servlet;

import com.java.dao.UserDao;
import com.java.domain.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        //2.获取请求参数
        String username = req.getParameter( "username" );
        String password = req.getParameter( "password" );
        String checkCode = req.getParameter( "checkCode" );

        //3.1 先获取生成的验证码
        HttpSession session = req.getSession();
        String checkCode_session = (String) session.getAttribute( "checkCode_session" );
        //删除session中储存的验证码,验证码使用一次立即销毁
        session.removeAttribute( "checkCode_session" );

        //3.2判断验证码是否正确
        if (checkCode_session != null && checkCode_session.equalsIgnoreCase( checkCode )) {
            //或略大小写比较
            //判断用户名和密码是否一致

            //封装user对象
            User loginuser = new User();
            loginuser.setUid( username );
            loginuser.setUpwd( password );

            //调用UserDao的login方法
            UserDao dao = new UserDao();
            User user = dao.login( loginuser );

            //判断user是否存在
            if (user == null) {
                //登录失败
                //储存提示信息到request
                req.setAttribute( "login_error", "用户名或密码错误" );
                //转发到登录页面
                req.getRequestDispatcher( "/study/jsp/login.jsp" ).forward( req, resp );
            } else {
                //登录成功
                //储存信息，用户信息
                session.setAttribute( "user", user.getUid() );
                //重定向到成功页面
                resp.sendRedirect(  "/study/jsp/success.jsp" );
            }
        } else {
            //验证码不一致
            //储存提示信息到request
            req.setAttribute( "cc_error", "验证码错误" );
            //转发到登录页面
            req.getRequestDispatcher( "/study/jsp/login.jsp" ).forward( req, resp );
        }


        /*//2.获取请求参数
        Map<String, String[]> map = req.getParameterMap();
        //3.获取User对象
        User loginuser = new User();
        //3.2使用BeanUtils封装
        try {
            BeanUtils.populate( loginuser, map );
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost( req, resp );
    }
}
