package com.platform.service.impl;

import com.platform.dao.SysRoleMenuDao;
import com.platform.service.SysRoleMenuService;
import com.platform.utils.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 角色与菜单对应关系
 *
 * @author lipengjun
 * @date 2016年9月18日 上午9:44:35
 */
@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl implements SysRoleMenuService {
    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;

    @Override
    @Transactional
    public void saveOrUpdate(String roleId, List<String> menuIdList) {
        if (menuIdList.size() == 0) {
            return;
        }
        //先删除角色与菜单关系
        sysRoleMenuDao.delete(roleId);

        //保存角色与菜单关系
        Map<String, Object> map = new HashMap<>();
        map.put("id", IdUtil.createIdbyUUID());
        map.put("roleId", roleId);
        map.put("menuIdList", menuIdList);
        sysRoleMenuDao.save(map);
    }

    @Override
    public List<String> queryMenuIdList(String roleId) {
        return sysRoleMenuDao.queryMenuIdList(roleId);
    }

}
