package com.java.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

/**
 * JedisPool工具类
 * 加载配置文件，配置连接池的参数
 * 提供获取连接的方法
 */
public class JedisPoolUtils {
    private static JedisPool jedisPool;

    static {
        //加载配置文件
        //创建Properties对象
        Properties pro = new Properties();
        //关联文件
        try {
            pro.load( Objects.requireNonNull( JedisPoolUtils.class.getClassLoader().getResourceAsStream( "config/jedis/jedis.properties" ) ) );
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取数据，设置到JedisPoolConfig中
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal( Integer.parseInt( pro.getProperty( "maxTotal" ) ) );
        config.setMaxIdle( Integer.parseInt( pro.getProperty( "maxIdle" ) ) );

        //初始化JedisPool
        jedisPool = new JedisPool( config, pro.getProperty( "host" ), Integer.parseInt( pro.getProperty( "port" ) ) );
    }

    /**
     * 获取连接的方法
     */
    public static Jedis getJedis() {
        return jedisPool.getResource();
    }
}
