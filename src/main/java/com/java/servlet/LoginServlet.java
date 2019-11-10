package com.java.servlet;

import com.java.domain.User;
import com.java.service.UserService;
import com.java.service.impl.UserServiceImpl;
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
 * 登录处理
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.设置编码
        req.setCharacterEncoding( "utf-8" );

        //2.获取数据
        //2.1 获取用户输入验证码
        String verifycode = req.getParameter( "captcha" );

        //3.验证码校验
        HttpSession session = req.getSession();
        //3.1 获取存放在session作用域中的验证码，忽略大小写比较，需要String类型，强转
        String CAPTCHA = (String) session.getAttribute( "CAPTCHA" );
        //3.2 确保验证码一次性
        session.removeAttribute( "CAPTCHA" );

        //验证码未输入
        if (CAPTCHA == null || !CAPTCHA.equalsIgnoreCase( verifycode )) {
            //验证码不正确
            req.setAttribute( "login_msg", "验证码错误！" );
            //跳转登录页面
            req.getRequestDispatcher( "/login.jsp" ).forward( req, resp );
            return;
        }

        Map<String, String[]> map = req.getParameterMap();
        //4.封装User对象
        User user = new User();
        try {
            BeanUtils.populate( user, map );
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        //5.调用Servlet查询
        UserService service = new UserServiceImpl();
        User loginUser = service.login( user );

        //6.判断是否登录成功
        if (loginUser != null) {
            //登录成功
            //将用户存入session
            session.setAttribute( "user", loginUser );
            //跳转页面
            resp.sendRedirect( "/index.jsp" );
        } else {
            //登录失败，提示信息
            req.setAttribute( "login_msg", "用户名或密码错误！" );
            //跳转登录页面
            req.getRequestDispatcher( "/login.jsp" ).forward( req, resp );
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost( req, resp );
    }
}
