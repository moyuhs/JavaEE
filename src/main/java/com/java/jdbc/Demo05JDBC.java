package com.java.jdbc;

import com.java.util.JDBCUtils;

import java.sql.*;
import java.util.Scanner;

/**
 * 使用JDBC实现用户登录
 * 1.通过键盘录入用户名和密码
 * 2.判断用户是否登录成功
 */
public class Demo05JDBC {
    public static void main(String[] args) {
        //1.键盘输入用户密码
        Scanner sc = new Scanner( System.in );
        System.out.println( "请输入用户名：" );
        String username = sc.nextLine();
        System.out.println( "请输入密码：" );
        String password = sc.nextLine();
        //2.调用方法
        boolean flag = new Demo05JDBC().login( username, password );
        //3.判断结果，输出不同语句
        if (flag) {
            System.out.println( "登录成功！" );
        } else {
            System.out.println( "用户名或密码错误！" );
        }
    }

    /**
     * 登录方法
     */
    public boolean login(String username, String password) {
        if (username == null || password == null) {
            return false;
        }
        //连接数据库，判断是否登录成功
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            //1.获取数据库连接
            conn = JDBCUtils.getConnection();
            //2.定义sql
            String sql = "select * from users where uid = ? and upwd = ?  ";
            //3.获取执行sql的对象
            pstmt = conn.prepareStatement( sql );
            //给？赋值
            pstmt.setString( 1, username );
            pstmt.setString( 2, password );
            //4.执行查询
            rs = pstmt.executeQuery();
            //5.判断
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close( rs, pstmt, conn );
        }
        return false;
    }
}
