package com.java.jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @Author：Haotian
 * @Date：2019/6/12 18:43
 */
public class RedisDemo {
    @Test
    public void test1() {
        //连接到linux的redis数据库
        Jedis jedis = new Jedis( "39.105.145.74", 6379 );
        jedis.auth( "123" );
        //选择要操作的数据库
        jedis.select( 1 );

        //存数据【五种类型】
        //String类型
        jedis.set( "welcome", "欢迎储存string类型的数据" );

        //Hash类型
        jedis.hset( "user:1001", "username", "潘多拉" );
        jedis.hset( "user:1001", "age", "0" );
        jedis.hset( "user:1001", "sex", "未知" );

        //List类型
        jedis.lpush( "Country", "中国", "法国", "日本" );

        //set类型
        jedis.sadd( "girl", "艾莉丝", "樱子" );

        //Sortedset,可排序
        jedis.zadd( "phone", 8400, "iphone X" );
        jedis.zadd( "phone", 3999, "Mi8" );
        jedis.zadd( "phone", 5499, "HuaWei" );

        //取数据
        System.out.println( jedis.get( "welcome" ) );

        //关闭连接
        jedis.close();
    }

    @Test
    public void testJedisPool() {
        //创建连接池对象
        JedisPool jedisPool = new JedisPool( "39.105.145.74", 6379 );
        //从连接池中获取连接
        Jedis jedis = jedisPool.getResource();
        jedis.auth( "123" );
        jedis.select( 1 );
        String result = jedis.get( "welcome" );
        System.out.println( result );
        //关闭连接
        jedis.close();
        //关闭连接池
        jedisPool.close();
    }


}
