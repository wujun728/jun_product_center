package com.du.lin.bean;


public class OperationLeave {
    private Integer id;

    private String type;

    private String starttime;

    private String endtime;

    private String reason;

    private String isfinish;

    private String createtime;

    private String username;
    
    private String operationUsername;
    
    
    public OperationLeave() {
    	super();
    }

    
	
	

	public String getUsername() {
		return username;
	}





	public void setUsername(String username) {
		this.username = username;
	}





	public String getOperationUsername() {
		return operationUsername;
	}





	public void setOperationUsername(String operationUsername) {
		this.operationUsername = operationUsername;
	}





	public OperationLeave(Integer id, String type, String starttime, String endtime, String reason, String isfinish,
			String createtime, String username, String operationUsername) {
		super();
		this.id = id;
		this.type = type;
		this.starttime = starttime;
		this.endtime = endtime;
		this.reason = reason;
		this.isfinish = isfinish;
		this.createtime = createtime;
		this.username = username;
		this.operationUsername = operationUsername;
	}





	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getIsfinish() {
		return isfinish;
	}

	public void setIsfinish(String isfinish) {
		this.isfinish = isfinish;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
    
    
    
}
