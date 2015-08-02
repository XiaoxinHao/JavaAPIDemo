package com.newidor.learn.concurrency.executor.future.demo1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Test {
	
	public static void main(String[] args) {
		Task task = new Task(1000);	//声明一个任务
		
		//第一种方式
		//ExecutorService executor = Executors.newCachedThreadPool();
		//Future<Integer> futureTask = executor.submit(task); //开启任务，Task继承Callable<Integer>，因此返回值为Integer
		//executor.shutdown();		
		
		//第二种方式，注意这种方式和第一种方式效果是类似的，只不过一个使用的是ExecutorService，一个使用的是Thread
        FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
        Thread thread = new Thread(futureTask);
        thread.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		
		System.out.println("主线程在执行任务");
		try {
			Integer sum = futureTask.get();
			System.out.println("task运行结果" + sum);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		System.out.println("所有任务执行完毕");
		
	}
	
}
