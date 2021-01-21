package sy.model.easyui;

import java.util.List;

/**
 * EasyUI DataGrid模型
 * 
 * @author Wujun
 * 
 */
public class TreeGrid<T> implements java.io.Serializable {

	private Integer total;
	private List<T> rows;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	/**
	 * @return the rows
	 */
	public List<T> getRows() {
		return rows;
	}
	
	/**
	 * @param rows
	 *            the rows to set
	 */
	public void setRows(List<T> rows) {
		this.rows = rows;
	}

}
