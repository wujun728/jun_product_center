package sy.pageModel.base;

/**
 * 封装json结果集
 * 
 * http://git.oschina.net/sphsyv/sypro
 * 
 * @author 孙宇
 *
 */
public class JsonResult {

	private Boolean success = false;// 返回是否成功
	private String msg = "";// 返回信息
	private Object obj = null;// 返回其他对象信息

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

}
