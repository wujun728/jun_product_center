package com.jun.plugin.system.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.jun.plugin.system.entity.SysDept;
import com.jun.plugin.system.entity.SysUser;
import com.jun.plugin.system.service.DeptService;
import com.jun.plugin.system.service.HomeService;
import com.jun.plugin.system.service.PermissionService;
import com.jun.plugin.system.service.UserService;
import com.jun.plugin.system.vo.resp.HomeRespVO;
import com.jun.plugin.system.vo.resp.PermissionRespNode;
import com.jun.plugin.system.vo.resp.UserInfoRespVO;

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
