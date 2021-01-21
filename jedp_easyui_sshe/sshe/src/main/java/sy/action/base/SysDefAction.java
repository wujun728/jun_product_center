package sy.action.base;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;

import sy.action.BaseAction;
import sy.action.EnumSysDefType;
import sy.model.base.SessionInfo;
import sy.model.base.Syorganization;
import sy.model.base.Syresource;
import sy.model.base.Syrole;
import sy.model.base.SysDef;
import sy.model.base.Syuser;
import sy.model.easyui.Grid;
import sy.model.easyui.Json;
import sy.model.easyui.Tree;
import sy.service.base.SyresourceServiceI;
import sy.service.base.SysDefServiceI;
import sy.service.base.SyuserServiceI;
import sy.util.base.BeanUtils;
import sy.util.base.ConfigUtil;
import sy.util.base.HqlFilter;
import sy.util.base.IpUtil;
import sy.util.base.MD5Util;

/**
 * 字典
 * 
 * action访问地址是/base/sysdef.sy
 * 
 */
@SuppressWarnings("serial")
@Namespace("/base")
@Action
public class SysDefAction extends BaseAction<SysDef> {
	private static final Logger logger = Logger.getLogger(SysDefAction.class);

	private String type;
	
	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * 获得一个对象 由于sysdef 结构的主键是 int 类型，必须重写这个方法
	 */
	public void getById() {
		if (Integer.valueOf(id) >= 0) {
			writeJson(service.getById(Integer.valueOf(id)));
		} else {
			Json j = new Json();
			j.setMsg("主键不可为空！");
			writeJson(j);
		}
	}
	
	/**
	 * 删除一个对象
	 */
	public void delete() {
		Json json = new Json();
		if (Integer.valueOf(id) >= 0) {
			SysDef t = ((SysDefServiceI) service).getSysDefById(Integer
					.valueOf(id));
			if(t.getType() == (short)0){
				//跟节点，属于系统定义的。不允许删除
				json.setSuccess(true);
				json.setMsg("删除失败！根节点，属于系统定义的。不允许删除!请联系管理员解决!!");
			}else{
				service.delete(t);
				json.setSuccess(true);
				json.setMsg("删除成功！");	
			}
		}
		writeJson(json);
	}

	/**
	 * 注入业务逻辑，使当前action调用service.xxx的时候，直接是调用基础业务逻辑
	 * 
	 * 如果想调用自己特有的服务方法时，请使用((TServiceI) service).methodName()这种形式强转类型调用
	 * 
	 * @param service
	 */
	@Autowired
	public void setService(SysDefServiceI service) {
		this.service = service;
	}

	/**
	 * 保存
	 */
	public void save() {
		Json json = new Json();
		if (data != null) {
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
					ConfigUtil.getSessionInfoName());
			SysDef t = super.service.getById(Integer.valueOf(data.getSysCode()));
			if(t != null){
				json.setMsg("系统编码重复 ！");
				json.setSuccess(false);
				writeJson(json);
				
				return;
			}
			((SysDefServiceI) service)
					.saveSysDef(data, sessionInfo.getUserId());
			json.setSuccess(true);
		}
		writeJson(json);
	}

	/**
	 * 更新资源
	 */
	public void update() {
		Json json = new Json();
		if (data.getSysCode() > 0) {
			if (data.getSysDef() != null
					&& data.getSysCode() == data.getSysDef().getSysCode()) {
				json.setMsg("父资源不可以是自己！");
			} else {
				((SysDefServiceI) service).updateSysDef(data);
				json.setSuccess(true);
			}
		}
		writeJson(json);
	}

	/**
	 * 获得资源treeGrid
	 */
	public void treeGrid() {
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		// hqlFilter
		// .addFilter("QUERY_user#id_S_EQ", sessionInfo.getUser().getId());
		List<SysDef> list = ((SysDefServiceI) service)
				.sysDefTreeGrid(hqlFilter);

		logger.info(" 获得资源treeGrid , 数量:" + list.size());
		logger.info(list.toString());

		writeJson(list);
	}

	/**
	 * 获得主菜单tree，也用于获得上级资源菜单combotree
	 */
	public void doNotNeedSecurity_getMainMenu() {
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		// hqlFilter
		// .addFilter("QUERY_user#id_S_EQ", sessionInfo.getUser().getId());
		// hqlFilter.addFilter("QUERY_t#level_S_EQ", "0");// 0就是只查菜单

		List<SysDef> resources = ((SysDefServiceI) service)
				.getMainMenu(hqlFilter);
		List<Tree> tree = new ArrayList<Tree>();
		for (SysDef sysDef : resources) {
			Tree node = new Tree();

			BeanUtils.copyNotNullProperties(sysDef, node, new String[] { "id",
					"pid" });
			node.setPid(sysDef.getPid() + "");
			node.setId(sysDef.getSysCode() + "");
			node.setText(sysDef.getSysName());
			Map<String, String> attributes = new HashMap<String, String>();
			node.setAttributes(attributes);
			tree.add(node);
		}
		writeJson(tree);
	}

	/**
	 * 根据简称获得数据
	 * 
	 * @param sname
	 * @return
	 */
	private SysDef getSysDef(String sname) {
		List<SysDef> list = ((SysDefServiceI) service).getAllList();
		if (list == null || (list != null && list.isEmpty())) {
			list = ((SysDefServiceI) service).getAllNoCacheList();
		}
		for (SysDef sysDef : list) {
			if (sysDef.getsName()  != null
					&& sysDef.getsName().equalsIgnoreCase(sname)) {

				return sysDef;
			}
		}
		return null;
	}
	
	public void doNotNeedSecurity_getSubListByType() {
		SysDef sysDef = getSysDef(type);

		List<SysDef> newList = new ArrayList<SysDef>();
		newList.add(sysDef);
		
		List<Tree> tree = new ArrayList<Tree>();
		for (SysDef def : newList) {
			Tree node = new Tree();

			BeanUtils.copyNotNullProperties(def, node, new String[] { "id",
					"pid" });
			node.setPid(def.getPid() + "");
			node.setId(def.getSysCode() + "");
			node.setText(def.getSysName());
			Map<String, String> attributes = new HashMap<String, String>();
			node.setAttributes(attributes);
			tree.add(node);
		}
		writeJson(tree);
	}	
	
	public void doNotNeedSecurity_getAllListByType() {
		doNotNeedSecurity_getAllListByType(this.getType());
	}	

	public void doNotNeedSecurity_getAllListByType(String type) {
		SysDef sysDef = getSysDef(type);
		doNotNeedSecurity_getAllListByType(sysDef.getSysCode());
	}
	
	public void doNotNeedSecurity_getAllListByType(int type) {
		
		List<SysDef> list = ((SysDefServiceI) service).getAllList();
		List<SysDef> newList = new ArrayList<SysDef>();
		for (SysDef sysDef : list) {
			if (sysDef.getPid() == type) {
				newList.add(sysDef);
			}
		}
		
		List<Tree> tree = new ArrayList<Tree>();
		for (SysDef sysDef : newList) {
			Tree node = new Tree();

			BeanUtils.copyNotNullProperties(sysDef, node, new String[] { "id",
					"pid" });
			node.setPid(sysDef.getPid() + "");
			node.setId(sysDef.getSysCode() + "");
			node.setText(sysDef.getSysName());
			Map<String, String> attributes = new HashMap<String, String>();
			node.setAttributes(attributes);
			tree.add(node);
		}
		writeJson(tree);
	}
	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

}
