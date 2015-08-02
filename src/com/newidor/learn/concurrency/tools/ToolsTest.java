package com.newidor.learn.concurrency.tools;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

/**
 * ������Դ��http://www.dewen.io/q/13713
 * @author Administrator
 */
public class ToolsTest {

	@Test
	/**
	 * ���Ա���
	 * @throws Exception
	 */
	public void CountDownLatch() throws Exception {
		final int N = 5;
		final CountDownLatch startSignal = new CountDownLatch(1);
		final CountDownLatch doneSignal = new CountDownLatch(N);
		final Random r = new Random();
		for (int i = 0; i < N; ++i)
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						startSignal.await();
						System.out.println(Thread.currentThread().getName()
								+ " starts!");
						Thread.sleep(r.nextInt(5000));
						System.out.println(Thread.currentThread().getName()
								+ " done!");
					} catch (Exception e) {
					}
					doneSignal.countDown();
				}
			}).start();
		System.out.println("All workers start!");
		startSignal.countDown();	//�����߳̿�ʼ����
		
		doneSignal.await();	//�ȴ������߳�ִ�����
		System.out.println("All workers done!");
	}

	@Test
	/**
	 * ����դ��
	 * @throws Exception
	 */
	public void testCyclicBarrier() throws Exception {
		final int N = 5;
		final AtomicInteger counter = new AtomicInteger(25);
		final CyclicBarrier barrier = new CyclicBarrier(N);
		final Random r = new Random();
		for (int i = 0; i < N; i++)
			new Thread(new Runnable() {
				@Override
				public void run() {
					while (counter.getAndDecrement() > 0) {
						System.out.println(Thread.currentThread().getName()
								+ " starts!");
						try {
							Thread.sleep(r.nextInt(2000));
							System.out.println(Thread.currentThread().getName()
									+ " done!");
							barrier.await();  //������ֱ������N����դ���������̼߳���ִ�У��ٴδ���N�����ٴο���դ��
						} catch (Exception e) {
						}
					}
				}
			}).start();
		do {
			Thread.sleep(5000);
		} while (counter.get() > 0);
	}

}
