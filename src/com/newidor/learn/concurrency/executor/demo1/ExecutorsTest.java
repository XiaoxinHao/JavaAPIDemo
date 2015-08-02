package com.newidor.learn.concurrency.executor.demo1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorsTest {

	public static void main(String[] args) {
		
		ExecutorService pool = getCachedThreadPool();
		
		// ����ʵ����Runnable�ӿڶ���Thread����ȻҲʵ����Runnable�ӿ�
		Thread t1 = new MyThread("Thread1");
		Thread t2 = new MyThread("Thread2");
		Thread t3 = new MyThread("Thread3");
		Thread t4 = new MyThread("Thread4");
		Thread t5 = new MyThread("Thread5");
		/** �߳�����ֵû���ã�Thread.currentThread().getName()�����Ϊ��pool-1-thread-1����ʽ
		t1.setName("Thread1");
		t2.setName("Thread2");
		t3.setName("Thread3");
		t4.setName("Thread4");
		t5.setName("Thread5");
		**/
		// ���̷߳�����н���ִ��
		pool.execute(t1);
		pool.execute(t2);
		pool.execute(t3);
		pool.execute(t4);
		pool.execute(t5);
		// �ر��̳߳�
		pool.shutdown(); //������رգ����߳�һֱ���ܽ���
	}
	
	/** ����һ�������ù̶��߳������̳߳�
	 *  ִ�н��:
		Thread1����ִ�У�ʣ��:2��
		Thread2����ִ�У�ʣ��:2��
		Thread1����ִ�У�ʣ��:1��
		Thread2����ִ�У�ʣ��:1��
		Thread3����ִ�У�ʣ��:2��
		Thread4����ִ�У�ʣ��:2��
		Thread4����ִ�У�ʣ��:1��
		Thread3����ִ�У�ʣ��:1��
		Thread5����ִ�У�ʣ��:2��
		Thread5����ִ�У�ʣ��:1��
	**/
	private static ExecutorService getFixedThreadPool(){
		return Executors.newFixedThreadPool(2);
	}
	
	/** 
	 * ����һ��ʹ�õ��� worker �̵߳� Executor�����޽���з�ʽ�����и��̡߳�
	 * ִ�н����
	  	Thread1����ִ�У�ʣ��:2��
		Thread1����ִ�У�ʣ��:1��
		Thread2����ִ�У�ʣ��:2��
		Thread2����ִ�У�ʣ��:1��
		Thread3����ִ�У�ʣ��:2��
		Thread3����ִ�У�ʣ��:1��
		Thread4����ִ�У�ʣ��:2��
		Thread4����ִ�У�ʣ��:1��
		Thread5����ִ�У�ʣ��:2��
		Thread5����ִ�У�ʣ��:1��
	 */
	private static ExecutorService getSingleThreadExecutor(){
		return Executors.newSingleThreadExecutor();
	}
	
	/**
	 * ����һ���ɸ�����Ҫ�������̵߳��̳߳أ���������ǰ������߳̿���ʱ���������ǡ� 
	 * ִ�н����
		Thread1����ִ�У�ʣ��:2��
		Thread2����ִ�У�ʣ��:2��
		Thread3����ִ�У�ʣ��:2��
		Thread4����ִ�У�ʣ��:2��
		Thread5����ִ�У�ʣ��:2��
		Thread4����ִ�У�ʣ��:1��
		Thread2����ִ�У�ʣ��:1��
		Thread3����ִ�У�ʣ��:1��
		Thread1����ִ�У�ʣ��:1��
		Thread5����ִ�У�ʣ��:1��
	 */
	private static ExecutorService getCachedThreadPool(){
		return Executors.newCachedThreadPool();
	}

}
