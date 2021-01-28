package sy.util.app;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import sy.util.app.*;

public class ReadExceRowFilter {

	/**
	 * false 没有倒入错误
	 */
	private boolean hasErrorCell;

	private List<ColMessage> colMessageList;

	public ReadExceRowFilter(boolean b) {
		this.hasErrorCell = false;
	}

	public Object handleRow(Row row, ReadCellCallBack readCellCallBack) {
		readCellCallBack.init();
		for (final Cell cell : row) {
			readCellCallBack.handleCell(readCellCallBack.getRow(), cell);
		}
		return readCellCallBack.returnRowObject(row);
	}

	/**
	 * @return the hasErrorCell
	 */
	public boolean isHasErrorCell() {
		return hasErrorCell;
	}

	/**
	 * @param hasErrorCell
	 *            the hasErrorCell to set
	 */
	public void setHasErrorCell(boolean hasErrorCell) {
		this.hasErrorCell = hasErrorCell;
	}

	/**
	 * @return the colMessageList
	 */
	public List<ColMessage> getColMessageList() {
		return colMessageList;
	}

	/**
	 * @param colMessageList
	 *            the colMessageList to set
	 */
	public void setColMessageList(List<ColMessage> colMessageList) {
		this.colMessageList = colMessageList;
	}

	public void addColMessage(int row, int col, String ColMessage) {
		this.colMessageList.add(new ColMessage(row, col, ColMessage));
	}

}
