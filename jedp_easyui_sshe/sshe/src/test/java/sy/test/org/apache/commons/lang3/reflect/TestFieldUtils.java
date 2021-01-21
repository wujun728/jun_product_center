package sy.test.org.apache.commons.lang3.reflect;

import org.apache.commons.lang3.reflect.FieldUtils;

import sy.test.org.apache.commons.lang3.TestClassUtils;

public class TestFieldUtils {

	public static void main(String[] args) throws IllegalAccessException {
		TestClassUtils test = new TestClassUtils("孙宇", 30);

		// 以下两个方法完全一样,都能获取共有或私有变量,因为第三个参数都设置了不检查
		FieldUtils.getDeclaredField(TestClassUtils.class, "name", true);
		FieldUtils.getField(TestClassUtils.class, "age", true);

		// 读取私有或公共变量的值
		System.out.println(FieldUtils.readField(test, "name", true));
		System.out.println(FieldUtils.readField(test, "age", true));

		// 读取静态变量
		System.out.println(FieldUtils.readStaticField(TestClassUtils.class, "STATIC_FIELD"));

		// 写入私有或共有变量
		FieldUtils.writeField(test, "name", "孙宇2", true);
		System.out.println(FieldUtils.readField(test, "name", true));

		// 写入静态变量
		FieldUtils.writeStaticField(TestClassUtils.class, "STATIC_FIELD", "static_value");
		System.out.println(FieldUtils.readStaticField(TestClassUtils.class, "STATIC_FIELD"));
	}

}
