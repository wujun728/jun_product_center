package sy.util.base;

import org.hibernate.collection.spi.PersistentCollection;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.proxy.LazyInitializer;

import com.alibaba.fastjson.serializer.PropertyFilter;

/**
 * 由于hibernate在懒加载的时候，转换json会出问题，所以编写了这个类用于过滤没有预先抓取出来的属性，以免代理对象被fastjson转换导致出错
 * 
 * http://git.oschina.net/sphsyv/sypro
 * 
 * @author 孙宇
 *
 */
public class JsonPropertyFilter implements PropertyFilter {

	/**
	 * @param object
	 *            属性所在的对象
	 * @param name
	 *            属性名
	 * @param value
	 *            属性值
	 * @return 返回false属性将被忽略，ture属性将被保留
	 */
	@Override
	public boolean apply(Object object, String name, Object value) {
		if (value instanceof HibernateProxy) {// hibernate代理对象
			LazyInitializer initializer = ((HibernateProxy) value).getHibernateLazyInitializer();
			if (initializer.isUninitialized()) {
				return false;
			}
		} else if (value instanceof PersistentCollection) {// 实体关联集合
			PersistentCollection collection = (PersistentCollection) value;
			if (!collection.wasInitialized()) {
				return false;
			}
			Object val = collection.getValue();
			if (val == null) {
				return false;
			}
		}
		return true;
	}

}
