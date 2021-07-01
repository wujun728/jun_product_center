package com.projectm.org.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.projectm.org.domain.Department;
import com.projectm.org.mapper.DepartmentMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class DepartmentService  extends ServiceImpl<DepartmentMapper, Department> {

    //获取部门信息根据 orgCode和pcode
    public IPage<Map> getDepartmentByOrgCodeAndPCode(IPage<Map> page, String orgCode,String pCode){
        return baseMapper.selectDepartmentByOrgCodeAndPCode(page,orgCode,pCode);
    }

    //根据部门编号获取部门信息
    public Map getDepartmentByCode(String depCode){
        return baseMapper.selectDepartmentByCode(depCode);
    }

    @Transactional
    public Integer delDepartmentByCodes(List<String> codes){
        Integer result = 0;
        for(String code:codes){
            result += baseMapper.deleteDepartmentByCode(code);
        }
        return result;
    }

    public Department getDept(String deptName,String pcode,String orgCode){
        LambdaQueryWrapper<Department> depQW = new LambdaQueryWrapper<Department>();
        depQW.eq(Department::getName,deptName);
        depQW.eq(Department::getPcode,pcode);
        depQW.eq(Department::getOrganization_code,orgCode);
        return baseMapper.selectOne(depQW);
    }

}
