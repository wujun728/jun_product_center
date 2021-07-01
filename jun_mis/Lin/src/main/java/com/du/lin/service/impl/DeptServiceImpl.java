package com.du.lin.service.impl;

import com.du.lin.bean.Dept;
import com.du.lin.bean.ShiroUser;
import com.du.lin.constant.Constant;
import com.du.lin.dao.DeptMapper;
import com.du.lin.dao.UserMapper;
import com.du.lin.service.DeptService;
import com.du.lin.utils.JqgridUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper mapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JqgridUtil jqgridUtil;
    @Override
    public String getAllDeptJson(int page , int count) {
        List<Dept> all = mapper.selectList(null);
        int toIndex = count * page;
        if (all.size() < toIndex) {
            toIndex = all.size();
        }
        List<Dept> list = all.subList(count * (page - 1), toIndex);
        return jqgridUtil.getJson(list, page + "", all.size() , count);
    }

    @Override
    public String deptListForUserAdd() {
        List<Dept> list = mapper.selectList(null);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i).getId() + ":" + list.get(i).getName() + ";");
        }
        return sb.substring(0, sb.length()-1);
    }

    @Override
    public String addDept(String deptName) {
    	Dept temp = new Dept();
    	temp.setName(deptName);
        Dept dept = mapper.selectOne(temp);
        if (dept != null) {
            return Constant.ERROR_ADD_DEPT_ALREADY_EXISTS;
        }
        dept = new Dept();
        dept.setName(deptName);
        int result = mapper.insert(dept);
        return result+"";
    }

    @Override
    public String modifyDept(String id , String deptName) {
        Dept dept = new Dept();
        dept.setId(Integer.parseInt(id));
        dept.setName(deptName);
        int result = mapper.updateById(dept);
        return result+"";
    }

    @Override
    public String deleteDept(String id) {
        if ("1".endsWith(id)) {
            return Constant.ERROR_CAN_NOT_DELETE_DEFAULT_DEPT;
        }
        ShiroUser updateUser = new ShiroUser();
        updateUser.setDeptid(1);
        updateUser.setId(Integer.parseInt(id));
//        int setUserResult = userMapper.updateByDeptidSelective(Integer.parseInt(id));
        userMapper.updateById(updateUser);
        int result = mapper.deleteById(Integer.parseInt(id));
        return result+"";
    }

	@Override
	public List<Dept> getAllDept() {
		return mapper.selectList(null);
	}
}
