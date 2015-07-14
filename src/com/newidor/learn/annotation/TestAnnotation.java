package com.newidor.learn.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class TestAnnotation {

	public static void parseTypeAnnotation() throws ClassNotFoundException {
		Annotation[] annotations = UserAnnotation.class.getAnnotations();
		for (Annotation annotation : annotations) {
			SimpleAnnotation SimpleAnnotation = (SimpleAnnotation) annotation;
			System.out.println("id= " + SimpleAnnotation.id() + "; name= "
					+ SimpleAnnotation.name() + "; gid = "
					+ SimpleAnnotation.gid());
		}
	}

	public static void parseMethodAnnotation() {
		Method[] methods = UserAnnotation.class.getDeclaredMethods();
		for (Method method : methods) {
			boolean hasAnnotation = method
					.isAnnotationPresent(SimpleAnnotation.class);
			if (hasAnnotation) {
				SimpleAnnotation annotation = method
						.getAnnotation(SimpleAnnotation.class);
				System.out.println("method = " + method.getName() + " ; id = "
						+ annotation.id() + " ; description = "
						+ annotation.name() + "; gid= " + annotation.gid());
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public static void parseConstructAnnotation() {
		Constructor[] constructors = UserAnnotation.class.getConstructors();
		for (Constructor<?> constructor : constructors) {
			boolean hasAnnotation = constructor
					.isAnnotationPresent(SimpleAnnotation.class);
			if (hasAnnotation) {
				SimpleAnnotation annotation = constructor
						.getAnnotation(SimpleAnnotation.class);
				System.out.println("constructor = " + constructor.getName()
						+ " ; id = " + annotation.id() + " ; description = "
						+ annotation.name() + "; gid= " + annotation.gid());
			}
		}
	}

	public static void main(String[] args) throws ClassNotFoundException {
		parseTypeAnnotation();
		parseMethodAnnotation();
		parseConstructAnnotation();
	}
}
