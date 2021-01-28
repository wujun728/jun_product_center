package sy.test.org.apache.commons.lang3.reflect;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.lang3.reflect.ConstructorUtils;

import sy.test.org.apache.commons.lang3.TestClassUtils;

public class TestConstructorUtils {

	public static void main(String[] args) {
		// 获取参数为String的构造函数
		System.out.println(ConstructorUtils.getAccessibleConstructor(TestClassUtils.class, String.class));

		// 执行参数为String的构造函数
		try {
			TestClassUtils test = (TestClassUtils) ConstructorUtils.invokeConstructor(TestClassUtils.class, "Hello");
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
	}

}
