package com.java.dao;

import com.java.domain.User;
import com.java.util.DruidUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * 操作数据库中User表类
 */
public class UserDao {

    //声明JDBCTemplate对象共用
    private JdbcTemplate template = new JdbcTemplate( DruidUtils.getDataSource() );

    /**
     * 登录方法
     *
     * @param loginUser 只有用户名和密码
     * @return user包含所有数据, 没有返回null
     */
    public User login(User loginUser) {
        try {
            //1.编写sql
            String sql = "select * from users where uid = ? and upwd = ?";
            //2.调用query方法
            User user = template.queryForObject( sql,
                    new BeanPropertyRowMapper<User>( User.class ),
                    loginUser.getUid(), loginUser.getUpwd() );
            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();//记录日志
            return null;
        }
    }
}
