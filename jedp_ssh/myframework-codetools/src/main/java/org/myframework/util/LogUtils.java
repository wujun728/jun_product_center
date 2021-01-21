package org.myframework.util;

import java.util.List;
import java.util.Map;

public class LogUtils {

	LogUtils() {
		// TODO Auto-generated constructor stub
	}

	public static void print(Object obj) {
		if (obj == null)
			return;
		System.out.println("===========content===========");
		if (obj instanceof Map) {
			Map map = (Map) obj;
			for (Object key : map.keySet()) {
				System.out.println(key + "=" + map.get(key));
			}
		} else if (obj instanceof List) {
			List ls = (List) obj;
			int i = 0;
			for (Object item : ls) {
				System.out.println("item [" + (i++) + "]=" + item);
			}
		} else
			System.out.println("obj=" + obj);

		System.out.println("===========content===========");
	}

}
