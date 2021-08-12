package com.pearadmin.common.listener;

import cn.hutool.extra.mail.MailAccount;
import com.pearadmin.common.constant.ConfigurationConstant;
import com.pearadmin.common.listener.event.SetupEvent;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Describe: 邮 件 配 置 监 听 器
 * Author: 就 眠 仪 式
 * CreateTime: 2021/2/4 11:22
 */
@Component
public class SetupListener implements ApplicationListener<SetupEvent>, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void onApplicationEvent(SetupEvent setupEvent) {
        MailAccount mailAccount = applicationContext.getBean("mailAccount", MailAccount.class);
        mailAccount.setHost(setupEvent.getMailConfig().get(ConfigurationConstant.MAIN_HOST));
        mailAccount.setPort(Integer.parseInt(setupEvent.getMailConfig().get(ConfigurationConstant.MAIN_PORT)));
        mailAccount.setFrom(setupEvent.getMailConfig().get(ConfigurationConstant.MAIN_FROM));
        mailAccount.setUser(setupEvent.getMailConfig().get(ConfigurationConstant.MAIN_USER));
        mailAccount.setPass(setupEvent.getMailConfig().get(ConfigurationConstant.MAIN_PASS));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
