package com.jun.plugin.project.mock;

import com.jun.plugin.common.feign.UserService;
import com.jun.plugin.common.model.LoginAppUser;
import com.jun.plugin.common.model.SysRole;
import com.jun.plugin.common.model.SysUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

@Configuration
public class FeignClientConfig {

    @Bean
    @Primary
    public UserService feignClient() {
        return new UserServiceMock(); // 返回模拟的FeignClient实例
    }

//    @FeignClient(name = "myService", url = "http://myServiceUrl")
//    public interface MyFeignClient {
//        // 定义FeignClient接口
//    }

    public static class UserServiceMock implements com.jun.plugin.common.feign.UserService {
        @Override
        public SysUser selectByUsername(String username) {
            return null;
        }

        @Override
        public LoginAppUser findByUsername(String username) {
            return null;
        }

        @Override
        public LoginAppUser findByMobile(String mobile) {
            return null;
        }

        @Override
        public LoginAppUser findByOpenId(String openId) {
            return null;
        }

        @Override
        public SysUser selectRoleUser(String username) {
            return null;
        }

        @Override
        public List<SysRole> findRolesByUserId(Long id) {
            return null;
        }
        // 模拟FeignClient的实现
    }
}