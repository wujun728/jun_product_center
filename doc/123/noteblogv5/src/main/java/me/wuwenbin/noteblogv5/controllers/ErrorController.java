package me.wuwenbin.noteblogv5.controllers;

import me.wuwenbin.noteblogv5.model.json.ResultBeanObj;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Map;

import static org.springframework.http.MediaType.*;

/**
 * created by Wuwenbin on 2018/7/17 at 17:03
 *
 * @author wuwenbin
 */
@Controller
@RequestMapping("/error")
public class ErrorController extends BaseController implements org.springframework.boot.web.servlet.error.ErrorController {

    private static final String ERROR_PAGE = "error/page";
    private static final String ERROR_ROUTER = "error/router";

    private ErrorAttributes errorAttributes;
    private ServerProperties serverProperties;


    public ErrorController(ErrorAttributes errorAttributes, ServerProperties serverProperties) {
        Assert.notNull(errorAttributes, "ErrorAttributes must not be null");
        this.errorAttributes = errorAttributes;
        this.serverProperties = serverProperties;
    }

    @Override
    public String getErrorPath() {
        return ERROR_PAGE;
    }

    /**
     * 非json的错误请求处理
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(produces = TEXT_HTML_VALUE)
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        HttpStatus status = getStatus(request);
        Map<String, Object> model = Collections.synchronizedMap(getErrorAttributes(request, isIncludeStackTrace(request)));
        response.setStatus(status.value());
        model.put("status", status.value());
        String template = isRouter(request) ? ERROR_ROUTER : ERROR_PAGE;
        model.put("message", request.getSession().getAttribute("errorMessage"));
        request.getSession().removeAttribute("errorMessage");
        return new ModelAndView(template, model);
    }

    /**
     * json的请求错误处理
     *
     * @param request
     * @return
     */
    @RequestMapping(produces = {
            APPLICATION_JSON_VALUE,
            APPLICATION_FORM_URLENCODED_VALUE,
            MULTIPART_FORM_DATA_VALUE
    })
    @ResponseBody
    public ResultBeanObj error(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request, isIncludeStackTrace(request));
        HttpStatus status = getStatus(request);
        String message = body.get("message").toString();
        body.remove("message");
        return ResultBeanObj.custom(status.value(), message, body);
    }


    private HttpStatus getStatus(HttpServletRequest request) {
        String code = request.getParameter("errorCode");
        Integer statusCode = code == null ? null : Integer.valueOf(code);
        if (statusCode == null) {
            statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        }
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        try {
            return HttpStatus.valueOf(statusCode);
        } catch (Exception ex) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    private Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
        WebRequest requestAttributes = new ServletWebRequest(request);
        return errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
    }

    private boolean isIncludeStackTrace(HttpServletRequest request) {
        ErrorProperties.IncludeStacktrace include = this.serverProperties.getError().getIncludeStacktrace();
        return include == ErrorProperties.IncludeStacktrace.ALWAYS || include == ErrorProperties.IncludeStacktrace.ON_TRACE_PARAM && getTraceParameter(request);
    }

    private boolean getTraceParameter(HttpServletRequest request) {
        String parameter = request.getParameter("trace");
        return parameter != null && !"false".equals(parameter.toLowerCase());
    }

}
