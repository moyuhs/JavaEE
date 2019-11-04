package com.java.util;

import cn.hutool.db.ds.DSFactory;
import cn.hutool.db.ds.druid.DruidDSFactory;
import cn.hutool.setting.Setting;

import javax.sql.DataSource;


/**
 * @Description : 使用HuTool创建Druid连接池
 * @Author：Haotian
 * @Date：2019/11/4 20:16
 */
public class DruidUtils {
    private static DataSource dataSource;

    static {
        //自定义数据库配置文件，Setting读取配置参数有更好的编码兼容性
        Setting setting = new Setting( "config/druid/druid.properties" );
        //自定义需要的连接池实现
        DSFactory dsFactory = DSFactory.setCurrentDSFactory( new DruidDSFactory( setting ) );
        //获取数据源
        dataSource = dsFactory.getDataSource();
    }

    /**
     * @return 连接池资源
     */
    public static DataSource getDataSource() {
        return dataSource;
    }

}
