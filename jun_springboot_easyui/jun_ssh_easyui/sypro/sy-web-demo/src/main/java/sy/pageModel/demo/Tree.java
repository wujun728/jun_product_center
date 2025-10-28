package sy.pageModel.demo;

/**
 * zTree使用的模型
 * 
 * @author 孙宇
 *
 */
public class Tree {

	private String id;
	private String pid;
	private String name;
	private Boolean open;

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
