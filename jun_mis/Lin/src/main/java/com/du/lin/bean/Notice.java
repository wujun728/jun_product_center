package com.du.lin.bean;



import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author lin
 * @since 2017-11-23
 */
public class Notice extends Model<Notice> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private String title;
	private String body;
	private String message;
	private Integer senduserid;
	private String type;
	private Date createtime;
	private Integer recivedeptid;
	private Integer reciveuserid;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getSenduserid() {
		return senduserid;
	}

	public void setSenduserid(Integer senduserid) {
		this.senduserid = senduserid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Integer getRecivedeptid() {
		return recivedeptid;
	}

	public void setRecivedeptid(Integer recivedeptid) {
		this.recivedeptid = recivedeptid;
	}

	public Integer getReciveuserid() {
		return reciveuserid;
	}

	public void setReciveuserid(Integer reciveuserid) {
		this.reciveuserid = reciveuserid;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Notice{" +
			", id=" + id +
			", title=" + title +
			", body=" + body +
			", message=" + message +
			", senduserid=" + senduserid +
			", type=" + type +
			", createtime=" + createtime +
			", recivedeptid=" + recivedeptid +
			", reciveuserid=" + reciveuserid +
			"}";
	}
}
