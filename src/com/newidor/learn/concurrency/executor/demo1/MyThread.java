package com.newidor.learn.concurrency.executor.demo1;

public class MyThread extends Thread {
	
	private String localname;
	
	public MyThread(String localname){
		this.localname = localname;
	}

	@Override
	public void run() {
		
		for(int i = 2; i > 0 ; i--){
			System.out.println(localname + "ÕıÔÚÖ´ĞĞ£¬Ê£Óà:" + i + "Ãë");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
