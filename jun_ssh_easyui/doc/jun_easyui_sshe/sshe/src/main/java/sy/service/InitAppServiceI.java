package sy.service;

/**
 * 初始化
 * 
 * @author Wujun
 * 
 */
public interface InitAppServiceI {

	/**
	 * 初始化数据库
	 */
	public void initDb();

	public void execSQL(String sql);

	void execDbData();
}
