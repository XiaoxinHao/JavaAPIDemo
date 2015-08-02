package com.newidor.learn.concurrency.notify.demo1;

class OutTurn {
	private boolean isSub = true;
	private int count = 0;

	public synchronized void sub() {
		try {
			System.out.println("sub start");
			while (!isSub) {
				this.wait();
			}
			System.out.println("sub ---- " + count);
			isSub = false;
			this.notifyAll();
			Thread.sleep(10);
		} catch (Exception e) {
			e.printStackTrace();
		}
		count++;

	}

	public synchronized void main() {
		try {
			System.out.println("main start");
			while (isSub) {
				this.wait();
			}
			System.out.println("main (((((((((((( " + count);
			isSub = true;
			//���ʹ��notify������ֻ�ܻ���һ�����п��ܻ�����
			this.notifyAll();
			Thread.sleep(10);
		} catch (Exception e) {
			e.printStackTrace();
		}
		count++;
	}
}
