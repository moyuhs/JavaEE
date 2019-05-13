package com.java.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.dao.ProvinceDao;
import com.java.dao.impl.ProvinceDaoImpl;
import com.java.domain.Province;
import com.java.service.ProvinceService;
import com.java.util.JedisPoolUtils;
import redis.clients.jedis.Jedis;

import java.util.List;

public class ProvinceServiceImpl implements ProvinceService {
    //声明dao
    private ProvinceDao dao = new ProvinceDaoImpl();

    @Override
    public List<Province> finAll() {
        return dao.finAll();
    }

    /**
     * 使用Redis缓存
     *
     * @return
     */
    @Override
    public String findAllJson() {
        //1.先从redis中查询数据
        //1.1 获取redis的客户端连接
        Jedis jedis = JedisPoolUtils.getJedis();
        String province_json = jedis.get( "province" );

        //2.判断 province_json 是否存在数据
        if (province_json == null || province_json.length() == 0) {
            //redis中没有数据
            System.out.println( "redis中没有数据，查询数据库。。。" );
            //2.1 从数据库中查询数据
            List<Province> ps = dao.finAll();
            //2.2 将list数据转化为json
            ObjectMapper mapper = new ObjectMapper();
            try {
                province_json = mapper.writeValueAsString( ps );
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            //2.3 将json数据存入redis中
            jedis.set( "province", province_json );
            //归还连接
            jedis.close();
        } else {
            System.out.println( "redis中有数据，从redis中读取数据" );
        }
        return province_json;
    }
}
