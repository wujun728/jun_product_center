package org.myframework.support.base;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.myframework.support.csv.CsvExport;
import org.myframework.support.i18n.II18nService;
import org.myframework.support.pdf.PdfExport;
import org.myframework.web.bind.ServiceResult;


/**
 * 功能说明:
 *  基础rest
 *  service用到同一个模型的场景很多,所以不使用泛型注入
 *
 */
public abstract class BaseRest {
	public static final  String PAGE_FLAG = "PAGE_FLAG";	//分页标记

	protected final Log logger = LogFactory.getLog(getClass());

	@Resource(name="i18nService")
	protected II18nService i18nService;

	@Resource(name = "csvExport")
	protected CsvExport csvExport;

	@Resource(name = "pdfExport")
	protected PdfExport pdfExport;
 
	/**
	 * 返回正常结果
	 * @param content
	 * @return
	 */
	protected ServiceResult createSuccessResult(Object content) {
		ServiceResult serviceResult = new ServiceResult();
		serviceResult.setSuccess(true);
		serviceResult.setContent(content);
		return serviceResult;
	}

	/**
	 * 返回错误原因信息
	 * @param errmsg
	 * @return
	 */
	protected ServiceResult createFailResult(String errmsg) {
		ServiceResult serviceResult = new ServiceResult();
		serviceResult.setSuccess(false);
		serviceResult.setErrorMessage(errmsg);
		return serviceResult;
	}
	
	/**
	 *  也可以使用SPRINGMVC的 ModelAndView对象来实现页面跳转含义是类似的，代码举例：
	 *  	@RequestMapping(value = "/view.html")
	 *		@ActionLog(content = "功能说明", description = "")
	 *		public ModelAndView view(ModelMap model){
	 *			logger.debug("2222");
	 *			 return new ModelAndView("redirect:/hollybeacon/security/page/restSecurity.jsp");
	 *			//return new ModelAndView("forward:/hollybeacon/security/page/restSecurity.jsp");
	 *		}
	 * springmvc风格,forward:表示请求转发，redirect:请求重定向
	 * @param location
	 * @param request
	 * @param response
	 */
	protected void jumpToView(String location, HttpServletRequest request, HttpServletResponse response) {
		 if(location.startsWith("forward:")) {
			 forward(location.substring("forward:".length()),  request,  response);
		 }
		 if(location.startsWith("redirect:")) {
			 redirect(location.substring("redirect:".length()),  request,  response);
		 }
	}

	/**
	 * redirect:请求重定向
	 * @param location
	 * @param request
	 * @param response
	 */
	protected void redirect(String location, HttpServletRequest request, HttpServletResponse response) {
		if(location.startsWith("/"))//本应用页面需要添加ContextPath
			location = request.getContextPath()+location;
		try {
			response.sendRedirect(location);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * forward :请求转发
	 * @param jspView
	 * @param request
	 * @param response
	 */
	protected void forward(String jspView, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			request.getRequestDispatcher(jspView).forward(request,
					response);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	 
}
