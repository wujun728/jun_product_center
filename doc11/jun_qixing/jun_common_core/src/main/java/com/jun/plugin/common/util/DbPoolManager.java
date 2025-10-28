package com.jun.plugin.common.util;

import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import cn.hutool.core.lang.Console;
import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jun.plugin.common.properties.ApiProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;


@Slf4j
public class DbPoolManager {

    private static Lock lock = new ReentrantLock();

    private static Lock deleteLock = new ReentrantLock();

    //所有数据源的连接池存在map里
    static ConcurrentHashMap<String, DruidDataSource> map = new ConcurrentHashMap<>();

    static ConcurrentHashMap<String, ActiveRecordPlugin> configmaps = new ConcurrentHashMap<>();

    public static DruidDataSource init(String dsname,String url,String username,String password,String driver) {
        if (map.containsKey(dsname)) {
            return map.get(dsname);
        } else {
            lock.lock();
            try {
                log.info(Thread.currentThread().getName() + "获取锁");
                if (!map.containsKey(dsname)) {
                    DruidDataSource druidDataSource = new DruidDataSource();
                    druidDataSource.setName(dsname);
                    druidDataSource.setUrl(url);
                    druidDataSource.setUsername(username);
                    druidDataSource.setPassword(password);
                    druidDataSource.setDriverClassName(driver);
                    druidDataSource.setConnectionErrorRetryAttempts(3);       //失败后重连次数
                    druidDataSource.setBreakAfterAcquireFailure(true);
                    map.put(dsname, druidDataSource);
                    log.info("创建Druid连接池成功：{}", dsname);
                }
                return map.get(dsname);
            } catch (Exception e) {
                return null;
            } finally {
                lock.unlock();
            }
        }
    }
    public static Boolean exitst(String dsname) {
        if (map.containsKey(dsname)) {
            return true;
        } else {
            return false;
        }
    }
    public static DruidDataSource get(String dsname) {
        if (map.containsKey(dsname)) {
            return map.get(dsname);
        } else {
            return null;
        }
    }

    //删除数据库连接池
    public static void remove(String dsname) {
        deleteLock.lock();
        try {
            DruidDataSource druidDataSource = map.get(dsname);
            if (druidDataSource != null) {
                druidDataSource.close();
                map.remove(dsname);
            }
        } catch (Exception e) {
            log.error(e.toString());
        } finally {
            deleteLock.unlock();
        }

    }

    public static DruidPooledConnection getPooledConnection(String dsname) throws SQLException {
        DruidDataSource pool = DbPoolManager.get(dsname);
        DruidPooledConnection connection = pool.getConnection();
//        log.info("获取连接成功");
        return connection;
    }


    public static ActiveRecordPlugin getActiveRecordPlugin(String dsname) {
        if (configmaps.containsKey(dsname)) {
            return configmaps.get(dsname);
        } else {
            return null;
        }
    }

    public static String master = "_main";

    public static void initDefaultActiveRecordPlugin() {
        //ApiProperties properties = SpringContextUtil.getBean(ApiProperties.class);
        String url = SpringUtil.getProperty("project.groovy-api.datasource.url");
        String username = SpringUtil.getProperty("project.groovy-api.datasource.username");
        String password = SpringUtil.getProperty("project.groovy-api.datasource.password");
        String driver = SpringUtil.getProperty("project.groovy-api.datasource.driver");
        Console.log("project.groovy-api.datasource.url:{}",url);
        if(StringUtils.isEmpty(url)) {
            Console.log("project.datasource.url:{}", SpringUtil.getProperty("project.datasource.url"));
            url = SpringUtil.getProperty("project.datasource.url");
            username = SpringUtil.getProperty("project.datasource.username");
            password = SpringUtil.getProperty("project.datasource.password");
            driver = SpringUtil.getProperty("project.datasource.driver");
        }
        initActiveRecordPlugin(master,url,username,password,driver);
    }
    public static void initActiveRecordPlugin(String dsname,String url,String username,String password,String driver) {
        if (configmaps.containsKey(dsname)) {
            //return configmaps.get(dsname);
            log.warn("Config have bean created by configName: {}",dsname);
        } else {
            lock.lock();
            try {
                log.info(Thread.currentThread().getName() + "获取锁");
                if (!configmaps.containsKey(dsname)) {
                    DataSource ds = DbPoolManager.init(dsname,url,username,password,driver);
                    //DruidPlugin dp = new DruidPlugin(url, username, password);
                    ActiveRecordPlugin arp = new ActiveRecordPlugin(dsname, ds);
                    arp.setDevMode(true);
                    arp.setShowSql(true);
                    //dp.start();
                    arp.start();
                    log.warn("Config have bean created by configName: {}",dsname);
                    configmaps.put(dsname, arp);
                    log.info("创建Druid连接池成功：{}", dsname);
                }
            } catch (Exception e) {
                //return null;
            } finally {
                lock.unlock();
            }
        }
    }
}
