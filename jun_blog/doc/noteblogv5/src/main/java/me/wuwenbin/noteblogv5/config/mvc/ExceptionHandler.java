package me.wuwenbin.noteblogv5.config.mvc;

import me.wuwenbin.noteblogv5.exception.AppRunningException;
import me.wuwenbin.noteblogv5.exception.AppSetException;
import me.wuwenbin.noteblogv5.model.json.ResultBeanObj;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wuwen
 */
@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {
            AppRunningException.class,
            AppSetException.class
    })
    @ResponseBody
    public ResultBeanObj handle(Exception e) {
        return ResultBeanObj.error(e.getMessage());
    }
}
