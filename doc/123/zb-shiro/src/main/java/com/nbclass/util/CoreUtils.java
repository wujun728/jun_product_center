package com.nbclass.util;

import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
/**
 * @version V1.0
 * @date 2018年7月11日
 * @author superzheng
 */
public class CoreUtils {

    public static <T> T copy(Object orig, Class<T> clazz) {
        T dest = null;
        try {
            if (notNull(orig)) {
                dest = clazz.newInstance();
                PropertyUtils.copyProperties(dest, orig);
            }
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return dest;
    }

    public static <T> List<T> copyList(List<?> origs, Class<T> clazz) {
        List<T> list = new ArrayList<T>();
        if (notNull(origs)) {
            for (Object orig : origs) {
                list.add(copy(orig, clazz));
            }
        }
        return list;
    }

    public static boolean notNullAndEmpty(String string) {
        return null == string || "".equals(string) ? false : true;
    }

    public static boolean notNullAndZero(List<?> list) {
        return null == list || list.size() == 0 ? false : true;
    }

    public static boolean notNull(Object object) {
        if (object instanceof List<?>) {
            return notNullAndZero((List<?>) object);
        } else if (object instanceof String) {
            return notNullAndEmpty((String) object);
        } else {
            return null != object;
        }
    }

    public static boolean isEmpty(String string) {
        return null == string || "".equals(string) ? true : false;
    }

}