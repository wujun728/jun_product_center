package sy.test.org.apache.commons.lang3.reflect;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.lang3.reflect.MethodUtils;

import sy.test.org.apache.commons.lang3.TestClassUtils;

public class TestMethodUtils {

	public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		TestClassUtils test = new TestClassUtils();

		// 调用无参方法
		MethodUtils.invokeMethod(test, "publicHello", null);

		// 调用一参方法
		MethodUtils.invokeMethod(test, "publicHello", "Hello");

		// 调用多参方法
		MethodUtils.invokeMethod(test, "publicHello", new Object[] { "100", new Integer(10) });

		// 调用静态方法
		MethodUtils.invokeStaticMethod(TestClassUtils.class, "staticHello", null);
	}

}
