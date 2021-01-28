package sy.action.app;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import sy.action.BaseAction;
import sy.model.app.EmpAttendRecord;
import sy.model.base.SessionInfo;
import sy.model.base.Syuser;
import sy.model.easyui.Grid;
import sy.model.easyui.Json;
import sy.service.app.EmpAttendRecordServiceI;
import sy.service.base.SyuserServiceI;
import sy.util.base.ConfigUtil;
import sy.util.base.DataTypeUtil;
import sy.util.base.DateUtil;
import sy.util.base.HqlFilter;

/**
 * 员工考勤记录
 */
@Namespace("/app")
@Action
public class MedicalAttendAction extends BaseAction<EmpAttendRecord> {
	private static final Logger logger = Logger
			.getLogger(MedicalAttendAction.class);
	private int empAttendRecordId;
	// 部门
	private String syorganizationId;
	// 查询参数
	private String queryType;

	@Autowired
	private SyuserServiceI syuserServiceI;

	public class OpResult {
		// 数据编号
		int id;
		String sbTime;
		String xbTime;
		int isLeave;

		/**
		 * @return the sbTime
		 */
		public String getSbTime() {
			return sbTime;
		}

		/**
		 * @param sbTime
		 *            the sbTime to set
		 */
		public void setSbTime(String sbTime) {
			this.sbTime = sbTime;
		}

		/**
		 * @return the xbTime
		 */
		public String getXbTime() {
			return xbTime;
		}

		/**
		 * @param xbTime
		 *            the xbTime to set
		 */
		public void setXbTime(String xbTime) {
			this.xbTime = xbTime;
		}

		/**
		 * @return the id
		 */
		public int getId() {
			return id;
		}

		/**
		 * @param id
		 *            the id to set
		 */
		public void setId(int id) {
			this.id = id;
		}

		/**
		 * @return the isLeave
		 */
		public int getIsLeave() {
			return isLeave;
		}

		/**
		 * @param isLeave
		 *            the isLeave to set
		 */
		public void setIsLeave(int isLeave) {
			this.isLeave = isLeave;
		}
	}

	/**
	 * @param service
	 */
	@Autowired
	public void setService(EmpAttendRecordServiceI service) {
		this.service = service;
	}

	private boolean checkBandIP() {
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		Syuser t = ((SyuserServiceI) syuserServiceI).getById(sessionInfo
				.getUserId());

		if (t.getPhyAddr() == null) {
			return false;
		}

		return true;
	}

	/**
	 * 判断是否数据重复,一个用户一天只能有一个数据
	 * 
	 * return true 重复， false 有效
	 */
	private boolean checkRepeat() {
		// 获得当天时间
		String yyyymmdd = DateUtil.getNowDate();
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		// // 过滤用户ID

		HqlFilter hqlFilter = new HqlFilter(getRequest());
		hqlFilter.setNoWhere(true);
		hqlFilter.addFilter("QUERY_t#yyyymmdd_S_EQ", yyyymmdd);

		hqlFilter.addFilter("QUERY_t#status_B_NE",
				EmpAttendRecord.STATUS_DELETED + "");
		hqlFilter.addFilter("QUERY_t#syuser#id_S_EQ", sessionInfo.getUser()
				.getId() + "");

		List<EmpAttendRecord> list = ((EmpAttendRecordServiceI) service)
				.findEmpAttendRecordByFilter(hqlFilter);

		if (list.size() > 0) {
			return true;

		} else {
			return false;
		}

	}

	public void getCurrentUserInfo() {
		// 查询当前账号，当天的考勤数据
		if (!StringUtils.isEmpty(queryType)
				&& queryType.equalsIgnoreCase("me-today")) {
			// 获得当天时间
			String yyyymmdd = DateUtil.getNowDate();
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
					ConfigUtil.getSessionInfoName());
			// // 过滤用户ID

			HqlFilter hqlFilter = new HqlFilter(getRequest());
			hqlFilter.setNoWhere(true);
			hqlFilter.addFilter("QUERY_t#yyyymmdd_S_EQ", yyyymmdd);

			hqlFilter.addFilter("QUERY_t#status_B_NE",
					EmpAttendRecord.STATUS_DELETED + "");
			hqlFilter.addFilter("QUERY_t#syuser#id_S_EQ", sessionInfo.getUser()
					.getId() + "");

			List<EmpAttendRecord> list = ((EmpAttendRecordServiceI) service)
					.findEmpAttendRecordByFilter(hqlFilter);

			if (list.size() > 0) {
				EmpAttendRecord empAttendRecord = list.get(0);

				if (empAttendRecord != null) {
					writeJson(empAttendRecord);
				} else {
					Json j = new Json();
					j.setMsg("没有找到记录 ！");
					writeJson(j);
				}

			} else {
				Json j = new Json();
				j.setMsg("没有找到记录 ！");
				j.setSuccess(true);
				writeJson(j);
			}

		}
	}

	/**
	 * 获得一个对象
	 */
	@Override
	public void getById() {
		if (!DataTypeUtil.isBlank(empAttendRecordId)) {
			writeJson(service.getById(empAttendRecordId));
		} else {
			Json j = new Json();
			j.setMsg("主键不可为空！");
			writeJson(j);
		}
	}

	public void bandIP() {
		Json json = new Json();
		 
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
					ConfigUtil.getSessionInfoName());
			if (!checkBandIP()) {
				Syuser t = ((SyuserServiceI) syuserServiceI)
						.getById(sessionInfo.getUserId());

				String ip = sessionInfo.getUser().getIp();
				t.setPhyAddr(ip);
				
				((SyuserServiceI) syuserServiceI).updateSyuser(t);
				
				json.setSuccess(true);
				json.setMsg("绑定ip地址成功！");
				writeJson(json);
				return;
			}else{
				json.setSuccess(false);
				json.setMsg("已经绑定ip地址！");
			}
			
		 
		writeJson(json);
	}

	/**
	 * 更新员工考勤记录部门
	 */
	public void update() {
		Json json = new Json();
		if (!DataTypeUtil.isBlank(data.getId())) {

			if (!StringUtils.isEmpty(queryType)
					&& queryType.equalsIgnoreCase("SB")) {
				EmpAttendRecord tmp = service.getById(data.getId());
				if (tmp != null && tmp.getWorkTime() != null) {
					json.setSuccess(false);
					OpResult op = new OpResult();
					op.setId(data.getId());
					op.setSbTime(data.getWorkTime() != null ? data
							.getWorkTime().toLocaleString() : null);
					op.setXbTime(data.getAfterWorkTime() != null ? data
							.getAfterWorkTime().toLocaleString() : null);
					op.setIsLeave(data.getIsLeave());
					json.setObj(op);
					json.setMsg("已经提交过了！");
					writeJson(json);
					return;
				}
				data.setWorkTime(new Date());

			} else if (!StringUtils.isEmpty(queryType)
					&& queryType.equalsIgnoreCase("XB")) {
				// 如果没上班则不能下班
				EmpAttendRecord tmp = service.getById(data.getId());
				if (tmp != null && tmp.getWorkTime() == null) {
					json.setSuccess(false);
					OpResult op = new OpResult();
					op.setId(data.getId());
					op.setSbTime(data.getWorkTime() != null ? data
							.getWorkTime().toLocaleString() : null);
					op.setXbTime(data.getAfterWorkTime() != null ? data
							.getAfterWorkTime().toLocaleString() : null);
					op.setIsLeave(data.getIsLeave());
					json.setObj(op);
					json.setMsg("提交失败！你今天还没上班操作!!");
					writeJson(json);
					return;
				}
				if (tmp != null && tmp.getAfterWorkTime() != null) {
					json.setSuccess(false);
					OpResult op = new OpResult();
					op.setId(data.getId());
					op.setSbTime(data.getWorkTime() != null ? data
							.getWorkTime().toLocaleString() : null);
					op.setXbTime(data.getAfterWorkTime() != null ? data
							.getAfterWorkTime().toLocaleString() : null);
					op.setIsLeave(data.getIsLeave());
					json.setObj(op);
					json.setMsg("提交失败！你今天已经提交过下班操作!!");
					writeJson(json);
					return;
				}
				data.setAfterWorkTime(new Date());
			} else if (!StringUtils.isEmpty(queryType)
					&& queryType.equalsIgnoreCase("qj")) {
				data.setIsLeave(EmpAttendRecord.NO_LEAVE);
			}
			
			//是否绑定了地址
			if(!checkSameIP()){
				json.setSuccess(false);
				json.setMsg("与绑定的电脑不匹配 ！");
				writeJson(json);
				return ;
			}
			
			((EmpAttendRecordServiceI) service).updateEmpAttendRecord(data);
			json.setSuccess(true);
			OpResult op = new OpResult();
			op.setId(data.getId());
			op.setSbTime(data.getWorkTime() != null ? data.getWorkTime()
					.toLocaleString() : null);
			op.setXbTime(data.getAfterWorkTime() != null ? data
					.getAfterWorkTime().toLocaleString() : null);
			op.setIsLeave(data.getIsLeave());
			json.setObj(op);
			json.setMsg("提交成功！");
		}
		writeJson(json);
	}

	/**
	 * 角色grid
	 */
	public void gridAll() {
		Grid grid = new Grid();
		HqlFilter hqlFilter = new HqlFilter(getRequest(), true);
		hqlFilter.setNoWhere(true);
		hqlFilter.addFilter("QUERY_t#status_B_NE",
				EmpAttendRecord.STATUS_DELETED + "");
		// 增加玩家账号的限制。

		// 获得当天时间
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		// 过滤用户ID
		// hqlFilter.addFilter("QUERY_t#syuser#id_S_EQ",
		// sessionInfo.getUser().getId() + "");

		grid.setTotal(((EmpAttendRecordServiceI) service)
				.countEmpAttendRecordByFilter(hqlFilter));
		grid.setRows(((EmpAttendRecordServiceI) service)
				.findEmpAttendRecordByFilter(hqlFilter, page, rows));

		writeJson(grid);
	}

	/**
	 * 角色grid
	 */
	public void grid() {
		if (!StringUtils.isEmpty(queryType)
				&& queryType.equalsIgnoreCase("NODEPT")) {
			Grid grid = new Grid();
			HqlFilter hqlFilter = new HqlFilter(getRequest(), false);
			// hqlFilter.setNoWhere(true);
			hqlFilter.addFilter("QUERY_t#status_B_NE",
					EmpAttendRecord.STATUS_DELETED + "");

			// 增加玩家账号的限制。

			// 获得当天时间
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
					ConfigUtil.getSessionInfoName());
			// 过滤用户ID
			hqlFilter.addFilter("QUERY_t#syuser#id_S_EQ", sessionInfo.getUser()
					.getId() + "");

			grid.setTotal(((EmpAttendRecordServiceI) service)
					.countEmpAttendRecordByFilterNoDept(hqlFilter));
			grid.setRows(((EmpAttendRecordServiceI) service)
					.findEmpAttendRecordByFilterNoDept(hqlFilter, page, rows));

			writeJson(grid);

		} else {

			Grid grid = new Grid();
			HqlFilter hqlFilter = new HqlFilter(getRequest());
			hqlFilter.setNoWhere(true);
			hqlFilter.addFilter("QUERY_t#status_B_NE",
					EmpAttendRecord.STATUS_DELETED + "");

			hqlFilter.addFilter("QUERY_o#id_S_EQ", this.getSyorganizationId()
					+ "");
			// 增加玩家账号的限制。

			// 获得当天时间
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
					ConfigUtil.getSessionInfoName());
			// 过滤用户ID
			hqlFilter.addFilter("QUERY_t#syuser#id_S_EQ", sessionInfo.getUser()
					.getId() + "");

			grid.setTotal(((EmpAttendRecordServiceI) service)
					.countEmpAttendRecordByFilter(hqlFilter));
			grid.setRows(((EmpAttendRecordServiceI) service)
					.findEmpAttendRecordByFilter(hqlFilter, page, rows));

			writeJson(grid);
		}

	}

	/**
	 * 获得员工考勤记录表格下啦列表
	 */
	public void doNotNeedSessionAndSecurity_EmpAttendRecordIdComboGrid() {
		Grid grid = new Grid();
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		hqlFilter.setNoWhere(true);
		hqlFilter.addFilter("QUERY_t#customerName_S_LK",
				"%%" + StringUtils.defaultString(q) + "%%");
		hqlFilter.addFilter("QUERY_t#customerEnName_S_LK",
				"%%" + StringUtils.defaultString(q) + "%%");
		hqlFilter.addFilter("QUERY_t#customerStatus_B_NE",
				EmpAttendRecord.STATUS_DELETED + "");

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
	 * 
	 * @return true 相同的地址 。 false 不同的地址
	 */
	public boolean checkSameIP(){
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		
		Syuser t = ((SyuserServiceI) syuserServiceI)
				.getById(sessionInfo.getUserId());

		String ip = sessionInfo.getUser().getIp();
		if(t != null && t.getPhyAddr() != null && t.getPhyAddr().equalsIgnoreCase(ip)){
			return true;
		}
		
		return false;
	}
	/**
	 * 保存一个员工考勤记录
	 */
	public void save() {
		Json json = new Json();
		if (data != null) {
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
					ConfigUtil.getSessionInfoName());
			boolean canXB = false;
			if (checkRepeat()) {
				// 重复数据
				if (!StringUtils.isEmpty(queryType)
						&& queryType.equalsIgnoreCase("XB")) {
					canXB = true;
				} else {
					json.setSuccess(true);
					OpResult op = new OpResult();
					op.setId(data.getId());
					op.setSbTime(data.getWorkTime() != null ? data
							.getWorkTime().toLocaleString() : null);
					op.setXbTime(data.getAfterWorkTime() != null ? data
							.getAfterWorkTime().toLocaleString() : null);
					op.setIsLeave(data.getIsLeave());
					json.setObj(op);

					json.setMsg("已经提交过了！");
					writeJson(json);
					return;
				}
			} else {
				if (!StringUtils.isEmpty(queryType)
						&& queryType.equalsIgnoreCase("XB")) {
					canXB = true;
					if (canXB) {
						// 不能直接下班
						json.setSuccess(true);
						OpResult op = new OpResult();
						op.setId(data.getId());
						op.setSbTime(data.getWorkTime() != null ? data
								.getWorkTime().toLocaleString() : null);
						op.setXbTime(data.getAfterWorkTime() != null ? data
								.getAfterWorkTime().toLocaleString() : null);
						op.setIsLeave(data.getIsLeave());
						json.setObj(op);

						json.setMsg("不能直接下班！");
						writeJson(json);
						return;
					}
				} else {
					canXB = false;
				}
			}

			String yyyymmdd = DateUtil.getNowDate();
			data.setYyyymmdd(yyyymmdd);
			data.setIsLeave(EmpAttendRecord.NO_LEAVE);
			if (!StringUtils.isEmpty(queryType)
					&& queryType.equalsIgnoreCase("SB")) {
				data.setWorkTime(new Date());

			} else if (!StringUtils.isEmpty(queryType)
					&& queryType.equalsIgnoreCase("XB")) {
				data.setAfterWorkTime(new Date());
			} else if (!StringUtils.isEmpty(queryType)
					&& queryType.equalsIgnoreCase("qj")) {
				data.setIsLeave(EmpAttendRecord.NO_LEAVE);
			}
			if (!canXB) {
				//是否绑定了地址
				if(!checkSameIP()){
					json.setSuccess(false);
					json.setMsg("与绑定的电脑不匹配 ！");
					writeJson(json);
					return ;
				}
				((EmpAttendRecordServiceI) service).saveEmpAttendRecord(data,
						sessionInfo.getUser().getId());
			} else {

			}

			json.setSuccess(true);
			OpResult op = new OpResult();
			op.setId(data.getId());
			op.setSbTime(data.getWorkTime() != null ? data.getWorkTime()
					.toLocaleString() : null);
			op.setXbTime(data.getAfterWorkTime() != null ? data
					.getAfterWorkTime().toLocaleString() : null);
			op.setIsLeave(data.getIsLeave());
			json.setObj(op);

			json.setMsg("提交成功！");
		}
		writeJson(json);
	}

	/**
	 * 删除一个对象
	 */
	public void delete() {
		Json json = new Json();
		if (!DataTypeUtil.isBlank(empAttendRecordId)) {
			EmpAttendRecord t = service.getById(empAttendRecordId);
			t.setStatus(EmpAttendRecord.STATUS_DELETED);
			((EmpAttendRecordServiceI) service).updateEmpAttendRecord(t);
			json.setSuccess(true);
			json.setMsg("删除成功！");
		}
		writeJson(json);
	}

	/**
	 * @return the EmpAttendRecordId
	 */
	public int getEmpAttendRecordId() {
		return empAttendRecordId;
	}

	/**
	 * @param EmpAttendRecordId
	 *            the EmpAttendRecordId to set
	 */
	public void setEmpAttendRecordId(int empAttendRecordId) {
		this.empAttendRecordId = empAttendRecordId;
	}

	/**
	 * @return the queryType
	 */
	public String getQueryType() {
		return queryType;
	}

	/**
	 * @param queryType
	 *            the queryType to set
	 */
	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	/**
	 * @return the syorganizationId
	 */
	public String getSyorganizationId() {
		return syorganizationId;
	}

	/**
	 * @param syorganizationId
	 *            the syorganizationId to set
	 */
	public void setSyorganizationId(String syorganizationId) {
		this.syorganizationId = syorganizationId;
	}

	/**
	 * @return the syuserServiceI
	 */
	public SyuserServiceI getSyuserServiceI() {
		return syuserServiceI;
	}

	/**
	 * @param syuserServiceI
	 *            the syuserServiceI to set
	 */
	public void setSyuserServiceI(SyuserServiceI syuserServiceI) {
		this.syuserServiceI = syuserServiceI;
	}

}
