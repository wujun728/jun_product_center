package sy.converter.base;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

public class BigDecimalConverter extends StrutsTypeConverter {
	
	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		BigDecimal bd = null;
		if (BigDecimal.class == toClass) {
			String bdStr = values[0];
			if (bdStr != null && !"".equals(bdStr)) {
				bd = new BigDecimal(bdStr);
			} else {
				// bd = BigDecimal.valueOf(-1);
			}
			return bd;
		}
		return BigDecimal.ZERO;
	}

	@Override
	public String convertToString(Map context, Object o) {

		if (o instanceof BigDecimal) {
			BigDecimal b = new BigDecimal(o.toString()).setScale(2,
					BigDecimal.ROUND_HALF_DOWN);
			return b.toString();
		}
		return o.toString();
	}
	public BigDecimal convertFromObject(Object values, Class toClass) {
		BigDecimal price = null;
		if (values instanceof String) {
			price = convertFromString((String) values, BigDecimal.class);
		} else if (values instanceof Integer) {
			price = convertFromString(values + "", BigDecimal.class);
		}

		return price;
	}

	public BigDecimal convertFromString(String values, Class toClass) {
		// 构造以字符串内容为值的BigDecimal类型的变量bd
		BigDecimal bd = new BigDecimal(values);
		// 设置小数位数，第一个变量是小数位数，第二个变量是取舍方法(四舍五入)
		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
		// 转化为字符串输出
		// String OutString=bd.toString();

		return bd;
	}
}
