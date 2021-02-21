package com.deer.wms.base.system.model.task;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 任务表 task_info
 * 
 * @author guo
 * @date 2019-06-03
 */
public class WCSTaskInfo
{

	
	/** ID */
	private Integer id;
	/** GUID */
	private String taskId;
	/** 起始位置 */
	private String startPosition;
	/** 结束位置 */
	private String endPosition;
	/** 类型 */
	private Integer type;
	/** 状态 */
	private Integer state;
	/** 点数数量 */
	private Integer quantity;
	/** 完成数量 */
	private Integer completeQuantity;
	/** 条码信息（贴标使用） */
	private String barCode;
	/** 托盘/料箱编码 */
	private String boxNo;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setTaskId(String taskId) 
	{
		this.taskId = taskId;
	}

	public String getTaskId() 
	{
		return taskId;
	}
	public void setStartPosition(String startPosition) 
	{
		this.startPosition = startPosition;
	}

	public String getStartPosition() 
	{
		return startPosition;
	}
	public void setEndPosition(String endPosition) 
	{
		this.endPosition = endPosition;
	}

	public String getEndPosition() 
	{
		return endPosition;
	}
	public void setType(Integer type) 
	{
		this.type = type;
	}

	public Integer getType() 
	{
		return type;
	}
	public void setState(Integer state) 
	{
		this.state = state;
	}

	public Integer getState() 
	{
		return state;
	}
	public void setQuantity(Integer quantity) 
	{
		this.quantity = quantity;
	}

	public Integer getQuantity() 
	{
		return quantity;
	}
	public void setCompleteQuantity(Integer completeQuantity) 
	{
		this.completeQuantity = completeQuantity;
	}

	public Integer getCompleteQuantity() 
	{
		return completeQuantity;
	}
	public void setBarCode(String barCode) 
	{
		this.barCode = barCode;
	}

	public String getBarCode() 
	{
		return barCode;
	}
	public void setBoxNo(String boxNo) 
	{
		this.boxNo = boxNo;
	}

	public String getBoxNo() 
	{
		return boxNo;
	}

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("taskId", getTaskId())
            .append("startPosition", getStartPosition())
            .append("endPosition", getEndPosition())
            .append("type", getType())
            .append("state", getState())
            .append("quantity", getQuantity())
            .append("completeQuantity", getCompleteQuantity())
            .append("barCode", getBarCode())
            .append("boxNo", getBoxNo())
            .toString();
    }
}
