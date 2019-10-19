package com.java.jdbc;

import com.java.util.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Author：Haotian
 * @Date：2019/10/19 18:23
 */
public class Demo06Jdbc {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        //事务练习
        try {
            //获取链接对象
            conn = JdbcUtils.getConnection();
            //开启事务
            conn.setAutoCommit( false );
            //定义sql
            String sql1 = "update users set money = money + ? where uid = ?";
            String sql2 = "update users set money = money - ? where uid = ?";
            //获取执行sql对象
            pstmt1 = conn.prepareStatement( sql1 );
            pstmt2 = conn.prepareStatement( sql2 );
            pstmt1.setDouble( 1, 500 );
            pstmt1.setString( 2, "admin" );
            //执行sql
            pstmt1.executeUpdate();
            pstmt2.setDouble( 1, 500 );
            pstmt2.setString( 2, "zero" );
            pstmt2.executeUpdate();
            //int count = 3 / 0;
            //提交事务
            conn.commit();
        } catch (Exception e) {
            //出现异常进行事务回滚
            if (conn != null) {
                //对象存在进行回滚
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            JdbcUtils.close( pstmt1, conn );
            JdbcUtils.close( pstmt2, null );
        }
    }
}
