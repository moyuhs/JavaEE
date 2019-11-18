package com.java.jedis;

import com.java.util.JedisPoolUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.lang.System.*;

/**
 * Jedis的测试类
 */
public class JedisTest {
    /**
     * 快速入门
     */
    @Test
    public void test1() {
        //1.获取连接
        Jedis jedis = new Jedis( "121.36.24.140", 6379 );
        jedis.auth( "aiya" );

        //2.操作存
        jedis.set( "username", "艾莉丝" );

        out.println( jedis.get( "username" ) );
        //3.关闭连接
        jedis.close();
    }

    /**
     * String 数据结构操作
     */
    @Test
    public void test2() {
        //1.获取连接，如果使用空参构造，默认值"localhost", 6379端口
        Jedis jedis = new Jedis();
        //2.操作
        //储存
        jedis.set( "username", "Mitu" );
        //获取
        out.println( jedis.get( "username" ) );

        //使用setex()方法可以储存指定时间的key value
        //将activecode:jihuo键值对存入redis，并在20秒后删除该键值对
        jedis.setex( "activecode", 20, "jihuo" );
        //3.关闭连接
        jedis.close();
    }

    /**
     * hash 数据结构操作 map
     */
    @Test
    public void test3() {
        //1.获取连接，如果使用空参构造，默认值"localhost", 6379端口
        Jedis jedis = new Jedis();
        //2.操作
        //储存
        jedis.hset( "user", "name", "潘多拉" );
        jedis.hset( "user", "age", "19" );
        jedis.hset( "user", "sex", "男" );
        //获取
        String name = jedis.hget( "user", "name" );
        out.println( name );
        //获取hash中map的所有数据
        Map<String, String> map = jedis.hgetAll( "user" );
        for (String key : map.keySet()) {
            String value = map.get( key );
            out.println( key + ":" + value );
        }
        //3.关闭连接
        jedis.close();
    }

    /**
     * list 数据结构操作 LinkedList
     */
    @Test
    public void test4() {
        //1.获取连接
        Jedis jedis = new Jedis();
        //2.操作
        //从左边存
        jedis.lpush( "mylist", "a", "b", "c" );
        //从右边存
        jedis.rpush( "mylist", "a", "b", "c" );

        //获取
        List<String> mylist1 = jedis.lrange( "mylist", 0, -1 );
        out.println( mylist1 );

        //左弹出
        String element1 = jedis.lpop( "mylist" );
        out.println( element1 );
        //右弹出
        String element2 = jedis.rpop( "mylist" );
        out.println( element2 );

        List<String> mylist2 = jedis.lrange( "mylist", 0, -1 );
        out.println( mylist2 );

        //3.关闭连接
        jedis.close();
    }

    /**
     * set 数据结构操作
     */
    @Test
    public void test5() {
        //1.获取连接
        Jedis jedis = new Jedis();
        //2.操作
        //储存
        jedis.sadd( "myset", "java", "php", "c#" );
        jedis.sadd( "myset", "c++", "php", "c#" );
        //获取
        Set<String> myset = jedis.smembers( "myset" );
        for (String s : myset) {
            out.println( s );
        }

        //3.关闭连接
        jedis.close();
    }

    /**
     * sortedset 数据结构操作
     */
    @Test
    public void test6() {
        //1.获取连接
        Jedis jedis = new Jedis();
        //2.操作
        //储存
        jedis.zadd( "mysortedset", 10, "亚瑟" );
        jedis.zadd( "mysortedset", 30, "武则天" );
        jedis.zadd( "mysortedset", 15, "艾琳" );
        //获取
        Set<String> mysortedset = jedis.zrange( "mysortedset", 0, -1 );
        out.println( mysortedset );

        //3.关闭连接
        jedis.close();
    }

    /**
     * jedis连接池使用
     */
    @Test
    public void test7() {
        //0.创建一个配置对象
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal( 50 );
        config.setMaxIdle( 10 );
        //1.创建Jedis连接池对象
        JedisPool jedisPool = new JedisPool( config );
        //2.获取连接
        Jedis jedis = jedisPool.getResource();
        //3.使用
        jedis.set( "name", "貂蝉" );

        //3.关闭连接
        jedis.close();
    }

    /**
     * jedis连接池工具类使用
     */
    @Test
    public void test8() {
        //1.通过连接池工具类获取
        Jedis jedis = JedisPoolUtils.getJedis();

        //2.使用
        jedis.set( "name", "20" );

        //3.关闭连接
        jedis.close();
    }
}