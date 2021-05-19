package com.platform.service.impl;

import com.platform.dao.SysRoleDeptDao;
import com.platform.service.SysRoleDeptService;
import com.platform.utils.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 角色与部门对应关系
 *
 * @author lipengjun
 * @date 2017年9月18日 上午9:18:38
 */
@Service("sysRoleDeptService")
public class SysRoleDeptServiceImpl implements SysRoleDeptService {
    @Autowired
    private SysRoleDeptDao sysRoleDeptDao;

    @Override
    @Transactional
    public void saveOrUpdate(String roleId, List<String> deptIdList) {
        //先删除角色与菜单关系
        sysRoleDeptDao.delete(roleId);

        if (deptIdList.size() == 0) {
            return;
        }

        //保存角色与菜单关系
        Map<String, Object> map = new HashMap<>();
        map.put("roleId", roleId);
        map.put("deptIdList", deptIdList);
        sysRoleDeptDao.save(map);
    }

    @Override
    public List<String> queryDeptIdList(String roleId) {
        return sysRoleDeptDao.queryDeptIdList(roleId);
    }

    @Override
    public List<String> queryDeptIdListByUserId(String userId) {
        return sysRoleDeptDao.queryDeptIdListByUserId(userId);
    }
}
