package com.java.jedis;

import com.java.util.JedisPoolUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;
import java.util.Map;
import java.util.Set;

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
        Jedis jedis = new Jedis( "localhost", 6379 );
        //2.操作
        jedis.set( "username", "Mitu" );
        //3.关闭连接
        jedis.close();
    }

    /**
     * String 数据结构操作
     */
    @Test
    public void test2() {
        //1.获取连接
        Jedis jedis = new Jedis();//如果使用空参构造，默认值"localhost", 6379端口
        //2.操作
        //储存
        jedis.set( "username", "Mitu" );
        //获取
        String username = jedis.get( "username" );
        System.out.println( username );

        //使用setex()方法可以储存指定时间的key value
        jedis.setex( "activecode", 20, "jihuo" );//将activecode:jihuo键值对存入redis，并在20秒后删除该键值对
        //3.关闭连接
        jedis.close();
    }

    /**
     * hash 数据结构操作 map
     */
    @Test
    public void test3() {
        //1.获取连接
        Jedis jedis = new Jedis();//如果使用空参构造，默认值"localhost", 6379端口
        //2.操作
        //储存
        jedis.hset( "user", "name", "潘多拉" );
        jedis.hset( "user", "age", "19" );
        jedis.hset( "user", "sex", "男" );
        //获取
        String name = jedis.hget( "user", "name" );
        System.out.println( name );
        //获取hash中map的所有数据
        Map<String, String> map = jedis.hgetAll( "user" );
        for (String key : map.keySet()) {
            String value = map.get( key );
            System.out.println( key + ":" + value );
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
        Jedis jedis = new Jedis();//如果使用空参构造，默认值"localhost", 6379端口
        //2.操作
        //储存
        jedis.lpush( "mylist", "a", "b", "c" );//从左边存
        jedis.rpush( "mylist", "a", "b", "c" );//从右边存

        //获取
        List<String> mylist1 = jedis.lrange( "mylist", 0, -1 );
        System.out.println( mylist1 );

        //左弹出
        String element1 = jedis.lpop( "mylist" );
        System.out.println( element1 );
        //右弹出
        String element2 = jedis.rpop( "mylist" );
        System.out.println( element2 );

        List<String> mylist2 = jedis.lrange( "mylist", 0, -1 );
        System.out.println( mylist2 );

        //3.关闭连接
        jedis.close();
    }

    /**
     * set 数据结构操作
     */
    @Test
    public void test5() {
        //1.获取连接
        Jedis jedis = new Jedis();//如果使用空参构造，默认值"localhost", 6379端口
        //2.操作
        //储存
        jedis.sadd( "myset", "java", "php", "c#" );
        jedis.sadd( "myset", "c++", "php", "c#" );
        //获取
        Set<String> myset = jedis.smembers( "myset" );
        for (String s : myset) {
            System.out.println( s );
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
        Jedis jedis = new Jedis();//如果使用空参构造，默认值"localhost", 6379端口
        //2.操作
        //储存
        jedis.zadd( "mysortedset", 10, "亚瑟" );
        jedis.zadd( "mysortedset", 30, "武则天" );
        jedis.zadd( "mysortedset", 15, "艾琳" );
        //获取
        Set<String> mysortedset = jedis.zrange( "mysortedset", 0, -1 );
        System.out.println( mysortedset );

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
