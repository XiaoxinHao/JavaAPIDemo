package com.newidor.learn.concurrency.executor.future.demo1;

import java.util.concurrent.Callable;

/**
 * 本方法用于计算0到n-1之间所有数的总和
 * @author Administrator
 */
class Task implements Callable<Integer> {

	private int n;

	public Task(int n) {
		super();
		this.n = n;
	}

	public Integer call() throws Exception {
		System.out.println("子线程在进行计算");
		Thread.sleep(3000);
		int sum = 0;
		for (int i = 0; i < n; i++)
			sum += i;
		return sum;
	}

}