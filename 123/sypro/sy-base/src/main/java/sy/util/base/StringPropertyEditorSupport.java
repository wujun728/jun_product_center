package sy.util.base;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.util.HtmlUtils;
import org.springframework.web.util.JavaScriptUtils;

/**
 * 与spring mvc的@InitBinder结合
 * 
 * 用于防止XSS攻击，并且带去左右空格功能
 * 
 * http://git.oschina.net/sphsyv/sypro
 * 
 * @author 孙宇
 * 
 */
public class StringPropertyEditorSupport extends PropertyEditorSupport {

	private boolean escapeHTML;// 编码HTML

	private boolean escapeJavaScript;// 编码javascript

	public StringPropertyEditorSupport() {
		super();
	}

	public StringPropertyEditorSupport(boolean escapeHTML, boolean escapeJavaScript) {
		super();
		this.escapeHTML = escapeHTML;
		this.escapeJavaScript = escapeJavaScript;
	}

	@Override
	public String getAsText() {
		Object value = getValue();
		return value != null ? value.toString() : "";
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text == null || StringUtils.isBlank(text)) {
			setValue(null);
		} else {
			String value = StringUtils.trim(text);
			if (escapeHTML) {
				value = HtmlUtils.htmlEscape(value);
			}
			if (escapeJavaScript) {
				value = JavaScriptUtils.javaScriptEscape(value);
			}
			setValue(value);
		}
	}

}
