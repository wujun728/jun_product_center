package io.github.wujun728.system.service.impl;

import io.github.wujun728.system.entity.SysDept;
import io.github.wujun728.system.entity.SysUser;
import io.github.wujun728.system.vo.resp.HomeRespVO;
import io.github.wujun728.system.vo.resp.PermissionRespNode;
import io.github.wujun728.system.vo.resp.UserInfoRespVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import io.github.wujun728.system.service.DeptService;
import io.github.wujun728.system.service.HomeService;
import io.github.wujun728.system.service.PermissionService;
import io.github.wujun728.system.service.UserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 首页
 *
 * @author wujun
 * @version V1.0
 * @date 2020年3月18日
 */
@Service
public class HomeServiceImpl implements HomeService {
    @Resource
    private UserService userService;
    @Resource
    private DeptService deptService;
    @Resource
    private PermissionService permissionService;

    @Override
    public HomeRespVO getHomeInfo(String userId) {
        SysUser sysUser = userService.getById(userId);
        UserInfoRespVO vo = new UserInfoRespVO();

        if (sysUser != null) {
            BeanUtils.copyProperties(sysUser, vo);
            SysDept sysDept = deptService.getById(sysUser.getDeptId());
            if (sysDept != null) {
                vo.setDeptId(sysDept.getId());
                vo.setDeptName(sysDept.getName());
            }
        }
        List<PermissionRespNode> menus = permissionService.permissionTreeList(userId);
        HomeRespVO respVO = new HomeRespVO();
        respVO.setMenus(menus);
        respVO.setUserInfo(vo);
        return respVO;
    }
    
    @Override
    public UserInfoRespVO getUserInfo(String userId) {
    	SysUser sysUser = userService.getById(userId);
    	UserInfoRespVO vo = new UserInfoRespVO();
    	if (sysUser != null) {
    		BeanUtils.copyProperties(sysUser, vo);
    		SysDept sysDept = deptService.getById(sysUser.getDeptId());
    		if (sysDept != null) {
    			vo.setDeptId(sysDept.getId());
    			vo.setDeptName(sysDept.getName());
    		}
    	}
    	return vo;
    }
}
