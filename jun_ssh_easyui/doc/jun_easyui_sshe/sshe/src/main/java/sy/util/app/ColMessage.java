package sy.util.app;

public class ColMessage {
	private int row;
	private int col;
	private String hint;

	public ColMessage(int row, int col, String colMessage) {
		this.row = row;
		this.col = col;
		this.hint = colMessage;
	}

	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * @param row
	 *            the row to set
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * @return the col
	 */
	public int getCol() {
		return col;
	}

	/**
	 * @param col
	 *            the col to set
	 */
	public void setCol(int col) {
		this.col = col;
	}

	/**
	 * @return the hint
	 */
	public String getHint() {
		return hint;
	}

	/**
	 * @param hint
	 *            the hint to set
	 */
	public void setHint(String hint) {
		this.hint = hint;
	}
}
