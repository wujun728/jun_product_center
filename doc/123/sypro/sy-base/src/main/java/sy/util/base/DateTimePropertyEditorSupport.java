package sy.util.base;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

/**
 * 与spring mvc的@InitBinder结合
 * 
 * 转换前台的字符串格式的日期值给后台使用
 * 
 * http://git.oschina.net/sphsyv/sypro
 * 
 * @author 孙宇
 * 
 */
public class DateTimePropertyEditorSupport extends PropertyEditorSupport {

	private static final DateFormat DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public DateTimePropertyEditorSupport() {
	}

	@Override
	public String getAsText() {
		Date value = (Date) getValue();
		return (value != null ? DATEFORMAT.format(value) : "");
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text == null || StringUtils.isBlank(text)) {
			setValue(null);
		} else {
			setValue(new Timestamp(DateUtil.stringToDate(StringUtils.trim(text)).getTime()));
		}
	}

}
