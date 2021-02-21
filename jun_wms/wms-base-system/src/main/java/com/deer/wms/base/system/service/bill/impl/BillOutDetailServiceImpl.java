package com.deer.wms.base.system.service.bill.impl;

import com.deer.wms.base.system.model.Carrier;
import com.deer.wms.base.system.model.bill.*;
import com.deer.wms.base.system.model.box.BoxItem;
import com.deer.wms.base.system.model.box.BoxItemDto;
import com.deer.wms.base.system.model.task.PickTask;
import com.deer.wms.base.system.model.ware.CellInfo;
import com.deer.wms.base.system.model.ware.Door;
import com.deer.wms.base.system.service.CarrierService;
import com.deer.wms.base.system.service.MESWebService.BillOutWorkerOrder;
import com.deer.wms.base.system.service.MESWebService.WebserviceResponse;
import com.deer.wms.base.system.service.bill.IBillOutMasterService;
import com.deer.wms.base.system.service.box.IBoxItemService;
import com.deer.wms.base.system.service.task.PickTaskService;
import com.deer.wms.base.system.service.ware.ICellInfoService;
import com.deer.wms.base.system.service.ware.IDoorService;
import com.deer.wms.base.system.service.webSocket.WebSocketServer;
import com.deer.wms.base.system.web.task.TaskInfoController;
import com.deer.wms.common.core.service.AbstractService;
import com.deer.wms.base.system.dao.bill.BillOutDetailMapper;
import com.deer.wms.base.system.service.bill.IBillOutDetailService;
import com.deer.wms.common.utils.DateUtils;
import org.apache.ibatis.annotations.Param;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;


/**
 * 入库单 服务层实现
 * 
 * @author cai
 * @date 2019-07-17
 */
@Service
public class BillOutDetailServiceImpl extends AbstractService<BillOutDetail, Integer> implements IBillOutDetailService {

	@Autowired
	private BillOutDetailMapper billOutDetailMapper;

	@Autowired
	private IBoxItemService boxItemService;
	@Autowired
	private IBillOutMasterService billOutMasterService;
	@Autowired
	private IDoorService doorService;
	@Autowired
	private CarrierService carrierService;
	@Autowired
	private ICellInfoService cellInfoService;
	@Autowired
	private PickTaskService pickTaskService;

	/**
	 * 根据BillOutDetailId删除BillOutDetail
	 *
	 */
	@Override
	public void deleteBillOutDetailByBillOutDetailId(Integer billOutDetailId) {

		billOutDetailMapper.deleteBillOutDetailByBillOutDetailId(billOutDetailId);
	}

	/**
	 *  根据billId查询BillOutDetail信息
	 *
	 * @param billId
	 * @return
	 */
	@Override
	public List<BillOutDetailDto> findListByBillId(Integer billId) {

		return billOutDetailMapper.findListByBillId(billId);
	}

	/**
	 * 保存BillOutDetail  出库单详情
	 *
	 * @param billOutDetail
	 */
	@Override
	public void saveBillOutDetail(BillOutDetail billOutDetail) {

		billOutDetailMapper.saveBillOutDetail(billOutDetail);
	}
	@Override
	public List<BillOutDetail> findList(BillOutDetailCriteria criteria){
		return billOutDetailMapper.findList(criteria);
	}

	@Override
	public void save(BillOutDetail billOutDetail){
		super.save(billOutDetail);
	}
	/**
	 * MES下发计划工单
	 */
	@Override
	@Transactional
	public synchronized WebserviceResponse downWipToStock(@Param("input") String input){
		WebserviceResponse webServiceResponse = null;
		String taskCode = null;
		try {
			if(input.length() > 0) {
				BillOutWorkerOrder billOutWorkerOrder = splitDownWipToStockXmlCode(input);
				taskCode = billOutWorkerOrder.getTaskCode();
				Integer outQuantitys = billOutWorkerOrder.getQuantity();
				List<BillOutMasterDto> billOutMasterDtos = billOutMasterService.findList(new BillOutMasterCriteria(billOutWorkerOrder.getWipEntity()));
				if(billOutMasterDtos.size()<=0) {
					List<BoxItemDto> boxItemDtos = boxItemService.findSuitByItemCodeAndQuantity(billOutWorkerOrder.getItemCode(), billOutWorkerOrder.getQuantity());
					if (boxItemDtos.size() > 0) {
						String nowStr = DateUtils.getTime();
						BillOutMaster billOutMaster = new BillOutMaster();
						billOutMaster.setBillId(null);
						billOutMaster.setBillNo(billOutWorkerOrder.getWipEntity());
						billOutMaster.setCreateTime(nowStr);
						billOutMaster.setState(0);
						billOutMaster.setMemo("MES工单");
						billOutMaster.setWareId(212);
						billOutMaster.setType(1);
						billOutMasterService.save(billOutMaster);
						BillOutDetail billOutDetail = new BillOutDetail();
						billOutDetail.setBillOutDetailId(null);
						billOutDetail.setBillId(billOutMaster.getBillId());
						billOutDetail.setItemCode(billOutWorkerOrder.getItemCode());
						billOutDetail.setQuantity(billOutWorkerOrder.getQuantity());
						billOutDetail.setTaskId(billOutWorkerOrder.getTaskCode());
						billOutDetail.setFinishedCode(billOutWorkerOrder.getFinishedCode());
						billOutDetail.setPriority(billOutWorkerOrder.getPriority());
						billOutDetail.setItemCode(billOutWorkerOrder.getItemCode());
						save(billOutDetail);
						for (BoxItemDto boxItemDto : boxItemDtos) {
							boxItemDto.setWorkOrderStockState(1);
							Integer canOutInventoryQuantity = boxItemDto.getQuantity() - boxItemDto.getForecastStockQuantity();
							PickTask pickTask = new PickTask(boxItemDto.getBoxCode(), 0, billOutDetail.getBillOutDetailId(), 1, boxItemDto.getBatch(), boxItemDto.getSubInventoryId(),DateUtils.getTime());
							if (outQuantitys <= canOutInventoryQuantity) {
								boxItemDto.setForecastStockQuantity(outQuantitys + boxItemDto.getForecastStockQuantity());
								pickTask.setPickQuantity(outQuantitys);
							} else {
								boxItemDto.setForecastStockQuantity(boxItemDto.getQuantity());
								pickTask.setPickQuantity(canOutInventoryQuantity);
								outQuantitys -= canOutInventoryQuantity;
							}
							pickTaskService.save(pickTask);
							boxItemService.update(boxItemDto);
//						CellInfo cellInfo = cellInfoService.selectCellInfoById(boxItemDto.getBoxCellId());
//						cellInfo.setState(2);
//						cellInfoService.update(cellInfo);
						}
						webServiceResponse = new WebserviceResponse(taskCode, "0", "OK", null);
					} else {
						webServiceResponse = new WebserviceResponse(taskCode, "-1", "库存不足", null);
					}
				}
				else{
					webServiceResponse = new WebserviceResponse(taskCode, "-1", "此工单一下发给WMS", null);

//					WebSocketServer.sendObject();
				}
			}else{
				webServiceResponse = new WebserviceResponse(taskCode, "-1", "缺少信息", null);
			}
		}catch(Exception e){
			e.printStackTrace();
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw, true));
			String error = sw.toString();
			webServiceResponse = new WebserviceResponse(taskCode,"-1",error,null);
		}
		return webServiceResponse;
	}

	/**
	 * 空载具到达出库输送线的入库口
	 */
	@Override
	public WebserviceResponse emptyShelfArrive(String input) {
		WebserviceResponse webServiceResponse = null;
		try {
			if(input.length() > 0) {
				BillOutWorkerOrder billOutWorkerOrder = splitEmptyShelfArriveXmlCode(input);
				String taskCode = billOutWorkerOrder.getTaskCode();
				if (taskCode != null && taskCode != "" && billOutWorkerOrder.getShelfCode() != null && billOutWorkerOrder.getShelfCode() != "") {
					Carrier carrier = new Carrier(billOutWorkerOrder.getShelfCode().replace("ID",""), 1, billOutWorkerOrder.getTaskCode(), DateUtils.getTime());
					carrierService.save(carrier);
					webServiceResponse = new WebserviceResponse(taskCode, "0", "OK", null);
				} else {
					webServiceResponse = new WebserviceResponse(taskCode, "-1", "缺少信息", null);
				}
			}else{
				webServiceResponse = new WebserviceResponse("", "-1", "缺少信息", null);
			}
		}catch(Exception e){
			e.printStackTrace();
			webServiceResponse = new WebserviceResponse(null,"-1",e.getMessage(),null);
		}
		return webServiceResponse;
	}

	/**
	 * 解析MES下发工单字符串
	 * @param xmlCode
	 * @return
	 * @throws Exception
	 */
	private BillOutWorkerOrder splitDownWipToStockXmlCode(String xmlCode)throws Exception{
		BillOutWorkerOrder billOutWorkerOrder = new BillOutWorkerOrder();
		try {
			Document doc = DocumentHelper.parseText(xmlCode);
			Element root = doc.getRootElement();
			List<Element> elements = root.elements();
			billOutWorkerOrder.setMacCode(root.attributeValue("macCode"));
			billOutWorkerOrder.setTaskCode(root.attributeValue("taskCode"));
			billOutWorkerOrder.setWipEntity(root.attributeValue("wipEntity"));
			Door door = doorService.selectDoorById(5);
			String code = door.getCode();
			for(Element element : elements){
				String tagCode = element.attributeValue("tagCode");
				String tagValue = element.attributeValue("tagValue");
				if(tagCode.equals(code+"_1000")){
					billOutWorkerOrder.setFinishedCode(tagValue);
				}else if(tagCode.equals(code+"_1001")){
					billOutWorkerOrder.setQuantity(Integer.parseInt(tagValue));
				}else if(tagCode.equals(code+"_1002")){
					billOutWorkerOrder.setPriority(tagValue);
				}else if(tagCode.equals(code+"_1003")){
					billOutWorkerOrder.setItemCode(tagValue);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return billOutWorkerOrder;
	}

	/**
	 * 解析Mes空载具到达立体库入料口xml字符串
	 * @param xmlCode
	 * @return
	 * @throws Exception
	 */
	private BillOutWorkerOrder splitEmptyShelfArriveXmlCode(String xmlCode)throws Exception{
		BillOutWorkerOrder billOutWorkerOrder = new BillOutWorkerOrder();
		try {
			Document doc = DocumentHelper.parseText(xmlCode);
			Element root = doc.getRootElement();
			billOutWorkerOrder.setMacCode(root.attributeValue("macCode"));
			billOutWorkerOrder.setTaskCode(root.attributeValue("taskCode"));
			billOutWorkerOrder.setShelfCode(root.attributeValue("shelfCode"));
		}catch(Exception e){
			e.printStackTrace();
		}
		return billOutWorkerOrder;
	}
	public static void main(String args[]){

	}
}


