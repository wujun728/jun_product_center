package com.lu.web.core.base.controller;

import com.lu.web.core.reqres.response.SuccessResponseData;
import com.lu.web.core.utils.HttpContext;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Objects;

/**
 * @program LuBoot
 * @description:
 * @author: zhanglu
 * @create: 2019-10-08 10:47:00
 */
public class BaseController {

    protected final String REDIRECT = "redirect:";
    protected final String FORWARD = "forward:";
    protected static SuccessResponseData SUCCESS_TIP = new SuccessResponseData();

    public BaseController() {
    }

    protected HttpServletRequest getHttpServletRequest() {
        return HttpContext.getRequest();
    }

    protected HttpServletResponse getHttpServletResponse() {
        return HttpContext.getResponse();
    }

    protected HttpSession getSession() {
        return ((HttpServletRequest) Objects.requireNonNull(HttpContext.getRequest())).getSession();
    }

    protected HttpSession getSession(Boolean flag) {
        return ((HttpServletRequest)Objects.requireNonNull(HttpContext.getRequest())).getSession(flag);
    }

    protected String getPara(String name) {
        return ((HttpServletRequest)Objects.requireNonNull(HttpContext.getRequest())).getParameter(name);
    }

    protected void setAttr(String name, Object value) {
        ((HttpServletRequest)Objects.requireNonNull(HttpContext.getRequest())).setAttribute(name, value);
    }


    protected void deleteCookieByName(String cookieName) {
        Cookie[] cookies = this.getHttpServletRequest().getCookies();
        Cookie[] var3 = cookies;
        int var4 = cookies.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            Cookie cookie = var3[var5];
            if (cookie.getName().equals(cookieName)) {
                Cookie temp = new Cookie(cookie.getName(), "");
                temp.setMaxAge(0);
                this.getHttpServletResponse().addCookie(temp);
            }
        }

    }

    protected void deleteAllCookie() {
        Cookie[] cookies = this.getHttpServletRequest().getCookies();
        Cookie[] var2 = cookies;
        int var3 = cookies.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            Cookie cookie = var2[var4];
            Cookie temp = new Cookie(cookie.getName(), "");
            temp.setMaxAge(0);
            this.getHttpServletResponse().addCookie(temp);
        }

    }

//    protected ResponseEntity<InputStreamResource> renderFile(String fileName, String filePath) {
//        try {
//            FileInputStream inputStream = new FileInputStream(filePath);
//            return this.renderFile(fileName, (InputStream)inputStream);
//        } catch (FileNotFoundException var4) {
//            throw new ServiceException(CoreExceptionEnum.FILE_READING_ERROR);
//        }
//    }

    protected ResponseEntity<InputStreamResource> renderFile(String fileName, byte[] fileBytes) {
        return this.renderFile(fileName, (InputStream)(new ByteArrayInputStream(fileBytes)));
    }

    protected ResponseEntity<InputStreamResource> renderFile(String fileName, InputStream inputStream) {
        InputStreamResource resource = new InputStreamResource(inputStream);
        String dfileName = null;

        try {
            dfileName = new String(fileName.getBytes("gb2312"), "iso8859-1");
        } catch (UnsupportedEncodingException var6) {
            var6.printStackTrace();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", dfileName);
        return new ResponseEntity(resource, headers, HttpStatus.CREATED);
    }

}
