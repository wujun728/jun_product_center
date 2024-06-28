package io.github.wujun728.project.config.interceptor;

import cn.hutool.extra.spring.SpringUtil;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.DbKit;
import io.github.wujun728.rest.util.DataSourcePool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static io.github.wujun728.rest.util.DataSourcePool.main;

@Slf4j
@Component
public class DataSourceInit implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        String url = SpringUtil.getProperty("spring.datasource.url");
        String username = SpringUtil.getProperty("spring.datasource.username");
        String password = SpringUtil.getProperty("spring.datasource.password");
        String driver = SpringUtil.getProperty("spring.datasource.driver-class-name");
        url = "jdbc:mysql://localhost:3306/db_oa?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=GMT%2b8&zeroDateTimeBehavior=convertToNull&useInformationSchema=true";
        DataSourcePool.init("oa",url,username,password,driver);
        //Db.init("oa",DataSourcePool.get("oa"));
        if(DbKit.getConfig("oa") == null){
            //Db.init(main, DataSourcePool.get(DbKit.MAIN_CONFIG_NAME));
            //DruidPlugin dp = new DruidPlugin(url, username, password);
            ActiveRecordPlugin arp = new ActiveRecordPlugin("oa", DataSourcePool.get("oa"));
            arp.setDevMode(true);
            arp.setShowSql(true);
            //dp.start();
            arp.start();
            log.warn("Config have bean created by configName: {}",1);
        }
    }
}
