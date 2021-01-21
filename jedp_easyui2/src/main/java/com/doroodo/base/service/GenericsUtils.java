package com.doroodo.base.service;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


/**
 * Provides a helper that locates the declarated generics type of a class.
 *
 * @author sshwsfc@gmail.com
 */
public class GenericsUtils {
    /**
     * Locates the first generic declaration on a class.
     *
     * @param clazz The class to introspect
     * @return the first generic declaration, or <code>null</code> if cannot be determined
     */
    public static Class getGenericClass(Class clazz) {
        return getGenericClass(clazz, 0);
    }

    /**
     * Locates  generic declaration by index on a class.
     *
     * @param clazz clazz The class to introspect
     * @param index the Index of the generic ddeclaration,start from 0.
     */
    public static Class getGenericClass(Class clazz, int index) {       
        
        Type genType = clazz.getGenericSuperclass();//class type
        if(null != genType){
                if (genType instanceof ParameterizedType) {
                    Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        
                    if ((params != null) && (params.length >= (index - 1))) {
                        return (Class) params[index];
                    }
                }
        }
        /*TODO 此路不通，需要用传统继承的方式
      Type[] interfaceType =  clazz.getDeclaredClasses();//interface type
        if(null != interfaceType && interfaceType.length>0){
                for(int i=0;i<interfaceType.length;i++){
                        Type interfType = interfaceType[i];
                        if(interfType instanceof ParameterizedType){
                                Type[] params = ((ParameterizedType) interfType).getActualTypeArguments();
                                 if ((params != null) && (params.length >= (index - 1))) {
                             return (Class) params[index];
                         }
                        }
                }
        }
        */
        return null;
    }
}