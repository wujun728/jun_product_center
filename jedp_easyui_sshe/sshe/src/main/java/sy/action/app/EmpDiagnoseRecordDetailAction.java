package sy.action.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import sy.action.BaseAction;
import sy.model.app.EmpDiagnoseRecordDetail;
import sy.model.app.PhysicalTypeDef;
import sy.model.base.SessionInfo;
import sy.model.easyui.Grid;
import sy.model.easyui.Json;
import sy.model.easyui.Tree;
import sy.model.easyui.TreeGrid;
import sy.service.app.EmpDiagnoseRecordDetailServiceI;
import sy.service.app.PhysicalTypeDefServiceI;
import sy.util.base.BeanUtils;
import sy.util.base.ConfigUtil;
import sy.util.base.DataTypeUtil;
import sy.util.base.HqlFilter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 员工就诊记录明细
 */
@Namespace("/app")
@Action
public class EmpDiagnoseRecordDetailAction extends
		BaseAction<EmpDiagnoseRecordDetail> {

	private static final long serialVersionUID = -1845239692435795896L;

	private static final Logger logger = Logger
			.getLogger(EmpDiagnoseRecordDetailAction.class);
	private int EmpDiagnoseRecordDetailId;
	private TreeGrid<EmpDiagnoseRecordDetail> treeGrid;
	private String params;
	private int medicalId;
	private String ids;
	private String opMod;

	@Autowired
	public void setService(EmpDiagnoseRecordDetailServiceI service) {
		this.service = service;
	}

	@Autowired
	private PhysicalTypeDefServiceI physicalTypeDefServiceI;

	/**
	 * 获得一个对象
	 */
	@Override
	public void getById() {
		if (!DataTypeUtil.isBlank(EmpDiagnoseRecordDetailId)) {
			writeJson(service.getById(EmpDiagnoseRecordDetailId));
		} else {
			Json j = new Json();
			j.setMsg("主键不可为空！");
			writeJson(j);
		}
	}

	/**
	 * 更新
	 */
	public void update() {
		Json json = new Json();
		if (!DataTypeUtil.isBlank(data.getId())) {
			((EmpDiagnoseRecordDetailServiceI) service)
					.updateEmpDiagnoseRecordDetail(data);
			json.setSuccess(true);
			json.setMsg("修改成功！");
		}
		writeJson(json);
	}

	/**
	 * grid
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

		if (opMod != null && opMod.equalsIgnoreCase("FZ")) {
			hqlFilter.addFilter("QUERY_t#type_B_EQ", "1");

			long total = ((EmpDiagnoseRecordDetailServiceI) service)
					.countEmpDiagnoseRecordDetailByFilter(hqlFilter);
			List<EmpDiagnoseRecordDetail> list = ((EmpDiagnoseRecordDetailServiceI) service)
					.findEmpDiagnoseRecordDetailByFilter(hqlFilter, page, rows);

			grid.setTotal((long) list.size());
			grid.setRows(list);
		}
		if (opMod != null && opMod.equalsIgnoreCase("TG")) {
			hqlFilter.addFilter("QUERY_t#type_B_EQ", "0");

			long total = ((EmpDiagnoseRecordDetailServiceI) service)
					.countEmpDiagnoseRecordDetailByFilter(hqlFilter);
			List<EmpDiagnoseRecordDetail> list = ((EmpDiagnoseRecordDetailServiceI) service)
					.findEmpDiagnoseRecordDetailByFilter(hqlFilter, page, rows);

			grid.setTotal((long) list.size());
			grid.setRows(list);
		}

		writeJson(grid);
	}

	/**
	 * 获得员工就诊记录明细表格下啦列表
	 */
	public void doNotNeedSessionAndSecurity_EmpDiagnoseRecordDetailIdComboGrid() {
		Grid grid = new Grid();
		HqlFilter hqlFilter = new HqlFilter(getRequest());

		hqlFilter.addFilter("QUERY_t#customerName_S_LK",
				"%%" + StringUtils.defaultString(q) + "%%");
		hqlFilter.addFilter("QUERY_t#customerEnName_S_LK",
				"%%" + StringUtils.defaultString(q) + "%%");

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
	 * 保存一个员工就诊记录明细
	 */
	public void save() {
		Json json = new Json();
		if (data != null) {
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
					ConfigUtil.getSessionInfoName());
			((EmpDiagnoseRecordDetailServiceI) service)
					.saveEmpDiagnoseRecordDetail(data, sessionInfo.getUser()
							.getId());
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
			List<EmpDiagnoseRecordDetail> empDiagnoseRecordDetailList = treeGrid
					.getRows();

			for (EmpDiagnoseRecordDetail m : empDiagnoseRecordDetailList) {
				if (m.getPhysicalTypeDef() == null) {
					json.setSuccess(false);
					json.setMsg("数据有错误！");
					writeJson(json);
					return;
				}
				if (m.getPhysicalTypeDef() != null
						&& m.getPhysicalTypeDef().getPhyId() < 0) {
					json.setSuccess(false);
					json.setMsg("数据有错误！");
					writeJson(json);
					return;
				}
			}
			((EmpDiagnoseRecordDetailServiceI) service)
					.saveEmpDiagnoseRecordDetail(empDiagnoseRecordDetailList,
							sessionInfo.getUser().getId());
			json.setSuccess(true);
			json.setMsg("保存成功！");
		}
		writeJson(json);
	}

	/**
	 * 删除一个对象
	 */
	public void delete() {
		Json json = new Json();
		if (!DataTypeUtil.isBlank(EmpDiagnoseRecordDetailId)) {
			EmpDiagnoseRecordDetail t = service
					.getById(EmpDiagnoseRecordDetailId);

			((EmpDiagnoseRecordDetailServiceI) service).delete(t);
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
						EmpDiagnoseRecordDetail t = service.getById(Integer
								.valueOf(str));

						((EmpDiagnoseRecordDetailServiceI) service).delete(t);
					}
					json.setSuccess(true);
					json.setMsg("删除成功！id =" + str);
				}
			}
		}
		writeJson(json);
	}

	/**
	 * @return the EmpDiagnoseRecordDetailId
	 */
	public int getEmpDiagnoseRecordDetailId() {
		return EmpDiagnoseRecordDetailId;
	}

	/**
	 * @param EmpDiagnoseRecordDetailId
	 *            the EmpDiagnoseRecordDetailId to set
	 */
	public void setEmpDiagnoseRecordDetailId(int EmpDiagnoseRecordDetailId) {
		this.EmpDiagnoseRecordDetailId = EmpDiagnoseRecordDetailId;
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

			List<EmpDiagnoseRecordDetail> list = new ArrayList<EmpDiagnoseRecordDetail>();
			for (int i = 0; i < size; i++) {
				String description = (String) (t.getRows().get(i))
						.get("description");
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

				Object pAttrObjSS = (Object) t.getRows().get(i).get("ss");
				if (pAttrObjSS instanceof String) {
					String ss = (String) ((pAttrObjSS != null && !((String) pAttrObjSS)
							.isEmpty()) ? pAttrObjSS : "");
					if (ss != null && ss.equalsIgnoreCase("I")) {
						id = -1;
					}
				}
				byte type = 0;
				Object pAttrObjSType = (Object) t.getRows().get(i).get("stype");
				if (pAttrObjSType instanceof String) {
					String stype = (String) ((pAttrObjSType != null && !((String) pAttrObjSType)
							.isEmpty()) ? pAttrObjSType : "");
					if (stype != null && stype.equalsIgnoreCase("TG")) {
						type = EmpDiagnoseRecordDetail.TYPE_TG;
					} else if (stype != null && stype.equalsIgnoreCase("FZ")) {
						type = EmpDiagnoseRecordDetail.TYPE_FZ;
					}
				}

				int medicalId = Integer.valueOf((String) (t.getRows().get(i))
						.get("medicalId"));
				Object o = (Object) (t.getRows().get(i)).get("phyTypeList");
				int phyId = -1;
				if (o instanceof JSONObject) {
					phyId = (int) ((JSONObject) o).get("phyId");
				} else if (o instanceof String) {
					phyId = Integer.valueOf((String) o);
				} else if (o instanceof Integer) {
					phyId = (int) o;
				}

				PhysicalTypeDef physicalTypeDef = new PhysicalTypeDef();

				physicalTypeDef.setPhyId(phyId);

				EmpDiagnoseRecordDetail empDiagnoseRecordDetail = new EmpDiagnoseRecordDetail();
				empDiagnoseRecordDetail.setDescription(description);
				empDiagnoseRecordDetail.setId(id);
				empDiagnoseRecordDetail.setMedicalId(medicalId);
				empDiagnoseRecordDetail.setType(type);
				empDiagnoseRecordDetail.setPhysicalTypeDef(physicalTypeDef);

				list.add(empDiagnoseRecordDetail);
			}
			TreeGrid<EmpDiagnoseRecordDetail> treeGrid = new TreeGrid<EmpDiagnoseRecordDetail>();

			treeGrid.setTotal(t.getTotal());
			treeGrid.setRows(list);

			this.setTreeGrid(treeGrid);
		}

	}

	/**
	 * @return the treeGrid
	 */
	public TreeGrid<EmpDiagnoseRecordDetail> getTreeGrid() {
		return treeGrid;
	}

	/**
	 * @param treeGrid
	 *            the treeGrid to set
	 */
	public void setTreeGrid(TreeGrid<EmpDiagnoseRecordDetail> treeGrid) {
		this.treeGrid = treeGrid;
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
