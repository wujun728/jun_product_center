package com.pearadmin.pro.common.web.interceptor;

import com.pearadmin.pro.common.constant.TenantConstant;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import javax.annotation.Resource;
import java.util.List;

@Configuration
public class InterceptorConfiguration implements ApplicationListener<ContextRefreshedEvent> {

    @Resource
    private List<SqlSessionFactory> sqlSessionFactoryList;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        this.addMyInterceptor();
    }

    private void addMyInterceptor() {
        for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList) {
            if(TenantConstant.enable) {
                sqlSessionFactory.getConfiguration().addInterceptor(new TenantInterceptor());
            }
            sqlSessionFactory.getConfiguration().addInterceptor(new DataScopeInterceptor());
        }
    }
}
