package cc.mrbird.febs.auth.manager;

import cc.mrbird.febs.auth.mapper.MenuMapper;
import cc.mrbird.febs.auth.mapper.UserMapper;
import cc.mrbird.febs.auth.mapper.UserRoleMapper;
import cc.mrbird.febs.common.core.entity.constant.FebsConstant;
import cc.mrbird.febs.common.core.entity.constant.StringConstant;
import cc.mrbird.febs.common.core.entity.system.Menu;
import cc.mrbird.febs.common.core.entity.system.SystemUser;
import cc.mrbird.febs.common.core.entity.system.UserDataPermission;
import cc.mrbird.febs.common.core.entity.system.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户业务逻辑
 *
 * @author MrBird
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserManager {

    private final UserMapper userMapper;
    private final MenuMapper menuMapper;
    private final UserRoleMapper userRoleMapper;

    /**
     * 通过用户名查询用户信息
     *
     * @param username 用户名
     * @return 用户
     */
    public SystemUser findByName(String username) {
        SystemUser user = userMapper.findByName(username);
        if (user != null) {
            List<UserDataPermission> permissions = userMapper.findUserDataPermissions(user.getUserId());
            String deptIds = permissions.stream().map(p -> String.valueOf(p.getDeptId())).collect(Collectors.joining(StringConstant.COMMA));
            user.setDeptIds(deptIds);
        }
        return user;
    }

    /**
     * 通过用户名查询用户权限串
     *
     * @param username 用户名
     * @return 权限
     */
    public String findUserPermissions(String username) {
        List<Menu> userPermissions = menuMapper.findUserPermissions(username);
        return userPermissions.stream().map(Menu::getPerms).collect(Collectors.joining(StringConstant.COMMA));
    }

    /**
     * 注册用户
     *
     * @param username username
     * @param password password
     * @return SystemUser SystemUser
     */
    @Transactional(rollbackFor = Exception.class)
    public SystemUser registUser(String username, String password) {
        SystemUser systemUser = new SystemUser();
        systemUser.setUsername(username);
        systemUser.setPassword(password);
        systemUser.setCreateTime(new Date());
        systemUser.setStatus(SystemUser.STATUS_VALID);
        systemUser.setSex(SystemUser.SEX_UNKNOW);
        systemUser.setAvatar(SystemUser.DEFAULT_AVATAR);
        systemUser.setDescription("注册用户");
        this.userMapper.insert(systemUser);

        UserRole userRole = new UserRole();
        userRole.setUserId(systemUser.getUserId());
        // 注册用户角色 ID
        userRole.setRoleId(FebsConstant.REGISTER_ROLE_ID);
        this.userRoleMapper.insert(userRole);
        return systemUser;
    }
}
