package sy.action.app;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import sy.action.BaseAction;
import sy.model.app.EmpJiabanRecord;
import sy.model.app.EmpJiabanRecordDetail;
import sy.model.base.SessionInfo;
import sy.model.easyui.Grid;
import sy.model.easyui.Json;
import sy.service.app.EmpJiabanRecordServiceI;
import sy.util.base.ConfigUtil;
import sy.util.base.DataTypeUtil;
import sy.util.base.DateUtil;
import sy.util.base.HqlFilter;
import sy.util.base.IpUtil;

/**
 * 加班请假记录
 */
@Namespace("/app")
@Action
public class EmpJiabanAction extends BaseAction<EmpJiabanRecord> {
	private static final Logger logger = Logger
			.getLogger(EmpJiabanAction.class);
	private int empJiabanId;
	// 查询参数
	private String queryType;

	// 加班、请假
	private String opType;

	public enum OPTYPE {
		OPTYPE_JIABAN((byte) 1), OPTYPE_QINGJIA((byte) 2);

		private byte b;

		OPTYPE(byte b) {
			this.b = b;
		}

		public byte getValue() {
			return this.b;
		}
	}

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
	public void setService(EmpJiabanRecordServiceI service) {
		this.service = service;
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

		hqlFilter.addFilter("QUERY_t#yyyymmdd_S_EQ", yyyymmdd);

		hqlFilter.addFilter("QUERY_t#status_B_NE",
				EmpJiabanRecord.STATUS_DELETED + "");
		hqlFilter.addFilter("QUERY_t#syuser#id_S_EQ", sessionInfo.getUser()
				.getId() + "");

		List<EmpJiabanRecord> list = ((EmpJiabanRecordServiceI) service)
				.findEmpJiabanRecordByFilter(hqlFilter);

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

			hqlFilter.addFilter("QUERY_t#yyyymmdd_S_EQ", yyyymmdd);

			hqlFilter.addFilter("QUERY_t#status_B_NE",
					EmpJiabanRecord.STATUS_DELETED + "");
			hqlFilter.addFilter("QUERY_t#syuser#id_S_EQ", sessionInfo.getUser()
					.getId() + "");

			List<EmpJiabanRecord> list = ((EmpJiabanRecordServiceI) service)
					.findEmpJiabanRecordByFilter(hqlFilter);

			if (list.size() > 0) {
				EmpJiabanRecord EmpJiabanRecord = list.get(0);

				if (EmpJiabanRecord != null) {
					writeJson(EmpJiabanRecord);
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
		if (!DataTypeUtil.isBlank(empJiabanId)) {
			writeJson(service.getById(empJiabanId));
		} else {
			Json j = new Json();
			j.setMsg("主键不可为空！");
			writeJson(j);
		}
	}

	/**
	 * 更新加班请假记录部门
	 */
	public void update() {
		Json json = new Json();

		if (!DataTypeUtil.isBlank(data.getId())) {
			EmpJiabanRecord empJiabanRecord = ((EmpJiabanRecordServiceI) service)
					.getById(data.getId());
			if (empJiabanRecord != null) {
				if (empJiabanRecord.getStatus() == EmpJiabanRecord.STATUS_SUBMIT) {
					json.setSuccess(false);
					json.setMsg("请求失败！已经提交过了。不能修改!!");
					writeJson(json);
					return;
				}
				if (empJiabanRecord.getStatus() == EmpJiabanRecord.STATUS_DELETED) {
					json.setSuccess(false);
					json.setMsg("请求失败！删除过的记录，不允许修改!!");
					writeJson(json);
					return;
				}
			} else {
				json.setSuccess(false);
				json.setMsg("请求失败！没有找到以前保存的数据! 请核实!!");
				writeJson(json);
				return;
			}

			data.setPhyAddr(IpUtil.getIpMacAddr(getRequest()));
			String yyyymmdd = DateUtil.getNowDate();
			data.setYyyymmdd(yyyymmdd);
			if (data.getType() == OPTYPE.OPTYPE_JIABAN.getValue()) {
				if (data.getStartTime() != null) {
					yyyymmdd = DateUtil.dateToString(data.getStartTime(),
							"yyyyMMdd");

					data.setYyyymmdd(yyyymmdd);
				}
			}
			((EmpJiabanRecordServiceI) service).updateEmpJiabanRecord(data);
			json.setSuccess(true);
			json.setMsg("修改成功！");
		}
		writeJson(json);
	}

	/**
	 * 角色grid
	 */
	public void gridAll() {
		Grid grid = new Grid();
		HqlFilter hqlFilter = new HqlFilter(getRequest());

		hqlFilter.addFilter("QUERY_t#status_B_NE",
				EmpJiabanRecord.STATUS_DELETED + "");
		// 增加玩家账号的限制。

		// 获得当天时间
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		// 过滤用户ID
		// hqlFilter.addFilter("QUERY_t#syuser#id_S_EQ",
		// sessionInfo.getUser().getId() + "");

		grid.setTotal(((EmpJiabanRecordServiceI) service)
				.countEmpJiabanRecordByFilter(hqlFilter));
		grid.setRows(((EmpJiabanRecordServiceI) service)
				.findEmpJiabanRecordByFilter(hqlFilter, page, rows));

		writeJson(grid);
	}

	/**
	 * 角色grid
	 */
	public void grid() {
		Grid grid = new Grid();
		HqlFilter hqlFilter = new HqlFilter(getRequest());

		hqlFilter.addFilter("QUERY_t#type_I_EQ", this.getOpType() + "");

		hqlFilter.addFilter("QUERY_t#status_B_NE",
				EmpJiabanRecord.STATUS_DELETED + "");
		// 增加玩家账号的限制。
		
		// 获得当天时间
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		// 过滤用户ID
		hqlFilter.addFilter("QUERY_t#syuser#id_S_EQ", sessionInfo.getUser()
				.getId() + "");
		try {
			grid.setTotal(((EmpJiabanRecordServiceI) service)
					.countEmpJiabanRecordByFilter(hqlFilter));
			grid.setRows(((EmpJiabanRecordServiceI) service)
					.findEmpJiabanRecordByFilter(hqlFilter, page, rows));
		} catch (Exception e) {
			e.printStackTrace();
		}
		writeJson(grid);
	}

	/**
	 * 获得加班请假记录表格下啦列表
	 */
	public void doNotNeedSessionAndSecurity_empJiabanIdComboGrid() {
		Grid grid = new Grid();
		HqlFilter hqlFilter = new HqlFilter(getRequest());

		hqlFilter.addFilter("QUERY_t#customerName_S_LK",
				"%%" + StringUtils.defaultString(q) + "%%");
		hqlFilter.addFilter("QUERY_t#customerEnName_S_LK",
				"%%" + StringUtils.defaultString(q) + "%%");
		hqlFilter.addFilter("QUERY_t#customerStatus_B_NE",
				EmpJiabanRecord.STATUS_DELETED + "");

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
	 * 保存一个加班请假记录
	 * 
	 * 加班的开始和结束时间必须同一天， 不支持跨时间段的加班申请
	 * 
	 * 
	 */
	public void save() {
		Json json = new Json();
		if (data != null) {
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
					ConfigUtil.getSessionInfoName());
			// 默认获得当天时间； 如果是加班的申请， 则用加班开始和结束的时间作为 时间戳。
			String yyyymmdd = DateUtil.getNowDate();
			data.setPhyAddr(IpUtil.getIpMacAddr(getRequest()));
			data.setYyyymmdd(yyyymmdd);
			if (data.getType() == OPTYPE.OPTYPE_JIABAN.getValue()) {
				if (data.getStartTime() != null) {
					yyyymmdd = DateUtil.dateToString(data.getStartTime(),
							"yyyyMMdd");

					data.setYyyymmdd(yyyymmdd);
				}
			}
			((EmpJiabanRecordServiceI) service).saveEmpJiabanRecord(data,
					sessionInfo.getUser().getId());

			json.setSuccess(true);
			json.setMsg("新增成功！");
		}
		writeJson(json);
	}

	/**
	 * 提交一个加班请假记录
	 */
	public void submit() {
		Json json = new Json();
		if (data != null) {
			EmpJiabanRecord empJiabanRecord = ((EmpJiabanRecordServiceI) service)
					.getById(data.getId());
			if (empJiabanRecord != null) {
				if (empJiabanRecord.getStatus() == EmpJiabanRecord.STATUS_SUBMIT) {
					json.setSuccess(false);
					json.setMsg("请求失败！已经提交过了。不需要再次提交，请耐心等待审批结束后!!");
					writeJson(json);
					return;
				}
				if (empJiabanRecord.getStatus() == EmpJiabanRecord.STATUS_DELETED) {
					json.setSuccess(false);
					json.setMsg("请求失败！删除过的记录，不允许再次提交!!");
					writeJson(json);
					return;
				}
			} else {
				json.setSuccess(false);
				json.setMsg("请求失败！没有找到以前保存的数据! 请核实!!");
				writeJson(json);
				return;
			}
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
					ConfigUtil.getSessionInfoName());
			// 获得当天时间
			String yyyymmdd = DateUtil.getNowDate();
			data.setPhyAddr(IpUtil.getIpMacAddr(getRequest()));
			data.setYyyymmdd(yyyymmdd);
			data.setOpUserId(sessionInfo.getUser().getId());
			data.setStatus(EmpJiabanRecord.STATUS_SUBMIT);

			EmpJiabanRecordDetail epJiabanRecordDetail = new EmpJiabanRecordDetail();
			epJiabanRecordDetail.setPhyAddr(IpUtil.getIpMacAddr(getRequest()));
			epJiabanRecordDetail.setReason("" + data.getReason());

			epJiabanRecordDetail.setNewStatus(EmpJiabanRecord.STATUS_SUBMIT);
			((EmpJiabanRecordServiceI) service).updateEmpJiabanRecord(data,
					epJiabanRecordDetail, sessionInfo.getUser().getId());
			json.setSuccess(true);
			json.setMsg("提交成功！");
		}
		writeJson(json);
	}

	/**
	 * 取消
	 */
	public void cancel() {
		Json json = new Json();
		if (data != null) {
			EmpJiabanRecord empJiabanRecord = ((EmpJiabanRecordServiceI) service)
					.getById(data.getId());
			if (empJiabanRecord != null) {
				if (empJiabanRecord.getStatus() == EmpJiabanRecord.STATUS_CANCEL) {
					json.setSuccess(false);
					json.setMsg("请求失败！已经取消过了。");
					writeJson(json);
					return;
				}

				if (empJiabanRecord.getStatus() == EmpJiabanRecord.STATUS_SUBMIT) {
					json.setSuccess(false);
					json.setMsg("请求失败！已经提交过了。");
					writeJson(json);
					return;
				}
				if (empJiabanRecord.getStatus() == EmpJiabanRecord.STATUS_DELETED) {
					json.setSuccess(false);
					json.setMsg("请求失败！删除过的记录，不允许再次提交!!");
					writeJson(json);
					return;
				}
			} else {
				json.setSuccess(false);
				json.setMsg("请求失败！没有找到以前保存的数据! 请核实!!");
				writeJson(json);
				return;
			}
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
					ConfigUtil.getSessionInfoName());
			// 获得当天时间
			String yyyymmdd = DateUtil.getNowDate();
			data.setPhyAddr(IpUtil.getIpMacAddr(getRequest()));
			data.setYyyymmdd(yyyymmdd);
			data.setOpUserId(sessionInfo.getUser().getId());
			data.setStatus(EmpJiabanRecord.STATUS_CANCEL);

			EmpJiabanRecordDetail epJiabanRecordDetail = new EmpJiabanRecordDetail();
			epJiabanRecordDetail.setPhyAddr(IpUtil.getIpMacAddr(getRequest()));
			epJiabanRecordDetail.setReason("" + data.getReason());

			epJiabanRecordDetail.setNewStatus(EmpJiabanRecord.STATUS_CANCEL);
			((EmpJiabanRecordServiceI) service).updateEmpJiabanRecord(data,
					epJiabanRecordDetail, sessionInfo.getUser().getId());
			json.setSuccess(true);
			json.setMsg("取消成功！");
		}
		writeJson(json);
	}

	/**
	 * 定性
	 */
	public void opresult() {
		Json json = new Json();
		if (data != null) {
			EmpJiabanRecord empJiabanRecord = ((EmpJiabanRecordServiceI) service)
					.getById(data.getId());
			if (empJiabanRecord != null) {
				if (empJiabanRecord.getStatus() != EmpJiabanRecord.STATUS_ACCESS) {
					json.setSuccess(false);
					json.setMsg("请求失败！审批通过了才能定性。");
					writeJson(json);
					return;
				}
				if (empJiabanRecord.getStatus() == EmpJiabanRecord.STATUS_DELETED) {
					json.setSuccess(false);
					json.setMsg("请求失败！删除过的记录，不允许再次提交!!");
					writeJson(json);
					return;
				}
			} else {
				json.setSuccess(false);
				json.setMsg("请求失败！没有找到以前保存的数据! 请核实!!");
				writeJson(json);
				return;
			}
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
					ConfigUtil.getSessionInfoName());
			// 获得当天时间
			String yyyymmdd = DateUtil.getNowDate();
			data.setPhyAddr(IpUtil.getIpMacAddr(getRequest()));
			data.setYyyymmdd(yyyymmdd);
			data.setOpUserId(sessionInfo.getUser().getId());
			data.setStatus(EmpJiabanRecord.STATUS_ACCESS);
			data.setRealTime(data.getRequiredTime());

			EmpJiabanRecordDetail epJiabanRecordDetail = new EmpJiabanRecordDetail();
			epJiabanRecordDetail.setPhyAddr(IpUtil.getIpMacAddr(getRequest()));
			epJiabanRecordDetail.setReason("" + data.getReason());
			if (data.getType() == OPTYPE.OPTYPE_JIABAN.getValue()) {
				if (data.getStartTime() != null) {
					yyyymmdd = DateUtil.dateToString(data.getStartTime(),
							"yyyyMMdd");

					data.setYyyymmdd(yyyymmdd);
				}
			}
			epJiabanRecordDetail.setNewStatus(EmpJiabanRecord.STATUS_ACCESS);
			((EmpJiabanRecordServiceI) service).updateEmpJiabanRecord(data,
					epJiabanRecordDetail, sessionInfo.getUser().getId());
			json.setSuccess(true);
			json.setMsg("操作成功！");
		}
		writeJson(json);
	}

	/**
	 * 通过
	 */
	public void checked() {
		Json json = new Json();
		if (data != null) {
			EmpJiabanRecord empJiabanRecord = ((EmpJiabanRecordServiceI) service)
					.getById(data.getId());
			if (empJiabanRecord != null) {
				if (empJiabanRecord.getStatus() == EmpJiabanRecord.STATUS_ACCESS) {
					json.setSuccess(false);
					json.setMsg("请求失败！已经审批通过了。");
					writeJson(json);
					return;
				}
				if (empJiabanRecord.getStatus() != EmpJiabanRecord.STATUS_SUBMIT) {
					json.setSuccess(false);
					json.setMsg("请求失败！还没有提交。");
					writeJson(json);
					return;
				}
				if (empJiabanRecord.getStatus() == EmpJiabanRecord.STATUS_DELETED) {
					json.setSuccess(false);
					json.setMsg("请求失败！删除过的记录，不允许再次提交!!");
					writeJson(json);
					return;
				}
			} else {
				json.setSuccess(false);
				json.setMsg("请求失败！没有找到以前保存的数据! 请核实!!");
				writeJson(json);
				return;
			}
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
					ConfigUtil.getSessionInfoName());
			// 获得当天时间
			String yyyymmdd = DateUtil.getNowDate();
			data.setPhyAddr(IpUtil.getIpMacAddr(getRequest()));
			data.setYyyymmdd(yyyymmdd);
			data.setOpUserId(sessionInfo.getUser().getId());
			data.setStatus(EmpJiabanRecord.STATUS_ACCESS);
			data.setRealTime(data.getRequiredTime());

			EmpJiabanRecordDetail epJiabanRecordDetail = new EmpJiabanRecordDetail();
			epJiabanRecordDetail.setPhyAddr(IpUtil.getIpMacAddr(getRequest()));
			epJiabanRecordDetail.setReason("" + data.getReason());
			if (data.getType() == OPTYPE.OPTYPE_JIABAN.getValue()) {
				if (data.getStartTime() != null) {
					yyyymmdd = DateUtil.dateToString(data.getStartTime(),
							"yyyyMMdd");

					data.setYyyymmdd(yyyymmdd);
				}
			}
			epJiabanRecordDetail.setNewStatus(EmpJiabanRecord.STATUS_ACCESS);
			((EmpJiabanRecordServiceI) service).updateEmpJiabanRecord(data,
					epJiabanRecordDetail, sessionInfo.getUser().getId());
			json.setSuccess(true);
			json.setMsg("操作成功！");
		}
		writeJson(json);
	}

	/**
	 * 退回
	 */
	public void back() {
		Json json = new Json();
		if (data != null) {
			EmpJiabanRecord empJiabanRecord = ((EmpJiabanRecordServiceI) service)
					.getById(data.getId());
			if (empJiabanRecord != null) {
				if (empJiabanRecord.getStatus() == EmpJiabanRecord.STATUS_ACCESS) {
					json.setSuccess(false);
					json.setMsg("请求失败！已经审批通过了。不能再次退回!!");
					writeJson(json);
					return;
				}
				if (empJiabanRecord.getStatus() == EmpJiabanRecord.STATUS_BACKEND) {
					json.setSuccess(false);
					json.setMsg("请求失败！已经退回过了。");
					writeJson(json);
					return;
				}
				if (empJiabanRecord.getStatus() == EmpJiabanRecord.STATUS_DELETED) {
					json.setSuccess(false);
					json.setMsg("请求失败！删除过的记录，不允许再次退回!!");
					writeJson(json);
					return;
				}
				if (empJiabanRecord.getStatus() != EmpJiabanRecord.STATUS_SUBMIT) {
					json.setSuccess(false);
					json.setMsg("请求失败！不是等待审核状态，不允许退回!!");
					writeJson(json);
					return;
				}
			} else {
				json.setSuccess(false);
				json.setMsg("请求失败！没有找到以前保存的数据! 请核实!!");
				writeJson(json);
				return;
			}
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
					ConfigUtil.getSessionInfoName());
			// 获得当天时间
			String yyyymmdd = DateUtil.getNowDate();
			data.setPhyAddr(IpUtil.getIpMacAddr(getRequest()));
			data.setYyyymmdd(yyyymmdd);
			data.setOpUserId(sessionInfo.getUser().getId());
			data.setStatus(EmpJiabanRecord.STATUS_BACKEND);

			EmpJiabanRecordDetail epJiabanRecordDetail = new EmpJiabanRecordDetail();
			epJiabanRecordDetail.setPhyAddr(IpUtil.getIpMacAddr(getRequest()));
			epJiabanRecordDetail.setReason("" + data.getReason());

			epJiabanRecordDetail.setNewStatus(EmpJiabanRecord.STATUS_BACKEND);
			epJiabanRecordDetail.setSyuser(data.getSyuser());
			((EmpJiabanRecordServiceI) service).updateEmpJiabanRecord(data,
					epJiabanRecordDetail, sessionInfo.getUser().getId());
			json.setSuccess(true);
			json.setMsg("退回成功！");
		}
		writeJson(json);
	}

	/**
	 * 删除一个对象
	 */
	public void delete() {
		Json json = new Json();
		if (!DataTypeUtil.isBlank(empJiabanId)) {
			EmpJiabanRecord t = service.getById(empJiabanId);
			t.setStatus(EmpJiabanRecord.STATUS_DELETED);
			((EmpJiabanRecordServiceI) service).updateEmpJiabanRecord(t);
			json.setSuccess(true);
			json.setMsg("删除成功！");
		}
		writeJson(json);
	}

	/**
	 * @return the empJiabanId
	 */
	public int getEmpJiabanId() {
		return empJiabanId;
	}

	/**
	 * @param empJiabanId
	 *            the empJiabanId to set
	 */
	public void setEmpJiabanId(int empJiabanId) {
		this.empJiabanId = empJiabanId;
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
	 * @return the opType
	 */
	public String getOpType() {
		return opType;
	}

	/**
	 * @param opType
	 *            the opType to set
	 */
	public void setOpType(String opType) {
		this.opType = opType;
	}

}
