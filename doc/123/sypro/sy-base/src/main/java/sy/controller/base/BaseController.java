package sy.controller.base;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nutz.json.Json;
import org.nutz.lang.Lang;
import org.nutz.lang.Mirror;
import org.nutz.mapl.Mapl;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

import sy.pageModel.base.JsonResult;
import sy.pageModel.base.PageResult;
import sy.service.base.BaseService;
import sy.util.base.DateTimePropertyEditorSupport;
import sy.util.base.JsonPropertyFilter;
import sy.util.base.QueryFilter;
import sy.util.base.StringPropertyEditorSupport;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 基础控制器
 * 
 * 其他控制器继承此控制器获得日期字段类型转换和防止XSS攻击的功能
 * 
 * 此类还有一个序列化json的方法，可以返回你需要的属性
 * 
 * http://git.oschina.net/sphsyv/sypro
 * 
 * @author 孙宇
 *
 */
public abstract class BaseController<T extends Serializable, PK extends Serializable> {

	private final Class<T> entityClass;

	protected BaseService<T, PK> service;

	public abstract void setService(BaseService<T, PK> service);

	public BaseController() {
		this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		/**
		 * 自动转换日期类型的字段格式
		 */
		binder.registerCustomEditor(Date.class, new DateTimePropertyEditorSupport());

		/**
		 * 防止XSS攻击，并且带去左右空格功能
		 */
		binder.registerCustomEditor(String.class, new StringPropertyEditorSupport(true, false));
	}

	/**
	 * 异常处理
	 * 
	 * @param request
	 * @param ex
	 * @return
	 */
	@ExceptionHandler
	public String exception(HttpServletRequest request, Exception exception) {
		System.out.println(Lang.wrapThrow(exception).getMessage());
		return "/error/controllerException.jsp";
	}

	public JsonResult save(T t) {
		JsonResult j = new JsonResult();

		try {
			Mirror<?> mirror = deleteTheEmptyObjectInTheCollection(t);// 如果不是级联添加，那么这句话不写也行，子方法重写save的时候，视情况添加这句话吧

			service.save(t);
			j.setMsg("保存成功");
			j.setSuccess(true);
			j.setObj(t);
		} catch (Exception e) {
			j.setMsg("保存失败！");
			e.printStackTrace();
		}

		return j;
	}

	public JsonResult delete(QueryFilter filter) {
		JsonResult j = new JsonResult();
		try {
			service.delete(service.get(filter));
			j.setMsg("删除成功");
			j.setSuccess(true);
		} catch (Exception e) {
			j.setMsg("删除失败");
		}
		return j;
	}

	public JsonResult update(T t) {
		Mirror<?> mirror = deleteTheEmptyObjectInTheCollection(t);// 如果不是级联更新，那么这句话不写也行，子方法重写update的时候，视情况添加这句话吧

		JsonResult j = new JsonResult();
		PK id = (PK) mirror.getValue(t, "id");// 如果你的主键不叫id，那么只能在之类里面重写update方法了
		T ot = service.get(id);
		if (ot != null) {
			try {
				BeanUtils.copyProperties(t, ot, new String[] { "created" });// 一句话将新对象信息覆盖到原数据中,但是创建时间是不需要更新的
				mirror.setValue(ot, "modified", new Timestamp(new java.util.Date().getTime()));
				service.update(ot);
				j.setMsg("更新成功");
				j.setSuccess(true);
				j.setObj(t);
			} catch (Exception e) {
				j.setMsg("更新失败！");
				e.printStackTrace();
			}
		} else {
			j.setMsg("更新失败，找不到对象");
		}
		return j;
	}

	public JsonResult get(QueryFilter filter) {
		JsonResult j = new JsonResult();
		j.setSuccess(true);
		j.setObj(service.get(filter));
		return j;
	}

	public PageResult find(QueryFilter filter) {
		PageResult p = new PageResult();

		p.setRows(service.find(filter));
		p.setTotalRows(service.count(filter));
		p.setPage(filter.getPage());
		p.setPageSize(filter.getPageSize());

		return p;
	}

	/**
	 * 序列化json并返回到前台
	 * 
	 * @param object
	 * @param includesProperties
	 *            要序列化的属性
	 * @param response
	 */
	public void returnJsonByIncludeProperties(Object object, String[] includesProperties, HttpServletResponse response) {
		try {
			JsonPropertyFilter filter = new JsonPropertyFilter();// 用了这个过滤器，妈妈再也不用担心序列化JSON时hibernate懒加载异常了
			// System.out.println("对象转JSON：要排除的属性[" + excludesProperties + "]要包含的属性[" + includesProperties + "]");
			String json;
			// 使用SerializerFeature.WriteDateUseDateFormat特性来序列化日期格式的类型为yyyy-MM-dd HH24:mi:ss
			// 使用SerializerFeature.DisableCircularReferenceDetect特性关闭引用检测和生成
			json = JSON.toJSONString(object, filter, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.DisableCircularReferenceDetect);

			// 使用SerializerFeature.BrowserCompatible特性会把所有的中文都会序列化为\\uXXXX这种格式，字节数会多一些，但是能兼容IE6
			// json = JSON.toJSONString(object, filter, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.BrowserCompatible);
			// System.out.println("转换后的JSON字符串：" + json);

			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(Json.toJson(Mapl.includeFilter(Json.fromJson(json), Lang.array2list(includesProperties))));
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 在级联保存或者级联更新的时候，过滤id为空的关联对象
	 * 
	 * 避免出现下面错误
	 * 
	 * Caused by: org.hibernate.TransientObjectException: object references an unsaved transient instance - save the transient instance before flushing
	 * 
	 * @param t
	 * @return
	 */
	public Mirror<?> deleteTheEmptyObjectInTheCollection(T t) {
		Mirror<?> mirror = Mirror.me(this.entityClass);
		Field[] fields = mirror.getFields();// 获得这个类的所有属性
		for (Field field : fields) {
			if (field.getGenericType().toString().indexOf("java.util.List") > -1) {
				List list = (List) mirror.getValue(t, field.getName());
				for (Iterator it = list.iterator(); it.hasNext();) {
					Object o = it.next();
					Mirror<?> oMirror = Mirror.me(o);
					if (oMirror.getValue(o, "id") == null) {// 我默认你的主键叫ID，如果不叫这个，那你只能重写方法了
						it.remove();
					}
				}
			}
		}
		return mirror;
	}

}
