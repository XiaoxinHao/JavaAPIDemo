package com.newidor.learn.annotation;


@SimpleAnnotation(name = "type", gid = Long.class)
// ���Աע��
public class UserAnnotation {
	@SimpleAnnotation(name = "param", id = 1, gid = Long.class)
	// ���Աע��
	private Integer age;

	@SimpleAnnotation(name = "construct", id = 2, gid = Long.class)
	// ���췽��ע��
	public UserAnnotation() {
	}

	@SimpleAnnotation(name = "public method", id = 3, gid = Long.class)
	// �෽��ע��
	public void a() {
	}

	@SimpleAnnotation(name = "protected method", id = 4, gid = Long.class)
	// �෽��ע��
	protected void b() {
	}

	@SimpleAnnotation(name = "private method", id = 5, gid = Long.class)
	// �෽��ע��
	private void c() {
	}

	public void b(Integer a) {
	}
}
