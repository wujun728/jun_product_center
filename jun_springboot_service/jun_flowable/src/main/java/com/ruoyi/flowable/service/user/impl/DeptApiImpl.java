package com.ruoyi.flowable.service.user.impl;

import cn.hutool.core.collection.CollUtil;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.enums.CommonStatusEnum;
import com.ruoyi.common.utils.collection.CollectionUtils;
import com.ruoyi.flowable.convert.user.DeptConvert;
import com.ruoyi.flowable.domain.dto.user.DeptRespDTO;
import com.ruoyi.flowable.service.user.DeptApi;
import com.ruoyi.system.service.ISysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.ruoyi.common.exception.util.ServiceExceptionUtil.exception;
import static com.ruoyi.flowable.core.enums.user.SysErrorCodeConstants.DEPT_NOT_ENABLE;
import static com.ruoyi.flowable.core.enums.user.SysErrorCodeConstants.DEPT_NOT_FOUND;

/**
 * 部门 API 实现类
 * <p>
 * hasPermi
 *
 * @author wangzongrun
 */
@Service
public class DeptApiImpl implements DeptApi {

    @Autowired
    private ISysDeptService deptService;

    @Override
    public DeptRespDTO getDept(Long id) {
        SysDept sysDept = deptService.selectDeptById(id);
        return DeptConvert.INSTANCE.convert(sysDept);
    }

    @Override
    public List<DeptRespDTO> getDepts(Collection<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return Collections.emptyList();
        }
        List<SysDept> sysDepts = deptService.listByIds(ids);
        return DeptConvert.INSTANCE.convertList(sysDepts);
    }

    @Override
    public void validDepts(Collection<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return;
        }
        // 获得科室信息
        List<SysDept> depts = deptService.listByIds(ids);
        Map<Long, SysDept> deptMap = CollectionUtils.convertMap(depts, SysDept::getDeptId);
        // 校验
        ids.forEach(id -> {
            SysDept dept = deptMap.get(id);
            if (dept == null) {
                throw exception(DEPT_NOT_FOUND);
            }
            if (!CommonStatusEnum.ENABLE.getStatus().equals(Integer.parseInt(dept.getStatus()))) {
                throw exception(DEPT_NOT_ENABLE, dept.getDeptName());
            }
        });
    }
}
