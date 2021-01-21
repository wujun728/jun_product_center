package sy.util.base;

/**
 * 数据类型校验 工具类
 * 
 */
public class DataTypeUtil {

	public static boolean equals(int cs1, int cs2) {
		return cs1 == cs2;
	}

	public static boolean isBlank(int cs) {
		if (cs <= 0) {
			return true;
		}

		return false;
	}
}
