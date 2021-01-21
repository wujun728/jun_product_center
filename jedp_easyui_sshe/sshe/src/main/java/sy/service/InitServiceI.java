package sy.service;

/**
 * 初始化
 * 
 * @author Wujun
 * 
 */
public interface InitServiceI {

	/**
	 * 初始化数据库
	 */
	public void initDb();
	
	public void initResourceDb();
		
	public void initDbData();
	
	/**
	 * 执行prefix 开头的ID 
	 * @param idPrefix
	 */
	void initResourceDb(String idPrefix);

	void initResourceType();

}
