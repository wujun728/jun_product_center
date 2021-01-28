package org.myframework.web.eclipsedebug;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

import org.myframework.web.filter.XssFilterTest;
import org.springframework.core.MethodIntrospector;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;

public class EclipseDebugTest {

	public EclipseDebugTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			System.out.println(">>>");
		}
		Class targetClass = XssFilterTest.class;
		Map<Method, Set<Scheduled>> annotatedMethods = MethodIntrospector
				.selectMethods(targetClass,
						new MethodIntrospector.MetadataLookup<Set<Scheduled>>() {
							@Override
							public Set<Scheduled> inspect(Method method) {
								Set<Scheduled> scheduledMethods = AnnotationUtils
										.getRepeatableAnnotations(method,
												Scheduled.class,
												Schedules.class);
								return (!scheduledMethods.isEmpty()
										? scheduledMethods : null);
							}
						});
		
		System.out.println(annotatedMethods.keySet());

	}

}
