package cc.mrbird.system.dao;

import cc.mrbird.common.config.MyMapper;
import cc.mrbird.system.domain.MyUser;
import cc.mrbird.system.domain.UserWithRole;

import java.util.List;

public interface UserMapper extends MyMapper<MyUser> {
    
    List<MyUser> findUserWithDept(MyUser user);

    List<UserWithRole> findUserWithRole(Long userId);

    MyUser findUserProfile(MyUser user);
}