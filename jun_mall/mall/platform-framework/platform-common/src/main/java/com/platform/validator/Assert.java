package com.platform.validator;

import com.platform.utils.RRException;
import org.apache.commons.lang.StringUtils;

/**
 * 数据校验
 *
 * @author lipengjun
 * @date 2017年11月18日 下午13:13:23
 */
public abstract class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new RRException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new RRException(message);
        }
    }
}
