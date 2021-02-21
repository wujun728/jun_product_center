package com.deer.wms.base.system.model.ware;

/**
 * 出入库口表 door
 * 
 * @author deer
 * @date 2019-05-12
 */
public class DoorDto extends Door
{
	 private String wareName;

	public String getWareName() {
		return wareName;
	}

	public void setWareName(String wareName) {
		this.wareName = wareName;
	}
}
