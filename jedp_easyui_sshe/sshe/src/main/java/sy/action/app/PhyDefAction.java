package sy.action.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import sy.action.BaseAction;
import sy.model.app.PhysicalTypeDef;
import sy.model.base.SessionInfo;
import sy.model.easyui.Json;
import sy.model.easyui.Tree;
import sy.service.app.PhysicalTypeDefServiceI;
import sy.util.base.BeanUtils;
import sy.util.base.ConfigUtil;
import sy.util.base.HqlFilter;

/**
 * 体格类型
 * 
 * action访问地址是/base/PhysicalTypeDef.sy
 * 
 */
@Namespace("/app")
@Action
public class PhyDefAction extends BaseAction<PhysicalTypeDef> {
	private static final Logger logger = Logger.getLogger(PhyDefAction.class);
	
	private String type;

	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获得一个对象 由于PhysicalTypeDef 结构的主键是 int 类型，必须重写这个方法
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
			PhysicalTypeDef t = ((PhysicalTypeDefServiceI) service)
					.getPhysicalTypeDefById(Integer.valueOf(id));
			if(t != null){
				t.setStatus(PhysicalTypeDef.STATUS_DELETED);
				((PhysicalTypeDefServiceI) service).updatePhysicalTypeDef(t);
				//service.delete(t);
				json.setSuccess(true);
				json.setMsg("删除成功！");
			}else{
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
	public void setService(PhysicalTypeDefServiceI service) {
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
			((PhysicalTypeDefServiceI) service).savePhysicalTypeDef(data,
					sessionInfo.getUserId());
			json.setSuccess(true);
		}
		writeJson(json);
	}

	/**
	 * 更新资源
	 */
	public void update() {
		Json json = new Json();
		if (data.getPhyId() > 0) {
			if (data.getPhysicalTypeDef() != null
					&& data.getPhyId() == data.getPhysicalTypeDef().getPhyId()) {
				json.setMsg("父资源不可以是自己！");
			} else {
				((PhysicalTypeDefServiceI) service).updatePhysicalTypeDef(data);
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
		
		 hqlFilter .addFilter("QUERY_t#status_B_NE", PhysicalTypeDef.STATUS_DELETED+"");
		
		List<PhysicalTypeDef> list = ((PhysicalTypeDefServiceI) service)
				.PhysicalTypeDefTreeGrid(hqlFilter);

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

		List<PhysicalTypeDef> resources = ((PhysicalTypeDefServiceI) service)
				.getMainMenu(hqlFilter);
		List<Tree> tree = new ArrayList<Tree>();
		for (PhysicalTypeDef PhysicalTypeDef : resources) {
			Tree node = new Tree();

			BeanUtils.copyNotNullProperties(PhysicalTypeDef, node,
					new String[] { "id", "pid" });
			node.setPid(PhysicalTypeDef.getPid() + "");
			node.setId(PhysicalTypeDef.getPhyId() + "");
			node.setText(PhysicalTypeDef.getPhyName());
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
	private PhysicalTypeDef getPhysicalTypeDef(String sname) {
		List<PhysicalTypeDef> list = ((PhysicalTypeDefServiceI) service).getAllList();
		if (list == null || (list != null && list.isEmpty())) {
			list = ((PhysicalTypeDefServiceI) service).getAllNoCacheList();
		}
		for (PhysicalTypeDef physicalTypeDef : list) {
			if (physicalTypeDef.getsName() != null
					&& physicalTypeDef.getsName().equalsIgnoreCase(sname)) {

				return physicalTypeDef;
			}
		}
		return null;
	}

	
	public void doNotNeedSecurity_getAllListByType() {
		doNotNeedSecurity_getAllListByType(this.getType());
	}
	
	public void doNotNeedSecurity_getAllListByType(String type) {
		PhysicalTypeDef physicalTypeDef = getPhysicalTypeDef(type);
		doNotNeedSecurity_getAllListByType(physicalTypeDef.getPhyId());
	}

	public void addAllChildren(Set<PhysicalTypeDef> list, List<PhysicalTypeDef> newList,int type){
		for (PhysicalTypeDef sysDef : list) {
			if (sysDef.getPid() == type) {
				newList.add(sysDef);
				addAllChildren(sysDef.getPhysicalTypeDefs(),newList,sysDef.getPhyId()  );
			}
		}
	}
	
	public void doNotNeedSecurity_getAllListByType(int type) {

		List<PhysicalTypeDef> list = ((PhysicalTypeDefServiceI) service).getAllList();
		Set<PhysicalTypeDef> set = new HashSet<PhysicalTypeDef>();
		set.addAll(list);
		
		List<PhysicalTypeDef> newList = new ArrayList<PhysicalTypeDef>();
		addAllChildren(set,newList,type);
		
		List<Tree> tree = new ArrayList<Tree>();
		for (PhysicalTypeDef sysDef : newList) {
			
			if(sysDef.getStatus() == PhysicalTypeDef.STATUS_DELETED){
				continue;
			}
			Tree node = new Tree();

			BeanUtils.copyNotNullProperties(sysDef, node, new String[] { "id",
					"pid" });
			node.setPid(sysDef.getPid() + "");
			node.setId(sysDef.getPhyId() + "");
			node.setText(sysDef.getPhyName());
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
