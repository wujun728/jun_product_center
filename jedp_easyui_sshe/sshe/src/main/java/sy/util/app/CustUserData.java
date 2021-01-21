package sy.util.app;

import java.util.List;

public class CustUserData {
	// 行
	private int index;
	// 一行的列数据。按顺序
	private List<ColData> cellList;

	public CustUserData(int index, List<ColData> colList) {
		this.index = index;
		this.cellList = colList;
	}

	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @param index
	 *            the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * @return the cellList
	 */
	public List<ColData> getCellList() {
		return cellList;
	}

	/**
	 * @param cellList
	 *            the cellList to set
	 */
	public void setCellList(List<ColData> cellList) {
		this.cellList = cellList;
	}

}
