package sy.action.app;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import sy.action.BaseAction;
import sy.model.app.DrugPdRecordLog;
import sy.model.app.DrugSpecInfo;
import sy.model.app.DrugStoreCheck;
import sy.model.base.SessionInfo;
import sy.model.easyui.Grid;
import sy.model.easyui.Json;
import sy.service.app.DrugPdRecordLogServiceI;
import sy.service.app.DrugSpecInfoServiceI;
import sy.service.app.DrugStoreCheckRecordServiceI;
import sy.service.app.DrugStoreServiceI;
import sy.util.base.ConfigUtil;
import sy.util.base.DataTypeUtil;
import sy.util.base.HqlFilter;

/**
 * 盘点
 */
@Namespace("/app")
@Action
public class DrugStoreCheckAction extends BaseAction<DrugStoreCheck> {
	private static final Logger logger = Logger
			.getLogger(DrugStoreCheckAction.class);
	private int DrugRecordId;
	/**
	 * 查询自己的 SELF, 入库IN, 出库OUT
	 */
	private String opMod;
	private static final String OP_TYPE_SELF = "SELF";
	private static final String OP_TYPE_IN = "IN";
	private static final String OP_TYPE_OUT = "OUT";
	/**
	 * USE 消耗 PANDIAN 盘点
	 */
	public static final String OP_TYPE_USE = "USE";
	public static final String OP_TYPE_PANDIAN = "PANDIAN";
	public static final String OP_TYPE_JIUZHG = "JIUZHG";

	@Autowired
	public void setService(DrugStoreCheckRecordServiceI service) {
		this.service = service;
	}

	@Autowired
	private DrugSpecInfoServiceI drugSpecInfoServiceI;
	@Autowired
	private DrugStoreCheckRecordServiceI drugStoreCheckRecordServiceI;
	
	@Autowired
	private DrugStoreServiceI drugStoreServiceI;
	
	@Autowired
	private DrugPdRecordLogServiceI drugPdRecordLogServiceI;
	
	/**
	 * 获得一个对象
	 */
	@Override
	public void getById() {
		if (!DataTypeUtil.isBlank(DrugRecordId)) {
			writeJson(service.getById(DrugRecordId));
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
		if (!DataTypeUtil.isBlank(data.getStoreId())) {
			((DrugStoreCheckRecordServiceI) service).updateDrugStoreCheck(data);
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
		// SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
		// ConfigUtil.getSessionInfoName());
		// int customerId = sessionInfo.getUser().getCustomerId();
		// if (customerId > 0) {
		// hqlFilter.addFilter("QUERY_t#customerId_I_EQ", customerId + "");
		// }
		// super.addCutomerFilter(hqlFilter,"QUERY_t#custUser#customerInfo#customerId_I_EQ");

		if (!StringUtils.isBlank(getOpMod())) {
			if (getOpMod().equalsIgnoreCase(OP_TYPE_SELF)) {
				SessionInfo sessionInfo = (SessionInfo) getSession()
						.getAttribute(ConfigUtil.getSessionInfoName());
				hqlFilter.addFilter("QUERY_t#syuser#id_S_EQ", sessionInfo
						.getUser().getId() + "");
			} else if (getOpMod().equalsIgnoreCase(OP_TYPE_IN)) {
				hqlFilter.addFilter("QUERY_t#opType_B_EQ",
						DrugStoreCheck.OP_TYPE_IN + "");
			} else if (getOpMod().equalsIgnoreCase(OP_TYPE_OUT)) {
				hqlFilter.addFilter("QUERY_t#opType_B_EQ",
						DrugStoreCheck.OP_TYPE_OUT + "");
			} else if (getOpMod().equalsIgnoreCase(OP_TYPE_USE)) {
				hqlFilter.addFilter("QUERY_t#opType_B_EQ",
						DrugStoreCheck.OP_TYPE_USE + "");
			} else if (getOpMod().equalsIgnoreCase(OP_TYPE_PANDIAN)) {
				hqlFilter.addFilter("QUERY_t#opType_B_EQ",
						DrugStoreCheck.OP_TYPE_PANDIAN + "");
			}
		}

		hqlFilter.addFilter("QUERY_t#status_B_NE",
				DrugStoreCheck.STATUS_DELETED + "");

		grid.setTotal(((DrugStoreCheckRecordServiceI) service)
				.countDrugStoreCheckByFilter(hqlFilter));
		grid.setRows(((DrugStoreCheckRecordServiceI) service)
				.findDrugStoreCheckByFilter(hqlFilter, page, rows));

		writeJson(grid);

	}

	/**
	 * 获得体检表格下啦列表
	 */
	public void doNotNeedSessionAndSecurity_DrugRecordIdComboGrid() {
		Grid grid = new Grid();
		HqlFilter hqlFilter = new HqlFilter(getRequest());

		hqlFilter.addFilter("QUERY_t#customerName_S_LK",
				"%%" + StringUtils.defaultString(q) + "%%");
		hqlFilter.addFilter("QUERY_t#customerEnName_S_LK",
				"%%" + StringUtils.defaultString(q) + "%%");
		hqlFilter.addFilter("QUERY_t#customerStatus_B_NE",
				DrugStoreCheck.STATUS_DELETED + "");

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
	 * 纠正误差，权限独立控制
	 */
	public void valid() {
		this.save();
	}

	/**
	 * 保存一个体检
	 */
	public void save() {
		try {

			Json json = new Json();
			
			if (data != null) {
				SessionInfo sessionInfo = (SessionInfo) getSession()
						.getAttribute(ConfigUtil.getSessionInfoName());
				DrugSpecInfo drugSpecInfo = drugSpecInfoServiceI.getById(data
						.getDrugSpecInfo().getSpecId());

				if (!StringUtils.isBlank(getOpMod())) {
					if (getOpMod().equalsIgnoreCase(OP_TYPE_SELF)) {

					} else if (getOpMod().equalsIgnoreCase(OP_TYPE_IN)) {
						data.setOpType(DrugStoreCheck.OP_TYPE_IN);
					} else if (getOpMod().equalsIgnoreCase(OP_TYPE_OUT)) {
						data.setOpType(DrugStoreCheck.OP_TYPE_OUT);

						if (drugSpecInfo == null) {
							json.setSuccess(false);
							json.setMsg("出库失败！库存中没有该药品 !!");
							writeJson(json);
							return;
						}
						int num = drugStoreServiceI
								.getDrugStoreCount(data.getDrugSpecInfo()
										.getSpecId(), data.getDrugLotNo(), data
										.getUnit());

						if (num < 0) {
							json.setSuccess(false);
							json.setMsg("出库失败！库存中没有该药品 !!");
							writeJson(json);
							
							DrugPdRecordLog drugPdRecordLog = new DrugPdRecordLog();
							drugPdRecordLog.setDrugSpecInfo(data
									.getDrugSpecInfo());
							drugPdRecordLog.setNum(data.getNum());
							drugPdRecordLog.setOpOrder(order);
							drugPdRecordLog.setOpType(data.getOpType());
							drugPdRecordLog.setPrice(data.getPrice());
							drugPdRecordLog
									.setStatus(DrugPdRecordLog.STATUS_SUCC);
							drugPdRecordLog.setCustomerInfo(drugSpecInfo
									.getDrugInfo().getCustomerInfo());
							drugPdRecordLog.setReason("出库失败！库存中没有该药品 !!");

							drugPdRecordLogServiceI.saveDrugPdRecordLog(drugPdRecordLog,
									sessionInfo.getUser().getId());

							return;
						} else if (data.getNum() > num) {
							json.setSuccess(false);
							json.setMsg("出库失败！库存不足 !!");
							writeJson(json);

							DrugPdRecordLog drugPdRecordLog = new DrugPdRecordLog();
							drugPdRecordLog.setDrugSpecInfo(data
									.getDrugSpecInfo());
							drugPdRecordLog.setNum(data.getNum());
							drugPdRecordLog.setOpOrder(order);
							drugPdRecordLog.setOpType(data.getOpType());
							drugPdRecordLog.setPrice(data.getPrice());
							drugPdRecordLog
									.setStatus(DrugPdRecordLog.STATUS_SUCC);
							drugPdRecordLog.setCustomerInfo(drugSpecInfo
									.getDrugInfo().getCustomerInfo());
							drugPdRecordLog.setReason("出库失败！库存不足 ["
									+ data.getNum() + "] !!");

							drugPdRecordLogServiceI.saveDrugPdRecordLog(drugPdRecordLog, sessionInfo.getUser().getId());
							return;
						} else {
							data.setNum(data.getNum() * -1);
						}

					} else if (getOpMod().equalsIgnoreCase(OP_TYPE_USE)) {
						data.setOpType(DrugStoreCheck.OP_TYPE_USE);
						data.setNum(data.getNum() * -1);
					} else if (getOpMod().equalsIgnoreCase(OP_TYPE_PANDIAN)) {
						data.setOpType(DrugStoreCheck.OP_TYPE_PANDIAN);
					} else if (getOpMod().equalsIgnoreCase(OP_TYPE_JIUZHG)) {
						data.setOpType(DrugStoreCheck.OP_TYPE_JIUZHG);
					}
				}
				if (drugSpecInfo == null) {
					json.setSuccess(false);
					json.setMsg("出库失败！库存中没有该药品 !!");
					writeJson(json);
					return;
				}

				data.setCustomerInfo(drugSpecInfo.getDrugInfo()
						.getCustomerInfo());

				((DrugStoreCheckRecordServiceI) service)
						.saveDrugStoreCheckAndRelStore(data, sessionInfo
								.getUser().getId());

				json.setObj(data);
				json.setSuccess(true);
				if (data.getOpType() == DrugStoreCheck.OP_TYPE_JIUZHG) {
					json.setMsg("纠正成功！");
				} else if (data.getOpType() == DrugStoreCheck.OP_TYPE_PANDIAN) {
					json.setMsg("盘点成功！");
				} else {
					json.setMsg("新增成功！");
				}

			}
			writeJson(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除一个对象
	 */
	public void delete() {
		Json json = new Json();
		if (!DataTypeUtil.isBlank(DrugRecordId)) {
			DrugStoreCheck t = service.getById(DrugRecordId);
			t.setStatus(DrugStoreCheck.STATUS_DELETED);
			((DrugStoreCheckRecordServiceI) service).updateDrugStoreCheck(t);
			json.setSuccess(true);
			json.setMsg("删除成功！");
		}
		writeJson(json);
	}

	/**
	 * @return the DrugRecordId
	 */
	public int getDrugRecordId() {
		return DrugRecordId;
	}

	/**
	 * @param DrugRecordId
	 *            the DrugRecordId to set
	 */
	public void setDrugRecordId(int DrugRecordId) {
		this.DrugRecordId = DrugRecordId;
	}

	/**
	 * @return the opMod
	 */
	public String getOpMod() {
		return opMod;
	}

	/**
	 * @param opMod
	 *            the opMod to set
	 */
	public void setOpMod(String opMod) {
		this.opMod = opMod;
	}

}
