package com.platform.service.impl;

import com.platform.dao.SysDeptDao;
import com.platform.entity.SysDeptEntity;
import com.platform.service.SysDeptService;
import com.platform.utils.ShiroUtils;
import com.platform.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("sysDeptService")
public class SysDeptServiceImpl implements SysDeptService {
    @Autowired
    private SysDeptDao sysDeptDao;

    @Override
    public SysDeptEntity queryObject(String deptId) {
        return sysDeptDao.queryObject(deptId);
    }

    @Override
    public List<SysDeptEntity> queryList(Map<String, Object> map) {
        return sysDeptDao.queryList(map);
    }

    @Override
    public void save(SysDeptEntity sysDept) {
        String parentId = sysDept.getParentId();
        String maxId = sysDeptDao.queryMaxIdByParentId(parentId);

        sysDept.setDeptId(StringUtils.addOne(parentId, maxId));
        sysDept.setCreateTime(new Date());
        sysDept.setCreateUser(ShiroUtils.getUserId());
        sysDept.setStatus(0);
        sysDept.setDeptLevel(sysDept.getDeptId().length() / 2);
        sysDeptDao.save(sysDept);
    }

    @Override
    public void update(SysDeptEntity sysDept) {
        sysDept.setUpdateTime(new Date());
        sysDept.setUpdateUser(ShiroUtils.getUserId());
        sysDeptDao.update(sysDept);
    }

    @Override
    public void delete(String deptId) {
        sysDeptDao.delete(deptId);
    }

    @Override
    public List<String> queryDetpIdList(String parentId) {
        return sysDeptDao.queryDetpIdList(parentId);
    }

    @Override
    public String getSubDeptIdList(String deptId) {
        //部门及子部门ID列表
        List<String> deptIdList = new ArrayList<>();

        //获取子部门ID
        List<String> subIdList = queryDetpIdList(deptId);
        getDeptTreeList(subIdList, deptIdList);

        //添加本部门
        deptIdList.add(deptId);

        String deptFilter = StringUtils.join(deptIdList, ",");
        return deptFilter;
    }

    /**
     * 递归
     */
    public void getDeptTreeList(List<String> subIdList, List<String> deptIdList) {
        for (String deptId : subIdList) {
            List<String> list = queryDetpIdList(deptId);
            if (list.size() > 0) {
                getDeptTreeList(list, deptIdList);
            }

            deptIdList.add(deptId);
        }
    }
}
