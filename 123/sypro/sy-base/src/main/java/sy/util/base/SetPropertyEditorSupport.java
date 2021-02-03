package sy.util.base;

import java.beans.PropertyEditorSupport;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.nutz.lang.Mirror;

/**
 * 与spring mvc的@InitBinder结合
 * 
 * 在controller的initBinder时，如果是Set类型的属性，前台传递的ids字符串，自动转成Set<T>类型
 * 
 * http://git.oschina.net/sphsyv/sypro
 * 
 * @author 孙宇
 * 
 */
public class SetPropertyEditorSupport extends PropertyEditorSupport {

	private Class clazz;

	public SetPropertyEditorSupport(Class clazz) {
		this.clazz = clazz;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text == null || StringUtils.isBlank(text)) {
			setValue(null);
		} else {
			Mirror<?> mirror = Mirror.me(clazz);
			Set l = new HashSet();
			for (String id : text.split(",")) {
				Object t;
				try {
					t = clazz.newInstance();
					mirror.setValue(t, "id", Long.parseLong(id));
					l.add(t);
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			setValue(l);
		}
	}

}
