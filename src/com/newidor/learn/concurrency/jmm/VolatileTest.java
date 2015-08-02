package com.newidor.learn.concurrency.jmm;

import java.util.concurrent.atomic.AtomicReference;

import org.junit.Test;

public class VolatileTest {

	private static volatile Integer num1 = 0;
	private static AtomicReference<Integer> ar = new AtomicReference<Integer>(
			num1);

	@Test
	public void testAtomic() throws InterruptedException {
		for (int i = 0; i < 1000; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					for (int i = 0; i < 10000; i++)
						while (true) {
							Integer temp = ar.get();
							if (ar.compareAndSet(temp, temp + 1))
								break;
						}
				}
			}).start();
		}
		Thread.sleep(10000);
		System.out.println(ar.get()); // 10000000
	}

	@Test
	public void testVolatile() throws InterruptedException {
		for (int i = 0; i < 1000; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					for (int i = 0; i < 10000; i++) {
						num1 = num1++;
					}
				}
			}).start();
		}
		Thread.sleep(10000);
		System.out.println(num1); // something like 238981
	}

}
