package com.newidor.learn.multithread.lock;

import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 代码来源：http://www.cnblogs.com/dolphin0520/p/3920385.html
 */
public class Test3 {
	private int queueSize = 10;
	private PriorityQueue<Integer> queue = new PriorityQueue<Integer>(queueSize);
	//lock效果基本等同于synchronized关键字，用await()替换wait()，用signal()替换notify()，用signalAll()替换notifyAll()
	private Lock lock = new ReentrantLock();
	private Condition notFull = lock.newCondition();
	private Condition notEmpty = lock.newCondition();

	public static void main(String[] args) {
		Test3 test3 = new Test3();
		Producer producer = test3.new Producer();
		Consumer consumer = test3.new Consumer();

		producer.start();
		consumer.start();
	}

	class Consumer extends Thread {

		@Override
		public void run() {
			consume();
		}

		private void consume() {
			while (true) {
				lock.lock();
				try {
					while (queue.size() == 0) {
						try {
							System.out.println("队列空，等待数据");
							notEmpty.await();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					queue.poll(); // 每次移走队首元素
					notFull.signal();
					System.out.println("从队列取走一个元素，队列剩余" + queue.size() + "个元素");
				} finally {
					lock.unlock();
				}
			}
		}
	}

	class Producer extends Thread {

		@Override
		public void run() {
			produce();
		}

		private void produce() {
			while (true) {
				lock.lock();
				try {
					while (queue.size() == queueSize) {
						try {
							System.out.println("队列满，等待有空余空间");
							notFull.await();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					queue.offer(1); // 每次插入一个元素
					notEmpty.signal();
					System.out.println("向队列取中插入一个元素，队列剩余空间："
							+ (queueSize - queue.size()));
				} finally {
					lock.unlock();
				}
			}
		}
	}
}
