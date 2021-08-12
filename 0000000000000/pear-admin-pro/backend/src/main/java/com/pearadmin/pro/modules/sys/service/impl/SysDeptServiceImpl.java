package com.pearadmin.pro.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pearadmin.pro.common.tools.support.server.server.Sys;
import com.pearadmin.pro.modules.sys.domain.SysDept;
import com.pearadmin.pro.modules.sys.domain.SysPower;
import com.pearadmin.pro.modules.sys.repository.SysDeptRepository;
import com.pearadmin.pro.modules.sys.service.SysDeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptRepository, SysDept> implements SysDeptService {

    @Resource
    private SysDeptRepository sysDeptRepository;

    @Override
    public List<SysDept> tree() {
        return toTree(sysDeptRepository.selectDept(),"0");
    }

    public List<SysDept> toTree(List<SysDept> sysDept, String parent) {
        List<SysDept> list = new ArrayList<>();
        for (SysDept dept : sysDept) {
            if (parent.equals(dept.getParent())) {
                dept.setChildren(toTree(sysDept, dept.getId()));
                list.add(dept);
            }
        }
        return list;
    }

    @Override
    public List<SysDept> treeAndChildren(String parent) {
        List<SysDept> ds = sysDeptRepository.selectDeptByParentId(parent);
        for (SysDept sd: ds) {
            treeAndChildren(sd,ds);
        }
        return ds;
    }

    private void treeAndChildren(SysDept sd, List<SysDept> ds) {
        List<SysDept> dss = sysDeptRepository.selectDeptByParentId(sd.getId());
        if(dss.size() > 0) {
            for (SysDept sdd: dss) {
                ds.add(sdd);
                treeAndChildren(sdd,ds);
            }
        } else {
            return;
        }
    }
}
