package com.java.jdbc;

import com.java.util.DruidUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 使用Druid工具类
 */
public class Demo02Druid {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            //1.获取连接
            conn = DruidUtils.getConnection();
            //2.定义sql
            String sql = "insert into users value(?,?)";
            //3.获取pstmt对象
            pstmt = conn.prepareStatement( sql );
            //4.给？赋值
            pstmt.setString( 1, "13795725154" );
            pstmt.setString( 2, "123456" );
            //5.执行sql
            int count = pstmt.executeUpdate();
            System.out.println( count );
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //6.释放资源
            DruidUtils.close( pstmt, conn );
        }

    }
}
