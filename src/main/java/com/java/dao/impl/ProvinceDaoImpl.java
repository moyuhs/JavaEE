package com.java.dao.impl;

import com.java.dao.ProvinceDao;
import com.java.domain.Province;
import com.java.util.DruidUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ProvinceDaoImpl implements ProvinceDao {
    //1.声明成员变量 JdbcTemplate
    private JdbcTemplate template = new JdbcTemplate( DruidUtils.getDataSource() );

    @Override
    public List<Province> finAll() {
        //1.定义sql
        String sql = " select * from province ";
        //2.执行sql
        List<Province> list = template.query( sql, new BeanPropertyRowMapper<Province>( Province.class ) );
        return list;
    }
}
