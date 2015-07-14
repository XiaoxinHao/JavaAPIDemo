package com.newidor.learn.annotation;


@SimpleAnnotation(name = "type", gid = Long.class)
// 类成员注解
public class UserAnnotation {
	@SimpleAnnotation(name = "param", id = 1, gid = Long.class)
	// 类成员注解
	private Integer age;

	@SimpleAnnotation(name = "construct", id = 2, gid = Long.class)
	// 构造方法注解
	public UserAnnotation() {
	}

	@SimpleAnnotation(name = "public method", id = 3, gid = Long.class)
	// 类方法注解
	public void a() {
	}

	@SimpleAnnotation(name = "protected method", id = 4, gid = Long.class)
	// 类方法注解
	protected void b() {
	}

	@SimpleAnnotation(name = "private method", id = 5, gid = Long.class)
	// 类方法注解
	private void c() {
	}

	public void b(Integer a) {
	}
}
