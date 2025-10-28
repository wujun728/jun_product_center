package io.github.wujun728.admin.util;

import java.lang.reflect.Constructor;
import java.util.Collection;

public class CollectionUtil {
    /***
     * c1集合-c2集合,原集合不变,产生新的差集集合
     * @param c1
     * @param c2
     * @param <C>
     * @return
     */
    public static <C extends Collection>  C remove(C c1,C c2){
        C copy = copy(c1);
        copy.removeAll(c2);
        return copy;
    }

    /***
     * 两个集合交集,原集合不变,产生新的交集集合
     * @param c1
     * @param c2
     * @param <C>
     * @return
     */
    public static <C extends Collection>  C retain(C c1,C c2){
        C copy = copy(c1);
        c1.retainAll(c2);
        return copy;
    }

    /**
     * 复制集合
     * @param c
     * @param <C>
     * @return
     */
    public static <C extends Collection>  C copy(C c){
        try {
            Constructor<? extends Collection> constructor = c.getClass().getConstructor(Collection.class);
            C copy = (C) constructor.newInstance(c);
            return copy;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
