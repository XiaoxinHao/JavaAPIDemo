package com.newidor.learn.concurrency.locks;

import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * ������Դ��http://www.cnblogs.com/dolphin0520/p/3920385.html
 * ����newConditionʵ���˶�д��
 */
public class ConditionTest {
	private int queueSize = 10;
	private PriorityQueue<Integer> queue = new PriorityQueue<Integer>(queueSize);
	//lockЧ��������ͬ��synchronized�ؼ��֣���await()�滻wait()����signal()�滻notify()����signalAll()�滻notifyAll()
	private Lock lock = new ReentrantLock();
	private Condition notFull = lock.newCondition();
	private Condition notEmpty = lock.newCondition();

	public static void main(String[] args) {
		ConditionTest test3 = new ConditionTest();
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
							System.out.println("���пգ��ȴ�����");
							notEmpty.await();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					queue.poll(); // ÿ�����߶���Ԫ��
					notFull.signal();
					System.out.println("�Ӷ���ȡ��һ��Ԫ�أ�����ʣ��" + queue.size() + "��Ԫ��");
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
							System.out.println("���������ȴ��п���ռ�");
							notFull.await();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					queue.offer(1); // ÿ�β���һ��Ԫ��
					notEmpty.signal();
					System.out.println("�����ȡ�в���һ��Ԫ�أ�����ʣ��ռ䣺"
							+ (queueSize - queue.size()));
				} finally {
					lock.unlock();
				}
			}
		}
	}
}
