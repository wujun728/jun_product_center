package com.deer.wms.base.system.model.ware;

/**
 * 条件查询货位表 cell_info相关
 * 
 * @author deer
 * @date 2019-07-04				markeloff
 */
public class CellInfoDto extends CellInfo
{

	/**货架名 */
	private String shelfName;

	/**物料名 */
	private String itemName;

	/** 物料编码*/
	private String itemCode;

	/** 批次*/
	private String batch;

	private String boxCode;



	public String getBoxCode() {
		return boxCode;
	}

	public void setBoxCode(String boxCode) {
		this.boxCode = boxCode;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getShelfName() {
		return shelfName;
	}

	public void setShelfName(String shelfName) {
		this.shelfName = shelfName;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
}
