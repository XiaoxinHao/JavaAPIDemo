package com.newidor.learn.concurrency.executor.future.demo1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Test {
	
	public static void main(String[] args) {
		Task task = new Task(1000);	//����һ������
		
		//��һ�ַ�ʽ
		//ExecutorService executor = Executors.newCachedThreadPool();
		//Future<Integer> futureTask = executor.submit(task); //��������Task�̳�Callable<Integer>����˷���ֵΪInteger
		//executor.shutdown();		
		
		//�ڶ��ַ�ʽ��ע�����ַ�ʽ�͵�һ�ַ�ʽЧ�������Ƶģ�ֻ����һ��ʹ�õ���ExecutorService��һ��ʹ�õ���Thread
        FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
        Thread thread = new Thread(futureTask);
        thread.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		
		System.out.println("���߳���ִ������");
		try {
			Integer sum = futureTask.get();
			System.out.println("task���н��" + sum);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		System.out.println("��������ִ�����");
		
	}
	
}
