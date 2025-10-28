package sy.util.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

/**
 * 与spring mvc结合，像前台返回json格式数据
 * 
 * 在controller类上面写@ResponseBody注解就可以
 * 
 * http://git.oschina.net/sphsyv/sypro
 * 
 * @author 孙宇
 *
 */
public class HibernateAwareObjectMapper extends ObjectMapper {
	public HibernateAwareObjectMapper() {
		registerModule(new Hibernate4Module());
	}
}
