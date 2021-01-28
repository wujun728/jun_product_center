package sy.action.app;

import java.io.IOException;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import sy.action.BaseAction;
import sy.model.app.CustUser;
import sy.model.app.CustomerInfo;
import sy.model.app.ImpCustUserData;
import sy.model.base.SessionInfo;
import sy.model.base.Syuser;
import sy.model.easyui.Grid;
import sy.model.easyui.Json;
import sy.service.PoiReadEmpInfoServiceI;
import sy.service.app.CustUserServiceI;
import sy.service.app.ImpCustUserDataServiceI;
import sy.util.base.ConfigUtil;
import sy.util.base.DataTypeUtil;
import sy.util.base.HqlFilter;

/**
 * 员工
 */
@Namespace("/app")
@Action
public class CustUserAction extends BaseAction<CustUser> {
	private static final Logger logger = Logger.getLogger(CustUserAction.class);

	private int userId;
	// 客户
	private int customerInfoId;
	// 客户部门
	private int deptId;
	@Autowired
	private PoiReadEmpInfoServiceI poiReadEmpInfoServiceI;
	@Autowired
	private ImpCustUserDataServiceI impCustUserDataServiceI;

	private String custType;

	/**
	 * 注入业务逻辑，使当前action调用service.xxx的时候，直接是调用基础业务逻辑
	 * 
	 * 如果想调用自己特有的服务方法时，请使用((TServiceI) service).methodName()这种形式强转类型调用
	 * 
	 * @param service
	 */
	@Autowired
	public void setService(CustUserServiceI service) {
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
	 * 导入员工的数据
	 */
	public void importUserData() {
		try {
			Json json = new Json();
			if (!DataTypeUtil.isBlank(data.getCustomerInfo().getCustomerId())) {
				try {
					SessionInfo sessionInfo = (SessionInfo) getSession()
							.getAttribute(ConfigUtil.getSessionInfoName());

					String userId = sessionInfo.getUser().getId();
					ImpCustUserData impCustUserData = new ImpCustUserData();
					impCustUserData.setImpTime(new Date());
					impCustUserData.setNewFileName(data.getIcon());
					impCustUserData.setSrcFileName(data.getExt1());
					impCustUserData.setUrl(data.getIcon());
					
					Syuser syuser = new Syuser();
					syuser.setId(userId);
					
					impCustUserData.setSyuser(syuser);
					
					((PoiReadEmpInfoServiceI) poiReadEmpInfoServiceI)
							.handleForeachReadExcel(data.getCustomerInfo()
									.getCustomerId(), data.getIcon(),impCustUserData);

				} catch (InvalidFormatException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

				json.setSuccess(true);
				json.setMsg("导入数据成功！");
			}

			writeJson(json);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 更新员工部门
	 */
	public void update() {
		Json json = new Json();
		if (!DataTypeUtil.isBlank(data.getCustomerInfo().getCustomerId())) {

			((CustUserServiceI) service).updateCustUser(data);
			json.setSuccess(true);
			json.setMsg("修改成功！");
		}
		writeJson(json);
	}

	/**
	 * 获得表格下啦列表
	 */
	public void doNotNeedSessionAndSecurity_userIdCombogrid() {
		Grid grid = new Grid();
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		if(q != null){
			hqlFilter.addFilter("QUERY_t#userName_S_LK",
					"%%" + StringUtils.defaultString(q) + "%%");
			hqlFilter.addFilter("QUERY_t#lastName_S_LK",
					"%%" + StringUtils.defaultString(q) + "%%");
			hqlFilter.addFilter("QUERY_t#firstName_S_LK",
					"%%" + StringUtils.defaultString(q) + "%%");	
		}
		
		
		hqlFilter.addFilter("QUERY_t#status_B_NE",
				CustUser.CUSTUSER_STATUS_DELETED + "");

		// SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
		// ConfigUtil.getSessionInfoName());
		// // 过滤用户ID
		// if(sessionInfo.getUser().getCustomerId() >0){
		// hqlFilter
		// .addFilter("QUERY_t#customerInfo#customerId_I_EQ",
		// sessionInfo.getUser().getCustomerId()+"");
		// }

		super.addCutomerFilter(hqlFilter,
				"QUERY_t#customerInfo#customerId_I_EQ");

		grid.setTotal(service.countByFilter(hqlFilter));
		grid.setRows(service.findByFilter(hqlFilter, page, rows));
		writeJson(grid);
	}
	
	/**
	 * 获得表格下啦列表
	 */
	public void doNotNeedSessionAndSecurity_userIdCombogridById() {
		Grid grid = new Grid();
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		
		if(!(q == null || (q != null && q.isEmpty()))){
			hqlFilter.addFilter("QUERY_t#userName_S_LK",
					"%%" + StringUtils.defaultString(q) + "%%");
			hqlFilter.addFilter("QUERY_t#lastName_S_LK",
					"%%" + StringUtils.defaultString(q) + "%%");
			hqlFilter.addFilter("QUERY_t#firstName_S_LK",
					"%%" + StringUtils.defaultString(q) + "%%");
		}
		

		if (getCustomerInfoId() > 0) {
			hqlFilter.addFilter("QUERY_t#customerInfo#customerId_I_EQ", ""
					+ getCustomerInfoId() + "");
		}

		if (getDeptId() > 0) {
			hqlFilter.addFilter("QUERY_t#custDept#deptId_I_EQ", ""
					+ getDeptId() + "");
		}

		hqlFilter.addFilter("QUERY_t#status_B_NE",
				CustUser.CUSTUSER_STATUS_DELETED + "");

		// SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
		// ConfigUtil.getSessionInfoName());
		// // 过滤用户ID
		// if(sessionInfo.getUser().getCustomerId() >0){
		// hqlFilter
		// .addFilter("QUERY_t#customerInfo#customerId_I_EQ",
		// sessionInfo.getUser().getCustomerId()+"");
		// }

		super.addCutomerFilter(hqlFilter,
				"QUERY_t#customerInfo#customerId_I_EQ");

		grid.setTotal(service.countByFilter(hqlFilter));
		grid.setRows(service.findByFilter(hqlFilter, page, rows));
		writeJson(grid);
	}

	/**
	 * 修改用户机构
	 */
	public void grantCustDept() {
		Json json = new Json();
		((CustUserServiceI) service).grantOrganization(id, ids);
		json.setSuccess(true);
		writeJson(json);
	}
	
	@Override
	public void addCutomerFilter(HqlFilter hqlFilter) {
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		Syuser syuser = sessionInfo.getUser();
		Set<CustomerInfo> set = syuser.getSycustomerInfos();
		if (set == null || (set != null && set.isEmpty())) {
			if (!syuser.isAdmin()) {
				hqlFilter.addFilter("QUERY_t#customerInfo#customerId_I_EQ", "-1");
			}
			
			return;
		}
		if (!syuser.isAdmin()) {
			for (CustomerInfo customerInfo : set) {
				int customerId = customerInfo.getCustomerId();
				if (customerId > 0) {
					hqlFilter.addFilter("QUERY_t#customerInfo#customerId_I_EQ", customerId
							+ "");
				}
			}
		}
	}
	/**
	 * 角色grid
	 */
	public void grid() {
		Grid grid = new Grid();
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		
		addCutomerFilter(hqlFilter);
		
		hqlFilter.addFilter("QUERY_t#status_B_NE",
				CustUser.CUSTUSER_STATUS_DELETED + "");
		if(getCustType() != null){
			hqlFilter.addFilter("QUERY_t#customerInfo#customerType_B_EQ", getCustType() + "");
		}
		
		grid.setTotal(((CustUserServiceI) service)
				.countCustUserByFilter(hqlFilter));
		grid.setRows(((CustUserServiceI) service).findCustUserByFilter(
				hqlFilter, page, rows));

		writeJson(grid);
	}

	/**
	 * 保存一个员工
	 */
	public void save() {
		Json json = new Json();
		if (data != null) {
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
					ConfigUtil.getSessionInfoName());
			((CustUserServiceI) service).saveCustUser(data, sessionInfo
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
		if (!DataTypeUtil.isBlank(userId)) {
			CustUser t = service.getById(userId);
			t.setStatus(CustUser.CUSTUSER_STATUS_DELETED);
			((CustUserServiceI) service).updateCustUser(t);
			json.setSuccess(true);
			json.setMsg("删除成功！");
		}
		writeJson(json);
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the deptId
	 */
	public int getDeptId() {
		return deptId;
	}

	/**
	 * @param deptId
	 *            the deptId to set
	 */
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	/**
	 * @return the customerInfoId
	 */
	public int getCustomerInfoId() {
		return customerInfoId;
	}

	/**
	 * @param customerInfoId
	 *            the customerInfoId to set
	 */
	public void setCustomerInfoId(int customerInfoId) {
		this.customerInfoId = customerInfoId;
	}
	/**
	 * @return the custType
	 */
	public String getCustType() {
		return custType;
	}

	/**
	 * @param custType
	 *            the custType to set
	 */
	public void setCustType(String custType) {
		this.custType = custType;
	}
}
