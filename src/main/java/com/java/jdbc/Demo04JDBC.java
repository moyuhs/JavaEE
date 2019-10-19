package com.java.jdbc;

import com.java.util.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC的基础使用:ResultSet返回查询结果
 */
public class Demo04JDBC {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            //1.使用工具类获取连接对象
            conn = JdbcUtils.getConnection();
            //2.定义查询sql
            String sql = "select * from users";
            //3.获取执行sql对象
            stmt = conn.createStatement();
            //4.执行sql
            rs = stmt.executeQuery( sql );
            //5.处理结果
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
            JdbcUtils.close( rs, stmt, conn );
        }
    }
}
