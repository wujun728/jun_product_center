package sy.action.app;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import sy.action.BaseAction;
import sy.action.OpResult;
import sy.converter.base.BigDecimalConverter;
import sy.model.app.DrugTimes;
import sy.model.app.EmpDrugUseRecord;
import sy.model.base.SessionInfo;
import sy.model.easyui.Grid;
import sy.model.easyui.Json;
import sy.model.easyui.TreeGrid;
import sy.service.app.EmpDrugUseRecordServiceI;
import sy.util.base.ConfigUtil;
import sy.util.base.DataTypeUtil;
import sy.util.base.HqlFilter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 员工用药记录
 */
@Namespace("/app")
@Action
public class EmpDrugUseRecordAction extends BaseAction<EmpDrugUseRecord> {
	private static final Logger logger = Logger
			.getLogger(EmpDrugUseRecordAction.class);
	private int EmpDrugUseRecordId;
	private int medicalId;
	private TreeGrid<EmpDrugUseRecord> treeGrid;
	private String params;
	private String ids;

	@Autowired
	public void setService(EmpDrugUseRecordServiceI service) {
		this.service = service;
	}

	/**
	 * 获得一个对象
	 */
	@Override
	public void getById() {
		if (!DataTypeUtil.isBlank(EmpDrugUseRecordId)) {
			writeJson(service.getById(EmpDrugUseRecordId));
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
			((EmpDrugUseRecordServiceI) service).updateEmpDrugUseRecord(data);
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
		super.addCutomerFilter(hqlFilter,
				"QUERY_t#custUser#customerInfo#customerId_I_EQ");

		if (this.getMedicalId() > 0) {
			hqlFilter.addFilter("QUERY_t#medicalId_I_EQ", getMedicalId() + "");
		}

		hqlFilter.addFilter("QUERY_t#status_B_NE",
				EmpDrugUseRecord.STATUS_DELETED + "");

		grid.setTotal(((EmpDrugUseRecordServiceI) service)
				.countEmpDrugUseRecordByFilter(hqlFilter));
		grid.setRows(((EmpDrugUseRecordServiceI) service)
				.findEmpDrugUseRecordByFilter(hqlFilter, page, rows));

		writeJson(grid);
	}

	/**
	 * 获得体检表格下啦列表
	 */
	public void doNotNeedSessionAndSecurity_EmpDrugUseRecordIdComboGrid() {
		Grid grid = new Grid();
		HqlFilter hqlFilter = new HqlFilter(getRequest());

		hqlFilter.addFilter("QUERY_t#customerName_S_LK",
				"%%" + StringUtils.defaultString(q) + "%%");
		hqlFilter.addFilter("QUERY_t#customerEnName_S_LK",
				"%%" + StringUtils.defaultString(q) + "%%");
		hqlFilter.addFilter("QUERY_t#customerStatus_B_NE",
				EmpDrugUseRecord.STATUS_DELETED + "");

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
			((EmpDrugUseRecordServiceI) service).saveEmpDrugUseRecord(data,
					sessionInfo.getUser().getId());
			json.setSuccess(true);
			json.setMsg("新增成功！");
		}
		writeJson(json);
	}

	public void saveList() {
		try {
			Json json = new Json();
			if (this.treeGrid != null) {
				SessionInfo sessionInfo = (SessionInfo) getSession()
						.getAttribute(ConfigUtil.getSessionInfoName());
				OpResult result = ((EmpDrugUseRecordServiceI) service)
						.saveEmpDrugUseRecord2(treeGrid.getRows(), sessionInfo
								.getUser().getId());
				if (result != null) {
					if (!result.isResult()) {
						json.setSuccess(false);
						json.setMsg(result.getMsg());
						writeJson(json);
						return;
					}
				}
				json.setSuccess(true);
				json.setMsg("分配药品成功！");
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
		if (!DataTypeUtil.isBlank(EmpDrugUseRecordId)) {
			EmpDrugUseRecord t = service.getById(EmpDrugUseRecordId);
			t.setStatus(EmpDrugUseRecord.STATUS_DELETED);
			((EmpDrugUseRecordServiceI) service).updateEmpDrugUseRecord(t);
			json.setSuccess(true);
			json.setMsg("删除成功！");
		}
		writeJson(json);
	}

	public void deleteIds() {
		Json json = new Json();
		if (this.getIds() != null) {
			String[] list = this.getIds().split(",");
			if (list.length > 0) {
				for (String str : list) {
					if (!StringUtils.isBlank(str)) {
						EmpDrugUseRecord t = service.getById(Integer
								.valueOf(str));

						((EmpDrugUseRecordServiceI) service).delete(t);
					}
					json.setSuccess(true);
					json.setMsg("删除成功！id =" + str);
				}
			}
		}
		writeJson(json);
	}

	/**
	 * @return the EmpDrugUseRecordId
	 */
	public int getEmpDrugUseRecordId() {
		return EmpDrugUseRecordId;
	}

	/**
	 * @param EmpDrugUseRecordId
	 *            the EmpDrugUseRecordId to set
	 */
	public void setEmpDrugUseRecordId(int EmpDrugUseRecordId) {
		this.EmpDrugUseRecordId = EmpDrugUseRecordId;
	}

	/**
	 * @return the medicalId
	 */
	public int getMedicalId() {
		return medicalId;
	}

	/**
	 * @param medicalId
	 *            the medicalId to set
	 */
	public void setMedicalId(int medicalId) {
		this.medicalId = medicalId;
	}

	/**
	 * @return the treeGrid
	 */
	public TreeGrid<EmpDrugUseRecord> getTreeGrid() {
		return treeGrid;
	}

	/**
	 * @param treeGrid
	 *            the treeGrid to set
	 */
	public void setTreeGrid(TreeGrid<EmpDrugUseRecord> treeGrid) {
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

			@SuppressWarnings("unchecked")
			TreeGrid<JSONObject> t = (TreeGrid<JSONObject>) JSON.parseObject(
					this.params, (TreeGrid.class));
			int size = t.getRows().size();

			List<EmpDrugUseRecord> list = new ArrayList<EmpDrugUseRecord>();
			for (int i = 0; i < size; i++) {

				Object pAttrObj = (Object) t.getRows().get(i).get("id");
				int id = -1;
				if (pAttrObj instanceof String) {
					id = (int) ((pAttrObj != null && !((String) pAttrObj)
							.isEmpty()) ? Integer.valueOf((String) pAttrObj)
							: -1);
				} else if (pAttrObj instanceof Integer) {
					id = (int) ((pAttrObj != null && ((Integer) pAttrObj)
							.intValue() > 0) ? ((Integer) pAttrObj).intValue()
							: -1);
				}
				// ss 是标识位
				Object pAttrObjSS = (Object) t.getRows().get(i).get("ss");
				if (pAttrObjSS instanceof String) {
					String ss = (String) ((pAttrObjSS != null && !((String) pAttrObjSS)
							.isEmpty()) ? pAttrObjSS : "");
					if (ss != null && ss.equalsIgnoreCase("I")) {
						id = -1;
					}
				}

				int medicalId = Integer.valueOf((String) (t.getRows().get(i))
						.get("medicalId"));

				Object o = (Object) (t.getRows().get(i)).get("drugCode");
				int drugCode = -1;
				if (o instanceof JSONObject) {
					drugCode = (int) ((JSONObject) o).get("drugCode");
				} else if (o instanceof String) {
					drugCode = Integer.valueOf((String) o);
				} else if (o instanceof Integer) {
					drugCode = (int) o;
				}

				String drugName = (String) (t.getRows().get(i)).get("drugName");

				BigDecimalConverter conv = new BigDecimalConverter();
				BigDecimal price = BigDecimal.ZERO;
				Object o_price = (Object) (t.getRows().get(i)).get("price");
				price = conv.convertFromObject(o_price, BigDecimal.class);

				int number = 0;
				Object o_number = (Object) (t.getRows().get(i)).get("number");
				if (o_number instanceof String) {
					number = Integer.valueOf((String) o_number);
				} else if (o_number instanceof Integer) {
					number = (int) o_number;
				}

				byte ebType = 0;
				Object o_ebType = (Object) (t.getRows().get(i)).get("ebType");
				if (o_ebType instanceof String) {
					if (((String) o_ebType).isEmpty()) {
						ebType = 0;
					} else {
						ebType = Byte.valueOf((String) o_ebType);
					}
				} else if (o_ebType instanceof Byte) {
					ebType = (byte) o_ebType;
				}

				BigDecimal medicalFee = BigDecimal.ZERO;
				Object o_medicalFee = (Object) (t.getRows().get(i))
						.get("medicalFee");
				medicalFee = conv.convertFromObject(o_medicalFee,
						BigDecimal.class);

				// BigDecimal empTakeFee = BigDecimal.ZERO;
				// Object o_empTakeFee = (Object)
				// (t.getRows().get(i)).get("empTakeFee");
				// empTakeFee =
				// conv.convertFromObject(o_empTakeFee,BigDecimal.class);
				//
				//
				// BigDecimal insuranceFee = BigDecimal.ZERO;
				// Object o_insuranceFee = (Object)
				// (t.getRows().get(i)).get("insuranceFee");
				// insuranceFee =
				// conv.convertFromObject(o_insuranceFee,BigDecimal.class);

				String ext1 = (String) (t.getRows().get(i)).get("ext1");
				Object drugTimesObj = (Object) (t.getRows().get(i))
						.get("drugTimes");
				int drugTimesId = -1;
				if (drugTimesObj instanceof String) {
					drugTimesId = Integer.valueOf((String) drugTimesObj);
				} else if (drugTimesObj instanceof Integer) {
					drugTimesId = (int) drugTimesObj;
				} else if (drugTimesObj instanceof JSONObject) {
					drugTimesId = ((int) ((JSONObject) drugTimesObj).get("id"));
				}
				DrugTimes drugTimes = new DrugTimes();

				drugTimes.setId(drugTimesId);
				// drugTimes.setDrugName(drugName);

				EmpDrugUseRecord empDrugUseRecord = new EmpDrugUseRecord();
				empDrugUseRecord.setId(id);
				empDrugUseRecord.setMedicalId(medicalId);
				empDrugUseRecord.setDrugTimes(drugTimes);

				empDrugUseRecord.setPrice(price);
				empDrugUseRecord.setNumber(number);
				empDrugUseRecord.setEbType(ebType);
				empDrugUseRecord.setMedicalFee(medicalFee);
				empDrugUseRecord.setEmpTakeFee(BigDecimal.ZERO);
				empDrugUseRecord.setInsuranceFee(BigDecimal.ZERO);

				empDrugUseRecord.setExt1(ext1);

				list.add(empDrugUseRecord);
			}
			TreeGrid<EmpDrugUseRecord> treeGrid = new TreeGrid<EmpDrugUseRecord>();

			treeGrid.setTotal(t.getTotal());
			treeGrid.setRows(list);

			this.setTreeGrid(treeGrid);
		}

	}

	/**
	 * @return the ids
	 */
	public String getIds() {
		return ids;
	}

	/**
	 * @param ids
	 *            the ids to set
	 */
	public void setIds(String ids) {
		this.ids = ids;
	}
}
