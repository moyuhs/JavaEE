package com.java.dao.impl;

import com.java.dao.UserDao;
import com.java.domain.User;
import com.java.util.DruidUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 用户操作的DAO
 */
public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate( DruidUtils.getDataSource() );

    @Override
    public List<User> findAll() {
        //使用jdbc操作数据库
        //1.定义sql
        String sql = "select * from user";
        List<User> userList = template.query( sql, new BeanPropertyRowMapper<User>( User.class ) );
        return userList;
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        try {
            String sql = "select * from user where username = ? and password = ?";
            return template.queryForObject( sql, new BeanPropertyRowMapper<>( User.class ), username, password );
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void add(User user) {
        //1.定义sql
        String sql = "insert into user values(null,?,?,?,?,?,?,null,null)";
        //2.执行sql
        template.update( sql, user.getName(), user.getSex(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail() );
    }

    @Override
    public void delete(int id) {
        //1.定义sql
        String sql = "delete from user where id = ?";
        //2.执行sql
        template.update( sql, id );
    }

    @Override
    public User findById(int id) {
        //1.定义sql
        String sql = "select * from user where id = ?";
        //2.执行sql
        User user = template.queryForObject( sql, new BeanPropertyRowMapper<User>( User.class ), id );
        return user;
    }

    @Override
    public void update(User user) {
        //1.定义sql
        String sql = "update user set name = ?,sex = ?,age = ?,address = ?,qq = ?,email = ? where id = ?";
        //2.执行sql
        template.update( sql, user.getName(), user.getSex(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail(), user.getId() );
    }

    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        //1.定义sql
        String sql = "select count(*) from user where 1 = 1 ";
        StringBuilder sb = new StringBuilder( sql );
        //2.遍历map
        //定义参数的集合
        List<Object> params = new ArrayList<Object>();
        for (String key : condition.keySet()) {

            //排除分页条件参数
            if ("currentPage".equals( key ) || "rows".equals( key )) {
                continue;
            }

            //获取value
            String value = condition.get( key )[0];
            //判断value是否有值
            if (value != null && !"".equals( value )) {
                //有值
                sb.append( " and " + key + " like ? " );
                params.add( "%" + value + "%" );//？条件的值
            }
        }
        //2.返回总记录条数
        return template.queryForObject( sb.toString(), Integer.class, params.toArray() );
    }

    @Override
    public List<User> findByPage(int start, int rows, Map<String, String[]> condition) {
        ///1.定义sql
        String sql = "select * from user where 1 = 1 ";
        StringBuilder sb = new StringBuilder( sql );
        //2.遍历map
        //定义参数的集合
        List<Object> params = new ArrayList<Object>();
        for (String key : condition.keySet()) {

            //排除分页条件参数
            if ("currentPage".equals( key ) || "rows".equals( key )) {
                continue;
            }

            //获取value
            String value = condition.get( key )[0];
            //判断value是否有值
            if (value != null && !"".equals( value )) {
                //有值
                sb.append( " and " + key + " like ? " );
                params.add( "%" + value + "%" );//？条件的值
            }
        }
        //添加分页查询
        sb.append( " limit ?,? " );
        //添加分页查询参数值
        params.add( start );
        params.add( rows );
        System.out.println( sb.toString() );
        System.out.println( params );
        return template.query( sb.toString(), new BeanPropertyRowMapper<User>( User.class ), params.toArray() );
    }
}
