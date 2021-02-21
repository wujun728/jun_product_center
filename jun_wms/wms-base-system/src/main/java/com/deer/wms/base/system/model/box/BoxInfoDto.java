package com.deer.wms.base.system.model.box;

/**
 *
 * 
 * @author markeloff
 * @date 2019-7-2
 */
public class BoxInfoDto extends BoxInfo
{



	/**  行(层) */
	private Integer sRow;
	/**  列 */
	private Integer sColumn;
	/**  货架名 */
	private String shelfName;

	private String location;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getShelfName() {
		return shelfName;
	}

	public void setShelfName(String shelfName) {
		this.shelfName = shelfName;
	}

	public Integer getsRow() {
		return sRow;
	}

	public void setsRow(Integer sRow) {
		this.sRow = sRow;
	}

	public Integer getsColumn() {
		return sColumn;
	}

	public void setsColumn(Integer sColumn) {
		this.sColumn = sColumn;
	}
}
