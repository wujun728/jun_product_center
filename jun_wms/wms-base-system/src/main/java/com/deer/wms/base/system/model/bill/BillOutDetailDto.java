package com.deer.wms.base.system.model.bill;


/**
 * 出库单详情表 bill_in_detail
 * 
 * @author cai
 * @date 2019-07-18
 */
public class BillOutDetailDto extends  BillOutDetail
{


	private String itemName;

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
}
