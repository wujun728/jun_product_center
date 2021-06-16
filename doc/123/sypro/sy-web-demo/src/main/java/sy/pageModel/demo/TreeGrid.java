package sy.pageModel.demo;

/**
 * jqGrid treeGrid使用的模型
 * 
 * @author 孙宇
 *
 */
public class TreeGrid {

	private Long id;
	private String name;

	private Long parent = null;// mixed 表示父节点是哪个，如果当前节点为父节点，则值为null，不是父节点则为其父节点ID
	private int level = 0;// number 表示此数据在哪一级
	private Boolean isLeaf = true;// boolean 表示此数据是否为叶子节点
	private Boolean expanded = true;// boolean 表示此节点是否展开
	private Boolean loaded = true;// boolean 表示是否加载完成，设置为True表示加载完成，不需要在加载

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Long getParent() {
		return parent;
	}

	public void setParent(Long parent) {
		this.parent = parent;
	}

	public Boolean getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public Boolean getExpanded() {
		return expanded;
	}

	public void setExpanded(Boolean expanded) {
		this.expanded = expanded;
	}

	public Boolean getLoaded() {
		return loaded;
	}

	public void setLoaded(Boolean loaded) {
		this.loaded = loaded;
	}

}
