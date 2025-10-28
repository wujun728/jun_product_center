package io.github.wujun728.admin.rbac.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import io.github.wujun728.admin.common.constants.Constants;
import io.github.wujun728.admin.common.constants.UserStatus;
import io.github.wujun728.admin.common.data.DataListener;
import io.github.wujun728.admin.common.service.DataListenerTask;
import io.github.wujun728.admin.db.service.JdbcService;
import io.github.wujun728.admin.rbac.constants.UserType;
import io.github.wujun728.admin.rbac.data.Enterprise;
import io.github.wujun728.admin.rbac.data.User;
import io.github.wujun728.admin.rbac.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service("userService")
@Slf4j
public class UserServiceImpl implements UserService, DataListenerTask {
    @Resource
    private JdbcService jdbcService;

    @Override
    public List<Enterprise> getUserEnterpriseList(User user) {
        List<Enterprise> list = null;
        if(UserType.Admin.equals(user.getUserType())){
            list = jdbcService.find("select * from enterprise",Enterprise.class);
        }else{
            list = jdbcService.find("select * from enterprise where id in(" +
                    "select enterprise_id id from enterprise_manager " +
                    "where user_id = ? " +
                    "union all " +
                    "select enterprise_id from enterprise_user " +
                    "where user_id = ? ) ",Enterprise.class,user.getId(),user.getId());
        }
        return list;
    }

    @Override
    public User get(Long id) {
        return jdbcService.getById(User.class, id);
    }

    @Override
    public void call(DataListener dataListener, Map<String, Object> context) {
        log.info("updateUser:{}",context);
        Map<String,Object> beforeObj = (Map<String, Object>) context.get("beforeObj");
        Map<String,Object> afterObj = (Map<String, Object>) context.get("afterObj");
        Long userId = (Long) afterObj.get("id");
        String userStatus = (String) afterObj.get("userStatus");

        if(isChange(afterObj,beforeObj,"userCode")
                || isChange(afterObj,beforeObj,"password")
                || isChange(afterObj,beforeObj,"salt")
                || !UserStatus.OnTheJob.equals(userStatus)
        ){
            //修改用户名,密码,离职等,强制踢下线
            String userKey = StrUtil.format("{}:{}", Constants.USER_KEY_PREFIX,userId);
            log.info("change:{}",userKey);
            StpUtil.kickout(userKey);
        }
    }

    private boolean isChange(Map<String,Object> obj,Map<String,Object> beforeObj,String name){
        Object value = obj.get(name);
        Object oldValue = beforeObj.get(name);
        return !Objects.equals(value,oldValue);
    }

}
