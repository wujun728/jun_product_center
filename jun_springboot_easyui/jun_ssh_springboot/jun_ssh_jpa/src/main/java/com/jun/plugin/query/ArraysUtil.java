package com.jun.plugin.query;


public class ArraysUtil {
	/**
	 * 用","连接
	 * @param a
	 * @param itemValue
	 * @return
	 * @throws Exception
	 */
	public static String toString(Object[] a,ItemValue itemValue) throws Exception{
		return toString(a, itemValue, ",");
	}
	/**
	 * 将数组的每一项按照指定方式toString，并通过separator连接起来.itemValue为null会忽略
	 * @param a
	 * @param itemValue
	 * @param separator
	 * @return
	 * @throws Exception
	 */
	public static String toString(Object[] a,ItemValue itemValue,String separator) throws Exception{
        if (a == null)
            return null;
        int iMax = a.length - 1;
        if (iMax == -1)
            return null;
        
        StringBuilder b = new StringBuilder();
        for (int i = 0; ; i++) {
        	String value=itemValue.of(a[i]);
            b.append(value==null?"":value);
            if (i == iMax)
                return b.toString();
            if(value!=null)
            	b.append(separator);
        }
	}
	
	
}