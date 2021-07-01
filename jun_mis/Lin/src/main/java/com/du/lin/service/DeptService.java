package com.du.lin.service;

import java.util.List;

import com.du.lin.bean.Dept;

public interface DeptService {
    public String getAllDeptJson(int page , int count);
    String deptListForUserAdd();
    String addDept(String deptName);
    String modifyDept(String id , String deptName);
    String deleteDept(String id);
    List<Dept> getAllDept();
}
