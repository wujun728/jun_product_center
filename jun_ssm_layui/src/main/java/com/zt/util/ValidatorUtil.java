package com.zt.util;

import com.zt.exception.ValidatorParamterException;
import org.apache.commons.collections.MapUtils;

import javax.validation.*;
import java.util.*;

/**
 * Created by CDHong on 2018/4/21.
 */
public class ValidatorUtil {
    /**
     * 如果在验证不通过的时候进行了添加、更新或删除操作的时候，则会抛出javax.validation.ConstraintViolationException异常
     * @param obj
     * @return
     */
    private static Map<String,String> validator(Object obj){
        //定义好返回值Map
        Map<String,String> errors = new LinkedHashMap<>();
        //在校验工厂中获取校验对象
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        //通过校验对象校验传入的obj对象是否符合要求，并返回校验结果
        Set<ConstraintViolation<Object>> validateResult = validator.validate(obj);
        //如果校验结果不为空，说明有不符合要求的，则把其封装为Map对象返回
        if(!validateResult.isEmpty()){
            Iterator<ConstraintViolation<Object>> it = validateResult.iterator();
            while (it.hasNext()){
                ConstraintViolation<Object> cv = it.next();
                errors.put(cv.getPropertyPath().toString(),cv.getMessage());
            }
        }
        return errors;
    }

    /**
     *通过全局异常统一处理，把不合法的信息通过JSONData传递到前端。
     * @param obj
     */
    public static void checkValidator(Object obj){
        Map<String, String> map = ValidatorUtil.validator(obj);
        if(MapUtils.isNotEmpty(map)){
            throw new ValidatorParamterException(map.toString());
        }
    }

}
