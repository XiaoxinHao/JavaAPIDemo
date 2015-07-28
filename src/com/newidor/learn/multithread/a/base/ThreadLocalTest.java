package com.newidor.learn.multithread.a.base;

public class ThreadLocalTest {

	static class ResourceClass {

		private final ThreadLocal<String> RESOURCE_1 = new ThreadLocal<String>();

		private final ThreadLocal<String> RESOURCE_2 = new ThreadLocal<String>();

		public ThreadLocal<String> getRESOURCE_1() {
			return RESOURCE_1;
		}

		public ThreadLocal<String> getRESOURCE_2() {
			return RESOURCE_2;
		}

	}

	static class A {

		private ThreadLocal<String> RESOURCE_1;

		private ThreadLocal<String> RESOURCE_2;

		public void setRESOURCE_1(ThreadLocal<String> rESOURCE_1) {
			RESOURCE_1 = rESOURCE_1;
		}

		public void setRESOURCE_2(ThreadLocal<String> rESOURCE_2) {
			RESOURCE_2 = rESOURCE_2;
		}

		public void setOne(String value) {
			RESOURCE_1.set(value);
		}

		public void setTwo(String value) {
			RESOURCE_2.set(value);
		}
	}

	static class B {

		private ThreadLocal<String> RESOURCE_1;

		private ThreadLocal<String> RESOURCE_2;

		public void setRESOURCE_1(ThreadLocal<String> rESOURCE_1) {
			RESOURCE_1 = rESOURCE_1;
		}

		public void setRESOURCE_2(ThreadLocal<String> rESOURCE_2) {
			RESOURCE_2 = rESOURCE_2;
		}

		public void display() {
			System.out.println(RESOURCE_1.get() + ":" + RESOURCE_2.get());
		}
	}

	public static void main(String[] args) {

		ThreadLocalTest.ResourceClass resourceClass = new ThreadLocalTest.ResourceClass();
		ThreadLocal<String> RESOURCE_1 = resourceClass.getRESOURCE_1();
		ThreadLocal<String> RESOURCE_2 = resourceClass.getRESOURCE_2();

		final A a = new A();
		a.setRESOURCE_1(RESOURCE_1);
		a.setRESOURCE_2(RESOURCE_2);
		final B b = new B();
		b.setRESOURCE_1(RESOURCE_1);
		b.setRESOURCE_2(RESOURCE_2);

		for (int i = 0; i < 15; i++) {
			final String resouce1 = "Ïß³Ì-" + i, resouce2 = " value = (" + i
					+ ")";
			new Thread() {
				public void run() {
					try {
						a.setOne(resouce1);
						a.setTwo(resouce2);
						b.display();
					} finally {
						a.RESOURCE_1.remove();
						b.RESOURCE_2.remove();
					}
				}
			}.start();
		}
	}
}
