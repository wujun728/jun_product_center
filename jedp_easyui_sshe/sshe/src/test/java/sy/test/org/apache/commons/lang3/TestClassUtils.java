package sy.test.org.apache.commons.lang3;

import java.util.ArrayList;

import org.apache.commons.lang3.ClassUtils;

public class TestClassUtils {

	public String name;
	private int age;
	public static String STATIC_FIELD = "staticStr";

	public static void main(String[] args) {

		// 获取ArrayList类所有实现的接口
		System.out.println(ClassUtils.getAllInterfaces(ArrayList.class));

		// 获取ArrayList类所有父类
		System.out.println(ClassUtils.getAllSuperclasses(ArrayList.class));

		// 获取ArrayList类所在的包名
		System.out.println(ClassUtils.getPackageName(ArrayList.class));

		// 获取ArrayList类简单类名
		System.out.println(ClassUtils.getShortClassName(ArrayList.class));

		// 判断是否可以转型
		System.out.println(ClassUtils.isAssignable(ArrayList.class, Object.class));

		// 判断是否有内部类
		System.out.println(ClassUtils.isInnerClass(ArrayList.class));
	}

	public TestClassUtils() {
		System.out.println("构造");
	}

	public TestClassUtils(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public TestClassUtils(String str) {
		System.out.println(str);
	}

	public void publicHello() {
		System.out.println("publicHello");
	}

	public void publicHello(String str) {
		System.out.println("publicHello  " + str);
	}

	public void publicHello(String str, Integer i) {
		System.out.println("publicHello  " + str + "  " + i);
	}

	public static void staticHello() {
		System.out.println("staticHello");
	}

}
