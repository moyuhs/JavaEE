package com.java.jdbc;

import com.java.util.JDBCUtils;

import java.sql.*;

/**
 * JDBC的基础使用:ResultSet返回查询结果
 */
public class Demo04JDBC {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
           /* //1.注册驱动
            Class.forName( "com.mysql.jdbc.Driver" );
            //2.获取链接对象
            conn = DriverManager.getConnection( "jdbc:mysql:///bank", "root", "AngelBeats" );*/
            //2.使用工具类进行连接
            conn = JDBCUtils.getConnection();
            //3.定义sql
            String sql = "select * from users";
            //4.获取执行sql对象
            stmt = conn.createStatement();
            //5.执行sql
            rs = stmt.executeQuery( sql );
            //6.处理结果
            //循环判断游标是否是最后一行末尾
            while (rs.next()) {
                String id = rs.getString( "uid" );
                String pwd = rs.getString( "upwd" );
                System.out.println( "用户账号：" + id + ",用户密码：" + pwd );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //7.释放资源
           /*if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }*/
            JDBCUtils.close( rs, stmt, conn );
        }
    }
}
