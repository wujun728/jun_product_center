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
import sy.model.app.EmpMedicalDetail;
import sy.model.app.MedicalReportAttr;
import sy.model.base.SessionInfo;
import sy.model.easyui.Grid;
import sy.model.easyui.Json;
import sy.model.easyui.Tree;
import sy.service.app.EmpMedicalDetailServiceI;
import sy.service.app.MedicalReportAttrServiceI;
import sy.util.base.BeanUtils;
import sy.util.base.ConfigUtil;
import sy.util.base.DataTypeUtil;
import sy.util.base.HqlFilter;

/**
 * 体检
 */
@Namespace("/app")
@Action
public class MedicalDetailAction extends BaseAction<EmpMedicalDetail> {
	private static final Logger logger = Logger
			.getLogger(MedicalDetailAction.class);
	private int EmpMedicalDetailId;

	/**
	 * 注入业务逻辑，使当前action调用service.xxx的时候，直接是调用基础业务逻辑
	 * 
	 * 如果想调用自己特有的服务方法时，请使用((TServiceI) service).methodName()这种形式强转类型调用
	 * 
	 * @param service
	 */
	@Autowired
	public void setService(EmpMedicalDetailServiceI service) {
		this.service = service;
	}

	/**
	 * 获得一个对象
	 */
	@Override
	public void getById() {
		if (!DataTypeUtil.isBlank(EmpMedicalDetailId)) {
			writeJson(service.getById(EmpMedicalDetailId));
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
			((EmpMedicalDetailServiceI) service).updateEmpMedicalDetail(data);
			json.setSuccess(true);
			json.setMsg("修改成功！");
		}
		writeJson(json);
	}
	
	/**
	 * 获得资源treeGrid
	 */
	public void doNotNeedSessionAndSecurity_treeGrid() {
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		
		
		List<EmpMedicalDetail> list = ((EmpMedicalDetailServiceI) service)
				.getTreeGrid(hqlFilter);
		
		List<Tree> tree = new ArrayList<Tree>();
		for (EmpMedicalDetail empMedicalDetail : list) {
			Tree node = new Tree();

			BeanUtils.copyNotNullProperties(empMedicalDetail, node,
					new String[] { "id", "pid" });
			node.setPid(empMedicalDetail.getPid() + "");
			node.setId(empMedicalDetail.getId() + "");
			node.setText(empMedicalDetail.getCustUser().getUserName());
			Map<String, String> attributes = new HashMap<String, String>();
			node.setAttributes(attributes);
			tree.add(node);
		}
		writeJson(tree);
	}
	
	/**
	 * 角色grid
	 */
	public void grid() {
		Grid grid = new Grid();
		HqlFilter hqlFilter = new HqlFilter(getRequest());
//		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
//				ConfigUtil.getSessionInfoName());
//		int customerId = sessionInfo.getUser().getCustomerId();
//		if (customerId > 0) {
//			hqlFilter.addFilter("QUERY_t#customerId_I_EQ", customerId + "");
//		}
		super.addCutomerFilter(hqlFilter,"QUERY_t#custUser#customerInfo#customerId_I_EQ");
		if(data.getMedicalType() >0){
			hqlFilter.addFilter("QUERY_t#medicalType_I_EQ", data.getMedicalType() + "");
		}
		hqlFilter.addFilter("QUERY_t#status_B_NE",
				EmpMedicalDetail.STATUS_DELETED + "");

		writeJson( ((EmpMedicalDetailServiceI) service)
				.findEmpMedicalDetailByFilter(hqlFilter, page, rows) );
		
//		grid.setTotal(((EmpMedicalDetailServiceI) service)
//				.countEmpMedicalDetailByFilter(hqlFilter));
//		grid.setRows(((EmpMedicalDetailServiceI) service)
//				.findEmpMedicalDetailByFilter(hqlFilter, page, rows));

		writeJson(grid);
	}

	/**
	 * 获得体检表格下啦列表
	 */
	public void doNotNeedSessionAndSecurity_EmpMedicalDetailIdComboGrid() {
		Grid grid = new Grid();
		HqlFilter hqlFilter = new HqlFilter(getRequest());

		hqlFilter.addFilter("QUERY_t#customerName_S_LK",
				"%%" + StringUtils.defaultString(q) + "%%");
		hqlFilter.addFilter("QUERY_t#customerEnName_S_LK",
				"%%" + StringUtils.defaultString(q) + "%%");
		hqlFilter.addFilter("QUERY_t#customerStatus_B_NE",
				EmpMedicalDetail.STATUS_DELETED + "");

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
			((EmpMedicalDetailServiceI) service).saveEmpMedicalDetail(data,
					sessionInfo.getUser().getId());
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
		if (!DataTypeUtil.isBlank(EmpMedicalDetailId)) {
			EmpMedicalDetail t = service.getById(EmpMedicalDetailId);
			t.setStatus(EmpMedicalDetail.STATUS_DELETED);
			((EmpMedicalDetailServiceI) service).updateEmpMedicalDetail(t);
			json.setSuccess(true);
			json.setMsg("删除成功！");
		}
		writeJson(json);
	}

	/**
	 * @return the EmpMedicalDetailId
	 */
	public int getEmpMedicalDetailId() {
		return EmpMedicalDetailId;
	}

	/**
	 * @param EmpMedicalDetailId
	 *            the EmpMedicalDetailId to set
	 */
	public void setEmpMedicalDetailId(int EmpMedicalDetailId) {
		this.EmpMedicalDetailId = EmpMedicalDetailId;
	}

}
