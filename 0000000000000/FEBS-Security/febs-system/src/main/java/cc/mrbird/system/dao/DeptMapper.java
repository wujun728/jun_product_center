package cc.mrbird.system.dao;

import cc.mrbird.common.config.MyMapper;
import cc.mrbird.system.domain.Dept;

import java.util.List;

public interface DeptMapper extends MyMapper<Dept> {
	
	// 删除父节点，子节点变成顶级节点（根据实际业务调整）
	void changeToTop(List<String> deptIds);
}