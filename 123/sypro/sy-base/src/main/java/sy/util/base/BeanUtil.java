package sy.util.base;

import java.util.Arrays;
import java.util.Map;

import org.nutz.json.Json;
import org.nutz.lang.Mirror;
import org.nutz.mapl.Mapl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * Bean工具类，主要用于属性值拷贝
 * 
 * 一般情况下都用在更新操作上，用新对象的信息覆盖掉旧对象信息，然后update
 * 
 * copy属性可以include或者exclude属性
 * 
 * copy属性还可以不拷贝值为null的
 * 
 * http://git.oschina.net/sphsyv/sypro
 * 
 * @author 孙宇
 *
 */
public class BeanUtil {

	/**
	 * copy属性值
	 * 
	 * @param source
	 *            源
	 * @param target
	 *            目标
	 */
	public static void copyProperties(Object source, Object target) {
		copyProperties(source, target, null, null, true);
	}

	/**
	 * copy属性值，不copy为空的属性值
	 * 
	 * @param source
	 *            源
	 * @param target
	 *            目标
	 */
	public static void copyNotNullProperties(Object source, Object target) {
		copyProperties(source, target, null, null, false);
	}

	/**
	 * copy属性值
	 * 
	 * @param source
	 *            源
	 * @param target
	 *            目标
	 * @param includes
	 *            复制哪些属性
	 * @param excludes
	 *            排除哪些属性
	 * 
	 * @param copyNotNullProperty
	 *            是否copy为空的属性
	 */
	public static void copyProperties(Object source, Object target, String[] includes, String[] excludes, Boolean copyNotNullProperty) {
		JsonPropertyFilter filter = new JsonPropertyFilter();
		String json = JSON.toJSONString(source, filter, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.DisableCircularReferenceDetect);
		Object sourceMapl = Mapl.toMaplist(Json.fromJson(json));// 将源对象转换成mapl格式对象
		if (includes != null && includes.length > 0) {
			sourceMapl = Mapl.includeFilter(sourceMapl, Arrays.asList(includes));// 只要includes的属性
		}
		if (excludes != null && excludes.length > 0) {
			sourceMapl = Mapl.excludeFilter(sourceMapl, Arrays.asList(excludes));// 排除excludes的属性
		}
		if (sourceMapl != null) {
			Map<String, ?> sourceMap = (Map<String, ?>) sourceMapl;
			Mirror<?> mirror = Mirror.me(target.getClass());
			for (String key : sourceMap.keySet()) {
				Object value = sourceMap.get(key);
				if (value != null || (value == null && copyNotNullProperty)) {
					mirror.setValue(target, key, value);// 将当前属性覆盖到目标同名属性中
				}
			}
		}
	}

	/**
	 * copy属性值
	 * 
	 * @param source
	 *            源
	 * @param target
	 *            目标
	 * @param includes
	 *            复制哪些属性
	 */
	public static void copyPropertiesInclude(Object source, Object target, String[] includes) {
		copyProperties(source, target, includes, null, true);
	}

	/**
	 * copy属性值，不copy为空的属性值
	 * 
	 * @param source
	 *            源
	 * @param target
	 *            目标
	 * @param includes
	 *            复制哪些属性
	 */
	public static void copyNotNullPropertiesInclude(Object source, Object target, String[] includes) {
		copyProperties(source, target, includes, null, false);
	}

	/**
	 * copy属性值
	 * 
	 * @param source
	 *            源
	 * @param target
	 *            目标
	 * @param excludes
	 *            排除哪些属性
	 */
	public static void copyPropertiesExclude(Object source, Object target, String[] excludes) {
		copyProperties(source, target, null, excludes, true);
	}

	/**
	 * copy属性值，不copy为空的属性值
	 * 
	 * @param source
	 *            源
	 * @param target
	 *            目标
	 * @param excludes
	 *            排除哪些属性
	 */
	public static void copyNotNullPropertiesExclude(Object source, Object target, String[] excludes) {
		copyProperties(source, target, null, excludes, false);
	}

}
