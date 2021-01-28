package sy.action.app;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import sy.action.BaseAction;
import sy.model.app.EmpIllHis;
import sy.model.base.SessionInfo;
import sy.model.easyui.Grid;
import sy.model.easyui.Json;
import sy.service.app.EmpIllHisServiceI;
import sy.util.base.ConfigUtil;
import sy.util.base.DataTypeUtil;
import sy.util.base.HqlFilter;

/**
 * 体检
 */
@Namespace("/app")
@Action
public class EmpIllHisAction extends BaseAction<EmpIllHis> {
	private static final Logger logger = Logger
			.getLogger(EmpIllHisAction.class);
	private int EmpIllHisId;

	/**
	 * 注入业务逻辑，使当前action调用service.xxx的时候，直接是调用基础业务逻辑
	 * 
	 * 如果想调用自己特有的服务方法时，请使用((TServiceI) service).methodName()这种形式强转类型调用
	 * 
	 * @param service
	 */
	@Autowired
	public void setService(EmpIllHisServiceI service) {
		this.service = service;
	}

	/**
	 * 获得一个对象
	 */
	@Override
	public void getById() {
		if (!DataTypeUtil.isBlank(EmpIllHisId)) {
			writeJson(service.getById(EmpIllHisId));
		} else {
			Json j = new Json();
			j.setMsg("主键不可为空！");
			writeJson(j);
		}
	}

	/**
	 * 更新体检部门
	 */
	public void update() {
		Json json = new Json();
		if (!DataTypeUtil.isBlank(data.getId())) {
			((EmpIllHisServiceI) service).updateEmpIllHis(data);
			json.setSuccess(true);
			json.setMsg("修改成功！");
		}
		writeJson(json);
	}

	// /**
	// * 获得主菜单tree，也用于获得上级体检部门菜单combotree
	// */
	// public void doNotNeedSecurity_getMainMenu() {
	// HqlFilter hqlFilter = new HqlFilter(getRequest());
	// SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
	// ConfigUtil.getSessionInfoName());
	// hqlFilter
	// .addFilter("QUERY_user#id_S_EQ", sessionInfo.getUser().getId());
	// hqlFilter.addFilter("QUERY_t#EmpIllHistype#id_S_EQ", "0");//
	// 0就是只查菜单
	// List<EmpIllHis> resources = ((EmpIllHisServiceI) service)
	// .getMainMenu(hqlFilter);
	// List<Tree> tree = new ArrayList<Tree>();
	// for (EmpIllHis resource : resources) {
	// Tree node = new Tree();
	// BeanUtils.copyNotNullProperties(resource, node);
	// node.setText(resource.getName());
	// Map<String, String> attributes = new HashMap<String, String>();
	// attributes.put("url", resource.getUrl());
	// attributes.put("target", resource.getTarget());
	// attributes.put("isOpen", resource.getIsOpen() + "");
	// if (resource.getIsOpen() ==
	// EmpIllHis.IS_OPEN_FLAG.IS_OPEN_FLAG_CLOSE
	// .ordinal()) {
	// node.setIconCls("ext-icon-exclamation");
	// }
	// node.setAttributes(attributes);
	// tree.add(node);
	// }
	// writeJson(tree);
	// }

	// /**
	// * 获得体检部门treeGrid - 获得菜单
	// */
	// public void treeGridMenu() {
	// HqlFilter hqlFilter = new HqlFilter(getRequest());
	// SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
	// ConfigUtil.getSessionInfoName());
	// // 过滤用户ID
	// hqlFilter
	// .addFilter("QUERY_user#id_S_EQ", sessionInfo.getUser().getId());
	// // 过滤菜单还是功能
	// hqlFilter.addFilter("QUERY_t#EmpIllHistype#id_S_EQ", "0");//
	// 0就是只查菜单
	//
	// List<EmpIllHis> list = ((EmpIllHisServiceI) service)
	// .getMainMenu(hqlFilter);
	//
	// logger.info(" 获得体检部门treeGrid , 数量:" + list.size());
	// logger.info(list.toString());
	// writeJson(list);
	// }

	/**
	 * 角色grid
	 */
	public void grid() {
		Grid grid = new Grid();
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		// SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
		// ConfigUtil.getSessionInfoName());
		// int customerId = sessionInfo.getUser().getCustomerId();
		// if (customerId > 0) {
		// hqlFilter.addFilter("QUERY_t#customerId_I_EQ", customerId + "");
		// }
		super.addCutomerFilter(hqlFilter,
				"QUERY_t#custUser#customerInfo#customerId_I_EQ");

		hqlFilter.addFilter("QUERY_t#status_B_NE", EmpIllHis.STATUS_DELETED
				+ "");

		grid.setTotal(((EmpIllHisServiceI) service)
				.countEmpIllHisByFilter(hqlFilter));
		grid.setRows(((EmpIllHisServiceI) service).findEmpIllHisByFilter(
				hqlFilter, page, rows));

		writeJson(grid);
	}

	/**
	 * 获得体检表格下啦列表
	 */
	public void doNotNeedSessionAndSecurity_EmpIllHisIdComboGrid() {
		Grid grid = new Grid();
		HqlFilter hqlFilter = new HqlFilter(getRequest());

		hqlFilter.addFilter("QUERY_t#customerName_S_LK",
				"%%" + StringUtils.defaultString(q) + "%%");
		hqlFilter.addFilter("QUERY_t#customerEnName_S_LK",
				"%%" + StringUtils.defaultString(q) + "%%");
		hqlFilter.addFilter("QUERY_t#customerStatus_B_NE",
				EmpIllHis.STATUS_DELETED + "");

		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		// // 过滤用户ID
		// if(sessionInfo.getUser().getCustomerId() >0){
		// hqlFilter
		// .addFilter("QUERY_t#id_I_EQ",
		// sessionInfo.getUser().getCustomerId()+"");
		// }

		grid.setTotal(service.countByFilter(hqlFilter));
		grid.setRows(service.findByFilter(hqlFilter, page, rows));
		writeJson(grid);
	}

	/**
	 * 保存一个体检
	 */
	public void save() {
		Json json = new Json();
		if (data != null) {
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
					ConfigUtil.getSessionInfoName());
			((EmpIllHisServiceI) service).saveEmpIllHis(data, sessionInfo
					.getUser().getId());
			json.setSuccess(true);
			json.setMsg("新增成功！");
		}
		writeJson(json);
	}

	/**
	 * 删除一个对象
	 */
	public void delete() {
		Json json = new Json();
		if (!DataTypeUtil.isBlank(EmpIllHisId)) {
			EmpIllHis t = service.getById(EmpIllHisId);
			t.setStatus(EmpIllHis.STATUS_DELETED);
			((EmpIllHisServiceI) service).updateEmpIllHis(t);
			json.setSuccess(true);
			json.setMsg("删除成功！");
		}
		writeJson(json);
	}

	/**
	 * @return the EmpIllHisId
	 */
	public int getEmpIllHisId() {
		return EmpIllHisId;
	}

	/**
	 * @param EmpIllHisId
	 *            the EmpIllHisId to set
	 */
	public void setEmpIllHisId(int EmpIllHisId) {
		this.EmpIllHisId = EmpIllHisId;
	}

}
