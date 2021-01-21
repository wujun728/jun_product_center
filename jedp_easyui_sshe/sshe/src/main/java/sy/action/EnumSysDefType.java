package sy.action;

/**
 * 系统定义的字典类型的定义
 * 
 * @author Wujun
 * 
 */
@Deprecated
public enum EnumSysDefType {

	USER_CERTIFICAT_TYPE(10100100, "USER_CERTIFICAT_TYPE", "用户的证件类型"),

	ATTR_KESHI(10101100, "ATTR_KESHI", "所属科室"), ATTR_CHECK(10102100,
			"ATTR_CHECK", "属性检查类型");

	// 注意这里有个分号
	private String name;
	// 描述
	private String desc;
	// 类型
	private int type;

	private EnumSysDefType(int type, String name, String desc) {
		this.name = name;
		this.type = type;
		this.desc = desc;
	}

	public int getType() {
		return this.type;
	}

	/**
	 * 获得中文的类型描述
	 * 
	 * @return
	 */
	public String getDesc() {
		return this.desc;
	}

	public static String getName(int type) {
		for (EnumSysDefType enumSysDefType : EnumSysDefType.values()) {
			if (enumSysDefType.type == type)
				return enumSysDefType.name;
		}
		return null;
	}

	public String toString() { // 覆写toString()方法
		return this.type + ":" + this.name;
	}

	/**
	 * 获得简称
	 * 
	 * @return
	 */
	public String getName() {
		return this.name;
	}
}
