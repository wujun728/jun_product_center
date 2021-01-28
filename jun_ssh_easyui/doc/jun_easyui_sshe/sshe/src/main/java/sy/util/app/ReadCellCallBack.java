package sy.util.app;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public interface ReadCellCallBack {

	public Row getRow();

	public Object handleCell(Row row, Cell cell);

	Object returnRowObject(Row row);
	
	void init();

	public List<ColData> getCellList(int index);

}
