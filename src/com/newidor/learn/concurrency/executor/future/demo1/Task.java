package com.newidor.learn.concurrency.executor.future.demo1;

import java.util.concurrent.Callable;

/**
 * ���������ڼ���0��n-1֮�����������ܺ�
 * @author Administrator
 */
class Task implements Callable<Integer> {

	private int n;

	public Task(int n) {
		super();
		this.n = n;
	}

	public Integer call() throws Exception {
		System.out.println("���߳��ڽ��м���");
		Thread.sleep(3000);
		int sum = 0;
		for (int i = 0; i < n; i++)
			sum += i;
		return sum;
	}

}