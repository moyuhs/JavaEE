package com.java.servlet;

import com.java.domain.PageBean;
import com.java.domain.User;
import com.java.service.UserService;
import com.java.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 分页查询
 */
@WebServlet("/findUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding( "utf-8" );
        //1.获取参数
        //当前页码
        String currentPage = request.getParameter( "currentPage" );
        //每页显示的条数
        String rows = request.getParameter( "rows" );

        //判断前台是否有需要的数据，没有，付初始值
        if (currentPage == null || "".equals( currentPage )) {
            //初始页码
            currentPage = "1";
        }
        if (rows == null || "".equals( rows )) {
            //初始显示数据条数
            rows = "6";
        }

        //获取条件查询参数
        Map<String, String[]> condition = request.getParameterMap();

        //2.调用service查询
        UserService service = new UserServiceImpl();
        PageBean<User> pb = service.findUserByPage( currentPage, rows, condition );

        //3.将PageBean存入request
        request.setAttribute( "pb", pb );
        //将查询条件存入request域
        request.setAttribute( "condition", condition );

        //4.装发到list.jsp
        request.getRequestDispatcher( "/list.jsp" ).forward( request, response );
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost( request, response );
    }
}
