package com.java.jdbc;

import com.java.util.DruidUtils;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * JdbcTemplate入门
 */
public class Demo01JdbcTemplate {

    public static void main(String[] args) {
        //1.导入jar包
        //2.创建JDBCTemplate对象
        JdbcTemplate template = new JdbcTemplate(  DruidUtils.getDataSource());
        //3.调用方法
        String sql = "update users set upwd = 54321 where uid = ?";
        int count = template.update(sql, "13795725154");
        System.out.println(count);
    }
}
