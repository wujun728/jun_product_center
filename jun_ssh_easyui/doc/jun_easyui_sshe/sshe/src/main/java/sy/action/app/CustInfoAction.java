package sy.action.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import sy.action.BaseAction;
import sy.model.app.CustDept;
import sy.model.app.CustomerInfo;
import sy.model.base.SessionInfo;
import sy.model.base.Syrole;
import sy.model.base.Syuser;
import sy.model.easyui.Grid;
import sy.model.easyui.Json;
import sy.model.easyui.Tree;
import sy.service.app.CustomerInfoServiceI;
import sy.service.base.SyroleServiceI;
import sy.service.base.SyuserServiceI;
import sy.util.base.ConfigUtil;
import sy.util.base.DataTypeUtil;
import sy.util.base.HqlFilter;

/**
 * 客户
 */
@Namespace("/app")
@Action
public class CustInfoAction extends BaseAction<CustomerInfo> {
	private static final Logger logger = Logger.getLogger(CustInfoAction.class);

	@Autowired
	private SyuserServiceI userService;

	private int customerInfoId;
	/**
	 * 客户类型 对应 CustomerInfo 中的 CUSTOMER_TYPE
	 */
	private String custType;

	/**
	 * 注入业务逻辑，使当前action调用service.xxx的时候，直接是调用基础业务逻辑
	 * 
	 * 如果想调用自己特有的服务方法时，请使用((TServiceI) service).methodName()这种形式强转类型调用
	 * 
	 * @param service
	 */
	@Autowired
	public void setService(CustomerInfoServiceI service) {
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
	 * 更新客户部门
	 */
	public void update() {
		Json json = new Json();
		if (!DataTypeUtil.isBlank(data.getCustomerId())) {

			((CustomerInfoServiceI) service).updateCustomerInfo(data);
			json.setSuccess(true);
			json.setMsg("修改成功！");
		}
		writeJson(json);
	}

	/**
	 * 角色grid
	 */
	public void grid() {
		Grid grid = new Grid();
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		Syuser syuser = sessionInfo.getUser();

		// int customerId = sessionInfo.getUser().getCustomerId();
		// if (customerId > 0) {
		// hqlFilter.addFilter("QUERY_t#customerId_I_EQ", customerId + "");
		// }
		super.addCutomerFilter(hqlFilter);
		hqlFilter.addFilter("QUERY_t#customerType_B_EQ", getCustType() + "");

		hqlFilter.addFilter("QUERY_t#customerStatus_B_NE",
				CustomerInfo.CUSTOMER_STATUS_DELETED + "");

		if (syuser.isAdmin()) {
			grid.setTotal(((CustomerInfoServiceI) service)
					.countCustomerInfoWithoutUserCondByFilter(hqlFilter));
			grid.setRows(((CustomerInfoServiceI) service)
					.findCustomerInfoWithoutUserCondByFilter(hqlFilter, page,
							rows));
		} else {
			grid.setTotal(((CustomerInfoServiceI) service)
					.countCustomerInfoByFilter(hqlFilter));
			grid.setRows(((CustomerInfoServiceI) service)
					.findCustomerInfoByFilter(hqlFilter, page, rows));
		}

		writeJson(grid);
	}

	/**
	 * 获得客户表格下啦列表
	 */
	public void doNotNeedSessionAndSecurity_customerInfoIdComboGrid() {
		Grid grid = new Grid();
		HqlFilter hqlFilter = new HqlFilter(getRequest());

		if (q != null) {
			hqlFilter.addFilter("QUERY_t#customerName_S_LK",
					"%%" + StringUtils.defaultString(q) + "%%");
			hqlFilter.addFilter("QUERY_t#customerEnName_S_LK", "%%"
					+ StringUtils.defaultString(q) + "%%");
		}

		hqlFilter.addFilter("QUERY_t#customerStatus_B_NE",
				CustomerInfo.CUSTOMER_STATUS_DELETED + "");
		super.addCutomerFilter(hqlFilter);
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		Syuser syuser = sessionInfo.getUser();
		// // 过滤用户ID
		// if(sessionInfo.getUser().getCustomerId() >0){
		// hqlFilter
		// .addFilter("QUERY_t#id_I_EQ",
		// sessionInfo.getUser().getCustomerId()+"");
		// }

		if (syuser.isAdmin()) {
			grid.setTotal(((CustomerInfoServiceI) service)
					.countCustomerInfoWithoutUserCondByFilter(hqlFilter));
			grid.setRows(((CustomerInfoServiceI) service)
					.findCustomerInfoWithoutUserCondByFilter(hqlFilter, page,
							rows));
		} else {
			grid.setTotal(service.countByFilter(hqlFilter));
			grid.setRows(service.findByFilter(hqlFilter, page, rows));
		}
		
		
		writeJson(grid);
	}

	// /**
	// * 获得客户部门treeGrid
	// */
	// public void treeGrid() {
	// HqlFilter hqlFilter = new HqlFilter(getRequest());
	// SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
	// ConfigUtil.getSessionInfoName());
	// int customerId = sessionInfo.getUser().getCustomerId();
	// if (customerId > 0) {
	// hqlFilter.addFilter("QUERY_t#customerInfo#customerId_I_EQ",
	// customerId + "");
	// }
	//
	// List<CustomerInfo> list = ((CustomerInfoServiceI) service)
	// .deptTreeGrid(hqlFilter);
	//
	// logger.info(" 获得客户部门treeGrid , 数量:" + list.size());
	// logger.info(list.toString());
	// writeJson(list);
	// }

	// /**
	// * 获得角色的客户部门列表
	// */
	// public void doNotNeedSecurity_getRoleResources() {
	// HqlFilter hqlFilter = new HqlFilter(getRequest());
	// hqlFilter.addFilter("QUERY_role#id_S_EQ", id);
	// writeJson(((CustomerInfoServiceI)
	// service).findResourceByFilter(hqlFilter));
	// }
	//
	// /**
	// * 获得机构的客户部门列表
	// */
	// public void doNotNeedSecurity_getOrganizationResources() {
	// HqlFilter hqlFilter = new HqlFilter(getRequest());
	// hqlFilter.addFilter("QUERY_organization#id_S_EQ", id);
	// writeJson(((CustomerInfoServiceI)
	// service).findResourceByFilter(hqlFilter));
	// }

	// /**
	// * 获得客户部门树
	// */
	// public void doNotNeedSecurity_getResourcesTree() {
	// treeGrid();
	// }

	/**
	 * 获得当前用户能看到的所有客户树
	 */
	public void doNotNeedSecurity_getCustomersTree() {
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		Syuser user = userService.getById(sessionInfo.getUser().getId());
		Set<CustomerInfo> customerInfos = user.getSycustomerInfos();
		List<CustomerInfo> l = new ArrayList<CustomerInfo>(customerInfos);

		HqlFilter hqlFilter = new HqlFilter(getRequest());
		Syuser syuser = sessionInfo.getUser();

		if (syuser.isAdmin()) {
			List<CustomerInfo> aList = ((CustomerInfoServiceI) service)
					.findCustomerInfoWithoutUserCondByFilter(hqlFilter, page,
							rows);
			if (aList != null) {
				for (CustomerInfo cust : aList) {
					if (!l.contains(cust)) {
						l.add(cust);
					}
				}

			}
		}

		Collections.sort(l, new Comparator<CustomerInfo>() {// 排序
					@Override
					public int compare(CustomerInfo o1, CustomerInfo o2) {
						return (o1.getCustomerId() + "").compareTo(o2
								.getCustomerId() + "");
					}
				});
		
		List<Tree> tree = new ArrayList<Tree>();
		for (CustomerInfo customerInfo : l) {
			Tree node = new Tree();
			node.setId(customerInfo.getCustomerId() + "");
			node.setPid( "0");
			node.setText(customerInfo.getCustomerName());

			Map<String, String> attributes = new HashMap<String, String>();
			// attributes.put("url", "");
			attributes.put("target", "");
			node.setAttributes(attributes);
			tree.add(node);
		}
		
		writeJson(tree);
		
//		writeJson(l);
	}

	/**
	 * 获得当前用户的客户
	 */
	public void doNotNeedSecurity_getCustomerByUserId() {
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		hqlFilter.addFilter("QUERY_user#id_S_EQ", id + "");
		List<CustomerInfo> customerInfos = ((CustomerInfoServiceI) service)
				.findCustomerInfoByFilter(hqlFilter);
		writeJson(customerInfos);
	}

	/**
	 * 保存一个客户
	 */
	public void save() {
		Json json = new Json();
		if (data != null) {
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
					ConfigUtil.getSessionInfoName());
			((CustomerInfoServiceI) service).saveCustomerInfo(data, sessionInfo
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
		if (!DataTypeUtil.isBlank(customerInfoId)) {
			CustomerInfo t = service.getById(customerInfoId);
			t.setCustomerStatus(CustomerInfo.CUSTOMER_STATUS_DELETED);
			((CustomerInfoServiceI) service).updateCustomerInfo(t);
			json.setSuccess(true);
			json.setMsg("删除成功！");
		}
		writeJson(json);
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
