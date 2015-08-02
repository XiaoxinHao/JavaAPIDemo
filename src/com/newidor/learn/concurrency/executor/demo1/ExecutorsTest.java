package com.newidor.learn.concurrency.executor.demo1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorsTest {

	public static void main(String[] args) {
		
		ExecutorService pool = getCachedThreadPool();
		
		// 创建实现了Runnable接口对象，Thread对象当然也实现了Runnable接口
		Thread t1 = new MyThread("Thread1");
		Thread t2 = new MyThread("Thread2");
		Thread t3 = new MyThread("Thread3");
		Thread t4 = new MyThread("Thread4");
		Thread t5 = new MyThread("Thread5");
		/** 线程名赋值没有用，Thread.currentThread().getName()结果均为“pool-1-thread-1”样式
		t1.setName("Thread1");
		t2.setName("Thread2");
		t3.setName("Thread3");
		t4.setName("Thread4");
		t5.setName("Thread5");
		**/
		// 将线程放入池中进行执行
		pool.execute(t1);
		pool.execute(t2);
		pool.execute(t3);
		pool.execute(t4);
		pool.execute(t5);
		// 关闭线程池
		pool.shutdown(); //如果不关闭，主线程一直不能结束
	}
	
	/** 创建一个可重用固定线程数的线程池
	 *  执行结果:
		Thread1正在执行，剩余:2秒
		Thread2正在执行，剩余:2秒
		Thread1正在执行，剩余:1秒
		Thread2正在执行，剩余:1秒
		Thread3正在执行，剩余:2秒
		Thread4正在执行，剩余:2秒
		Thread4正在执行，剩余:1秒
		Thread3正在执行，剩余:1秒
		Thread5正在执行，剩余:2秒
		Thread5正在执行，剩余:1秒
	**/
	private static ExecutorService getFixedThreadPool(){
		return Executors.newFixedThreadPool(2);
	}
	
	/** 
	 * 创建一个使用单个 worker 线程的 Executor，以无界队列方式来运行该线程。
	 * 执行结果：
	  	Thread1正在执行，剩余:2秒
		Thread1正在执行，剩余:1秒
		Thread2正在执行，剩余:2秒
		Thread2正在执行，剩余:1秒
		Thread3正在执行，剩余:2秒
		Thread3正在执行，剩余:1秒
		Thread4正在执行，剩余:2秒
		Thread4正在执行，剩余:1秒
		Thread5正在执行，剩余:2秒
		Thread5正在执行，剩余:1秒
	 */
	private static ExecutorService getSingleThreadExecutor(){
		return Executors.newSingleThreadExecutor();
	}
	
	/**
	 * 创建一个可根据需要创建新线程的线程池，但是在以前构造的线程可用时将重用它们。 
	 * 执行结果：
		Thread1正在执行，剩余:2秒
		Thread2正在执行，剩余:2秒
		Thread3正在执行，剩余:2秒
		Thread4正在执行，剩余:2秒
		Thread5正在执行，剩余:2秒
		Thread4正在执行，剩余:1秒
		Thread2正在执行，剩余:1秒
		Thread3正在执行，剩余:1秒
		Thread1正在执行，剩余:1秒
		Thread5正在执行，剩余:1秒
	 */
	private static ExecutorService getCachedThreadPool(){
		return Executors.newCachedThreadPool();
	}

}
