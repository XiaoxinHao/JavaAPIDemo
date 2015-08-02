package com.newidor.learn.concurrency.notify.demo1;

public class LockDemo {
	public static void main(String[] args) {
		// System.out.println("lock");

		final OutTurn ot = new OutTurn();

		for(int i = 0 ; i < 10 ; i++){

			new Thread(new Runnable() {

				public void run() {
					// try {
					// Thread.sleep(10);
					// } catch (InterruptedException e) {
					// e.printStackTrace();
					// }
					for (int i = 0; i < 5; i++) {
						ot.sub();
					}
				}
			}).start();

			new Thread(new Runnable() {

				public void run() {
					// try {
					// Thread.sleep(10);
					// } catch (InterruptedException e) {
					// e.printStackTrace();
					// }
					for (int i = 0; i < 5; i++) {
						ot.main();
					}
				}
			}).start();
		}

	}
}