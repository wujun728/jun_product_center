package io.github.wujun728.project.mock;

import io.github.wujun728.common.feign.UserService;
import io.github.wujun728.common.model.LoginAppUser;
import io.github.wujun728.common.model.SysRole;
import io.github.wujun728.common.model.SysUser;
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

    public static class UserServiceMock implements io.github.wujun728.common.feign.UserService {
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