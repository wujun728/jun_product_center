package com.sanri.tools.modules.core.service.data;

import com.sanri.tools.modules.core.service.data.regex.OrdinaryNode;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Pattern;

/**
 * 使用正则表达式生成随机数据
 */
@Service
@Slf4j
public class RegexRandomDataService extends RandomDataService{
    /**
     * 从正则表达式生成随机数据
     * @param expression
     * @return
     */
    public static String regexRandom(String expression)  {
        try {
            OrdinaryNode ordinaryNode = new OrdinaryNode(expression);
            return ordinaryNode.random();
        } catch (Exception e) {
            log.error("使用正则表达式生成数据失败,{}:{}",e.getClass().getSimpleName(),e.getMessage());
        }
        return "";
    }

    /**
     * 重写 populateDataOrigin ,读取标记的正则表达式,然后使用正则表达式生成数据
     * @param columnName
     * @param propertyType
     * @return
     */
    @Override
    protected Object populateDataOrigin(String columnName, Class<?> propertyType) {
        Pattern pattern = AnnotationUtils.getAnnotation(propertyType, Pattern.class);
        if(pattern == null || propertyType.isArray() || (!String.class.isAssignableFrom(propertyType))){
            return super.populateDataOrigin(columnName,propertyType);
        }
        String regexp = pattern.regexp();
        return regexRandom(regexp);
    }
}
