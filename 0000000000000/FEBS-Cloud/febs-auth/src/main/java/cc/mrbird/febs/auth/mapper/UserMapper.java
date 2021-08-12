package cc.mrbird.febs.auth.mapper;

import cc.mrbird.febs.common.core.entity.system.SystemUser;
import cc.mrbird.febs.common.core.entity.system.UserDataPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author MrBird
 */
public interface UserMapper extends BaseMapper<SystemUser> {

    /**
     * 获取用户
     *
     * @param username 用户名
     * @return 用户
     */
    SystemUser findByName(String username);

    /**
     * 获取用户数据权限
     *
     * @param userId 用户id
     * @return 数据权限
     */
    List<UserDataPermission> findUserDataPermissions(Long userId);
}
