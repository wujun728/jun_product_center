package sy.action.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import sy.action.BaseAction;
import sy.model.app.ImpCustUserData;
import sy.model.base.SessionInfo;
import sy.model.easyui.Grid;
import sy.model.easyui.Json;
import sy.model.easyui.Tree;
import sy.service.app.ImpCustUserDataServiceI;
import sy.util.base.ConfigUtil;
import sy.util.base.DataTypeUtil;
import sy.util.base.HqlFilter;

/**
 * 员工数据
 */
@SuppressWarnings("serial")
@Namespace("/app")
@Action
public class ImpCustUserDataAction extends BaseAction<ImpCustUserData> {

	private static final Logger logger = Logger
			.getLogger(ImpCustUserDataAction.class);
	private int impCustUserDataId;

	public ImpCustUserData getData() {
		return data;
	}

	public void setData(ImpCustUserData data) {
		this.data = data;
	}

	/**
	 * 注入业务逻辑，使当前action调用service.xxx的时候，直接是调用基础业务逻辑
	 * 
	 * 如果想调用自己特有的服务方法时，请使用((TServiceI) service).methodName()这种形式强转类型调用
	 * 
	 * @param service
	 */
	@Autowired
	public void setService(ImpCustUserDataServiceI service) {
		this.service = service;
	}

	/**
	 * 获得一个对象
	 */
	@Override
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
	 * 获得机构下拉树
	 */
	public void doNotNeedSecurity_comboTree() {
		HqlFilter hqlFilter = new HqlFilter();

		List<ImpCustUserData> ImpCustUserDataList = service
				.findByFilter(hqlFilter);
		List<Tree> tree = new ArrayList<Tree>();
		for (ImpCustUserData custDept : ImpCustUserDataList) {
			Tree node = new Tree();
			node.setId(custDept.getId() + "");
			node.setText(custDept.getSrcFileName());

			Map<String, String> attributes = new HashMap<String, String>();
			// attributes.put("url", "");
			attributes.put("target", "");
			node.setAttributes(attributes);
			tree.add(node);
		}

		writeJson(tree);
	}

	/**
	 * 获得员工数据部门treeGrid - 获得菜单
	 */
	public void grid() {
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		// 过滤用户ID
		hqlFilter.addFilter("QUERY_syuser#id_S_EQ", sessionInfo.getUser()
				.getId() + "");
		
		// 过滤菜单还是功能
		hqlFilter.addFilter("QUERY_t#dataType_B_EQ", "0");// 0就是只查菜单

		List<ImpCustUserData> list = ((ImpCustUserDataServiceI) service)
				.getMainMenu(hqlFilter);

		logger.info(" 获得员工数据部门treeGrid , 数量:" + list.size());
		logger.info(list.toString());
		writeJson(list);
	}

	/**
	 * 获得员工数据部门树
	 */
	public void doNotNeedSecurity_getResourcesTree() {
		treeGrid();
	}

	public void doNotNeedSessionAndSecurity_deptIdComboGrid() {

		Grid grid = new Grid();
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		if (getImpCustUserDataId() > 0) {
			hqlFilter.addFilter("QUERY_t#customerInfo#customerId_I_EQ",
					getImpCustUserDataId() + "");
		}

		hqlFilter.addFilter("QUERY_t#deptName_S_LK",
				"%%" + StringUtils.defaultString(q) + "%%");

		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());

		// 过滤用户ID
		// if(sessionInfo.getUser().getCustomerId() >0){
		// hqlFilter.addFilter("QUERY_t#id_I_EQ",
		// sessionInfo.getUser().getCustomerId()+"");
		// }

		grid.setTotal(service.countByFilter(hqlFilter));
		grid.setRows(service.findByFilter(hqlFilter, page, rows));
		writeJson(grid);
	}

	/**
	 * 更新员工数据部门
	 */
	public void update() {
		Json json = new Json();
		if (!DataTypeUtil.isBlank(data.getId())) {

			((ImpCustUserDataServiceI) service).updateImpCustUserData(data);
			json.setSuccess(true);
			json.setMsg("修改成功！");
		}

		writeJson(json);
	}

	/**
	 * 保存一个员工数据部门
	 */
	public void save() {
		Json json = new Json();
		if (data != null) {
			// if(getImpCustUserData_deptId()>0){
			// ImpCustUserData c = ((ImpCustUserDataServiceI)
			// service).getById(getImpCustUserData_deptId());
			// {
			// ((ImpCustUserData)data).setImpCustUserData(c);
			// }
			// }

			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
					ConfigUtil.getSessionInfoName());

			((ImpCustUserDataServiceI) service).saveImpCustUserData(data,
					sessionInfo.getUser().getId());

			json.setSuccess(true);
		}
		writeJson(json);
	}

	/**
	 * 删除一个对象
	 */
	public void delete() {
		Json json = new Json();
		if (!DataTypeUtil.isBlank(impCustUserDataId)) {
			ImpCustUserData t = service.getById(id);
			((ImpCustUserDataServiceI) service).delete(t);
			json.setSuccess(true);
			json.setMsg("删除成功！");
		}
		writeJson(json);
	}

	/**
	 * @return the impCustUserDataId
	 */
	public int getImpCustUserDataId() {
		return impCustUserDataId;
	}

	/**
	 * @param impCustUserDataId
	 *            the impCustUserDataId to set
	 */
	public void setImpCustUserDataId(int impCustUserDataId) {
		this.impCustUserDataId = impCustUserDataId;
	}
}
