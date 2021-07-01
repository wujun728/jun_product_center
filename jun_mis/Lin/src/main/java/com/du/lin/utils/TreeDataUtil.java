package com.du.lin.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.du.lin.bean.Dept;
import com.du.lin.bean.ShiroUser;
import com.du.lin.bean.TreeNode;
import com.du.lin.dao.DeptMapper;
import com.du.lin.dao.UserMapper;
import com.google.gson.Gson;

public class TreeDataUtil {
	@Autowired
	private DeptMapper deptMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private Gson gson;
	
	private List<TreeNode> nodeList;
	
	public String getTreeDataForNotice(){
		List<Dept> deptList = deptMapper.selectList(null);
		nodeList = new ArrayList<TreeNode>();
		TreeNode node = null;
		TreeNode childNode = null;
		List<TreeNode> childNodeList = null;

		for(int i=0;i< deptList.size() ; i++ ){
			Dept dept = deptList.get(i);
			node = new TreeNode();
			node.setId(i+1);
			node.setText(dept.getName());
//			List<ShiroUser> userList = userMapper.selectByDeptid(dept.getId());
			List<ShiroUser> userList = userMapper.selectList(new EntityWrapper<ShiroUser>().eq("deptid", dept.getId()));
			if (userList.size()>0) {
				childNodeList = new ArrayList<TreeNode>();
				for(int j=0;j< userList.size() ; j++ ){
					ShiroUser user = userList.get(j);
					childNode = new TreeNode();
					childNode.setId(Integer.parseInt((i+1) + "" + (j+1) ));
					childNode.setText(user.getUsername());
					childNode.setState(null);
					childNodeList.add(childNode);
				}
				node.setChildren(childNodeList);
			}
			if (node.getChildren() != null) {
				nodeList.add(node);
			}
		}
		
		return gson.toJson(nodeList);
		
	}
}
