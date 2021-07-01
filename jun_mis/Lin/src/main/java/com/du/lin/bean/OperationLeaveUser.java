package com.du.lin.bean;



import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author lin
 * @since 2017-11-23
 */
@TableName("operation_leave_user")
public class OperationLeaveUser extends Model<OperationLeaveUser> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private Integer leaveid;
	private Integer userid;
	private String username;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLeaveid() {
		return leaveid;
	}

	public void setLeaveid(Integer leaveid) {
		this.leaveid = leaveid;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "OperationLeaveUser{" +
			", id=" + id +
			", leaveid=" + leaveid +
			", userid=" + userid +
			", username=" + username +
			"}";
	}
}
