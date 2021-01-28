package sy.action.app;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import sy.action.BaseAction;
import sy.model.app.EmpDiagnoseRecord;
import sy.model.app.EmpEpidemicRecord;
import sy.model.base.SessionInfo;
import sy.model.easyui.Grid;
import sy.model.easyui.Json;
import sy.service.app.EmpDiagnoseRecordServiceI;
import sy.service.app.EmpEpidemicRecordServiceI;
import sy.util.base.ConfigUtil;
import sy.util.base.DataTypeUtil;
import sy.util.base.HqlFilter;

/**
 * 体检
 */
@Namespace("/app")
@Action
public class EmpDiagnoseRecordAction extends BaseAction<EmpDiagnoseRecord> {
	private static final Logger logger = Logger
			.getLogger(EmpDiagnoseRecordAction.class);
	private int EmpDiagnoseRecordId;
	private int userId;
	
	private String data_suspectedCheckbox;
	private int data_illType;
	
	@Autowired
	private EmpEpidemicRecordServiceI empEpidemicRecordServiceI;
	
	@Autowired
	public void setService(EmpDiagnoseRecordServiceI service) {
		this.service = service;
	}
	
	/**
	 * 获得一个对象
	 */
	@Override
	public void getById() {
		if (!DataTypeUtil.isBlank(EmpDiagnoseRecordId)) {
			writeJson(service.getById(EmpDiagnoseRecordId));
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
		if (!DataTypeUtil.isBlank(data.getMedicalId())) {
			if(getData_suspectedCheckbox() != null && getData_suspectedCheckbox().equalsIgnoreCase("yes")){
				if(this.getData_illType() <=0){
					json.setSuccess(true);
					json.setMsg("请选择疾病类型 ！");
					writeJson(json);
					return ;
				}
			}
			((EmpDiagnoseRecordServiceI) service).updateEmpDiagnoseRecord(data);
				
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
					ConfigUtil.getSessionInfoName());
			
			if(getData_suspectedCheckbox() != null && getData_suspectedCheckbox().equalsIgnoreCase("yes")){
				EmpEpidemicRecord empEpidemicRecord = new EmpEpidemicRecord();
				 
				empEpidemicRecord.setCustUser(data.getCustUser());
				empEpidemicRecord.setIllType(this.getData_illType());
				empEpidemicRecord.setSuspected(EmpEpidemicRecord.SUSPECTED_YS);
				
				empEpidemicRecord.setStatus(EmpEpidemicRecord.STATUS_NEW);
				empEpidemicRecord.setConfirmDate(new Date());
				
				((EmpEpidemicRecordServiceI) empEpidemicRecordServiceI).saveEmpEpidemicRecord(empEpidemicRecord,
						sessionInfo.getUser().getId());
				
			}
			
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
	// hqlFilter.addFilter("QUERY_t#EmpDiagnoseRecordtype#id_S_EQ", "0");//
	// 0就是只查菜单
	// List<EmpDiagnoseRecord> resources = ((EmpDiagnoseRecordServiceI) service)
	// .getMainMenu(hqlFilter);
	// List<Tree> tree = new ArrayList<Tree>();
	// for (EmpDiagnoseRecord resource : resources) {
	// Tree node = new Tree();
	// BeanUtils.copyNotNullProperties(resource, node);
	// node.setText(resource.getName());
	// Map<String, String> attributes = new HashMap<String, String>();
	// attributes.put("url", resource.getUrl());
	// attributes.put("target", resource.getTarget());
	// attributes.put("isOpen", resource.getIsOpen() + "");
	// if (resource.getIsOpen() ==
	// EmpDiagnoseRecord.IS_OPEN_FLAG.IS_OPEN_FLAG_CLOSE
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
	// hqlFilter.addFilter("QUERY_t#EmpDiagnoseRecordtype#id_S_EQ", "0");//
	// 0就是只查菜单
	//
	// List<EmpDiagnoseRecord> list = ((EmpDiagnoseRecordServiceI) service)
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
//		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
//				ConfigUtil.getSessionInfoName());
//		int customerId = sessionInfo.getUser().getCustomerId();
//		if (customerId > 0) {
//			hqlFilter.addFilter("QUERY_t#customerId_I_EQ", customerId + "");
//		}
		super.addCutomerFilter(hqlFilter,"QUERY_t#custUser#customerInfo#customerId_I_EQ");
		
		hqlFilter.addFilter("QUERY_t#status_B_NE",
				EmpDiagnoseRecord.STATUS_DELETED + "");
		if(getUserId()>0){
			hqlFilter.addFilter("QUERY_t#custUser#userId_I_EQ", getUserId()+ "");
		}

		grid.setTotal(((EmpDiagnoseRecordServiceI) service)
				.countEmpDiagnoseRecordByFilter(hqlFilter));
		grid.setRows(((EmpDiagnoseRecordServiceI) service)
				.findEmpDiagnoseRecordByFilter(hqlFilter, page, rows));

		writeJson(grid);
	}
	
	/**
	 * 获得体检表格下啦列表
	 */
	public void doNotNeedSessionAndSecurity_EmpDiagnoseRecordIdComboGrid() {
		Grid grid = new Grid();
		HqlFilter hqlFilter = new HqlFilter(getRequest());

		hqlFilter.addFilter("QUERY_t#customerName_S_LK",
				"%%" + StringUtils.defaultString(q) + "%%");
		hqlFilter.addFilter("QUERY_t#customerEnName_S_LK",
				"%%" + StringUtils.defaultString(q) + "%%");
		hqlFilter.addFilter("QUERY_t#customerStatus_B_NE",
				EmpDiagnoseRecord.STATUS_DELETED + "");

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
			if(getData_suspectedCheckbox() != null && getData_suspectedCheckbox().equalsIgnoreCase("yes")){
				if(this.getData_illType() <=0){
					json.setSuccess(true);
					json.setMsg("请选择疾病类型 ！");
					writeJson(json);
					return ;
				}
			}
			
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
					ConfigUtil.getSessionInfoName());
			((EmpDiagnoseRecordServiceI) service).saveEmpDiagnoseRecord(data,
					sessionInfo.getUser().getId());
			
			
			if(getData_suspectedCheckbox() != null && getData_suspectedCheckbox().equalsIgnoreCase("yes")){
				EmpEpidemicRecord empEpidemicRecord = new EmpEpidemicRecord();
				 
				empEpidemicRecord.setCustUser(data.getCustUser());
				empEpidemicRecord.setIllType(this.getData_illType());
				empEpidemicRecord.setSuspected(EmpEpidemicRecord.SUSPECTED_YS);
				
				empEpidemicRecord.setStatus(EmpEpidemicRecord.STATUS_NEW);
				empEpidemicRecord.setConfirmDate(new Date());
				
				((EmpEpidemicRecordServiceI) empEpidemicRecordServiceI).saveEmpEpidemicRecord(empEpidemicRecord,
						sessionInfo.getUser().getId());
				
			}
			
			json.setObj(data);
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
		if (!DataTypeUtil.isBlank(EmpDiagnoseRecordId)) {
			EmpDiagnoseRecord t = service.getById(EmpDiagnoseRecordId);
			t.setStatus(EmpDiagnoseRecord.STATUS_DELETED);
			((EmpDiagnoseRecordServiceI) service).updateEmpDiagnoseRecord(t);
			json.setSuccess(true);
			json.setMsg("删除成功！");
		}
		writeJson(json);
	}
	
	
	/**
	 * @return the EmpDiagnoseRecordId
	 */
	public int getEmpDiagnoseRecordId() {
		return EmpDiagnoseRecordId;
	}

	/**
	 * @param EmpDiagnoseRecordId
	 *            the EmpDiagnoseRecordId to set
	 */
	public void setEmpDiagnoseRecordId(int EmpDiagnoseRecordId) {
		this.EmpDiagnoseRecordId = EmpDiagnoseRecordId;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the data_suspectedCheckbox
	 */
	public String getData_suspectedCheckbox() {
		return data_suspectedCheckbox;
	}

	/**
	 * @param data_suspectedCheckbox the data_suspectedCheckbox to set
	 */
	public void setData_suspectedCheckbox(String data_suspectedCheckbox) {
		this.data_suspectedCheckbox = data_suspectedCheckbox;
	}

	/**
	 * @return the data_illType
	 */
	public int getData_illType() {
		return data_illType;
	}

	/**
	 * @param data_illType the data_illType to set
	 */
	public void setData_illType(int data_illType) {
		this.data_illType = data_illType;
	}
	
	

}
