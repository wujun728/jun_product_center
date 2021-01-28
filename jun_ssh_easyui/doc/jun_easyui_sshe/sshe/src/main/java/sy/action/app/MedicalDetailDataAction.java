package sy.action.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import sy.action.BaseAction;
import sy.model.app.CustUser;
import sy.model.app.CustomerInfo;
import sy.model.app.MedicalReportAttr;
import sy.model.app.MedicalReportData;
import sy.model.app.MedicalReportDef;
import sy.model.base.SessionInfo;
import sy.model.easyui.Grid;
import sy.model.easyui.Json;
import sy.model.easyui.TreeGrid;
import sy.service.app.MedicalReportAttrServiceI;
import sy.service.app.MedicalReportDataServiceI;
import sy.util.base.ConfigUtil;
import sy.util.base.DataTypeUtil;
import sy.util.base.HqlFilter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 报告数据
 */
@Namespace("/app")
@Action
public class MedicalDetailDataAction extends BaseAction<MedicalReportData> {
	private static final Logger logger = Logger
			.getLogger(MedicalDetailDataAction.class);
	private String opOrder;
	private TreeGrid<MedicalReportData> treeGrid;
	private String params;
	private int dataId;

	/**
	 * 注入业务逻辑，使当前action调用service.xxx的时候，直接是调用基础业务逻辑
	 * 
	 * 如果想调用自己特有的服务方法时，请使用((TServiceI) service).methodName()这种形式强转类型调用
	 * 
	 * @param service
	 */
	@Autowired
	public void setService(MedicalReportDataServiceI service) {
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
	 * 更新报告数据部门
	 */
	public void update() {
		Json json = new Json();
		if (!DataTypeUtil.isBlank(data.getDataId())) {

			((MedicalReportDataServiceI) service).updateMedicalReportData(data);
			json.setSuccess(true);
			json.setMsg("修改成功！");
		}
		writeJson(json);
	}

	/**
	 * 获得资源treeGrid
	 */
	public void treeGrid() {
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		// SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
		// ConfigUtil.getSessionInfoName());
		// hqlFilter
		// .addFilter("QUERY_user#id_S_EQ", sessionInfo.getUser().getId());
		if(opOrder != null){
			hqlFilter.addFilter("QUERY_t#opOrder_S_EQ", opOrder + "");
		}

		List<MedicalReportData> list = ((MedicalReportDataServiceI) service)
				.getTreeGrid(hqlFilter);
		
		logger.info(" 获得资源treeGrid , 数量:" + list.size());
		logger.info(list.toString());

		writeJson(list);
	}

	/**
	 * 角色grid
	 */
	public void grid() {
		Grid grid = new Grid();
		HqlFilter hqlFilter = new HqlFilter(getRequest());
//		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
//				ConfigUtil.getSessionInfoName());
//		
//		Set<CustomerInfo> set = sessionInfo.getUser().getSycustomerInfo();
//		for (CustomerInfo customerInfo : set) {
//			int customerId = customerInfo.getCustomerId();
//			if (customerId > 0) {
//				hqlFilter.addFilter("QUERY_t#customerId_I_EQ", customerId + "");
//			}
//		}
		//super.addCutomerFilter(hqlFilter);
		// hqlFilter.addFilter("QUERY_t#customerStatus_B_NE",
		// MedicalReportData.CUSTOMER_STATUS_DELETED + "");

		grid.setTotal(((MedicalReportDataServiceI) service)
				.countMedicalReportDataByFilter(hqlFilter));
		grid.setRows(((MedicalReportDataServiceI) service)
				.findMedicalReportDataByFilter(hqlFilter, page, rows));

		writeJson(grid);
	}

	/**
	 * 获得报告数据表格下啦列表
	 */
	public void doNotNeedSessionAndSecurity_MedicalDetailDataIdComboGrid() {
		Grid grid = new Grid();
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		
		hqlFilter.addFilter("QUERY_t#customerName_S_LK",
				"%%" + StringUtils.defaultString(q) + "%%");
		hqlFilter.addFilter("QUERY_t#customerEnName_S_LK",
				"%%" + StringUtils.defaultString(q) + "%%");
		// hqlFilter.addFilter("QUERY_t#customerStatus_B_NE",
		// MedicalReportData.CUSTOMER_STATUS_DELETED + "");

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
	 * 保存一个报告数据
	 */
	public void save() {
		Json json = new Json();
		if (data != null) {
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
					ConfigUtil.getSessionInfoName());
			((MedicalReportDataServiceI) service).saveMedicalReportData(data,
					sessionInfo.getUser().getId());
			json.setSuccess(true);
			json.setMsg("新增成功！");
		}
		writeJson(json);
	}

	public void saveList() {
		Json json = new Json();
		if (this.treeGrid != null) {
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
					ConfigUtil.getSessionInfoName());
			((MedicalReportDataServiceI) service).saveMedicalReportData(
					treeGrid.getRows(), sessionInfo.getUser().getId());
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
		if (!DataTypeUtil.isBlank(dataId)) {
			MedicalReportData t = service.getById(dataId);
			((MedicalReportDataServiceI) service).delete(t);
			json.setSuccess(true);
			json.setMsg("删除成功！");
		}
		writeJson(json);
	}

	/**
	 * @return the MedicalDetailDataId
	 */
	public String getOpOrder() {
		return opOrder;
	}

	/**
	 * @param MedicalDetailDataId
	 *            the MedicalDetailDataId to set
	 */
	public void setOpOrder(String opOrder) {
		this.opOrder = opOrder;
	}

	/**
	 * @return the treeGrid
	 */
	public TreeGrid<MedicalReportData> getTreeGrid() {
		return treeGrid;
	}

	/**
	 * @param treeGrid
	 *            the treeGrid to set
	 */
	public void setTreeGrid(TreeGrid<MedicalReportData> treeGrid) {
		this.treeGrid = treeGrid;
	}

	/**
	 * @return the params
	 */
	public String getParams() {
		return params;
	}

	/**
	 * @param params
	 *            the params to set
	 */
	public void setParams(String params) {
		if (params != null) {
			String p = params.replaceAll("&quot;", "\"");
			this.params = p;

			TreeGrid<JSONObject> t = (TreeGrid<JSONObject>) JSON.parseObject(
					this.params, (TreeGrid.class));
			int size = t.getRows().size();

			List<MedicalReportData> list = new ArrayList<MedicalReportData>();
			for (int i = 0; i < size; i++) {
				int userId = (int) (t.getRows().get(i)).get("custUser.userId");
				CustUser custUser = new CustUser();
				custUser.setUserId(userId);
				
				int tmeplateId = (int) (t.getRows().get(i).get("templateId"));
				MedicalReportDef medicalReportDef = new MedicalReportDef();
				medicalReportDef.setTemplateId(tmeplateId);
						
				Object pAttrObj = (Object)t.getRows().get(i).get("medicalReportAttr.attrId");
				int parentAttrId = -1;
				if(pAttrObj instanceof String){
					parentAttrId =  (int) ( (pAttrObj!=null && !((String)pAttrObj).isEmpty()  ) ? Integer.valueOf((String)pAttrObj) : -1);
				}else if(pAttrObj instanceof Integer){
					parentAttrId =  (int) ( (pAttrObj!=null && ((Integer)pAttrObj).intValue() > 0) ? ((Integer)pAttrObj).intValue() : -1);
				}
				
				MedicalReportData parent = null;
				if(parentAttrId>0){
					parent = new MedicalReportData();
					parent.setAttrId(parentAttrId);
						
				}
				
				MedicalReportData tt = JSON.toJavaObject(
						(JSONObject) (t.getRows().get(i)),
						MedicalReportData.class);
				tt.setCustUser(custUser);
				tt.setMedicalReportDef(medicalReportDef);
				tt.setMedicalReportData(parent);
				
				list.add(tt);
			}
			TreeGrid<MedicalReportData> treeGrid = new TreeGrid<MedicalReportData>();

			treeGrid.setTotal(t.getTotal());
			treeGrid.setRows(list);

			this.setTreeGrid(treeGrid);
		}

	}

	/**
	 * @return the dataId
	 */
	public int getDataId() {
		return dataId;
	}

	/**
	 * @param dataId
	 *            the dataId to set
	 */
	public void setDataId(int dataId) {
		this.dataId = dataId;
	}

}
