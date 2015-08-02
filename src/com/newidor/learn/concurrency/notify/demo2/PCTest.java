package com.newidor.learn.concurrency.notify.demo2;

import java.util.PriorityQueue;


/**
 * ������Դ��http://www.cnblogs.com/dolphin0520/p/3920385.html
 */
public class PCTest {
	private int queueSize = 10;
	private PriorityQueue<Integer> queue = new PriorityQueue<Integer>(queueSize);

	public static void main(String[] args) {
		PCTest pcTest = new PCTest();
		Producer producer = pcTest.new Producer();
		Consumer consumer = pcTest.new Consumer();

		consumer.start();
		/** 2.�������û��producer��������£�consumer�Ƿ���Լ���ִ�� **/
		/**
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		**/
		
		producer.start();
		
		
		
	}

	class Consumer extends Thread {

		@Override
		public void run() {
			consume();
		}

		private void consume() {
			while (true) {
				/** 0��queue.notify()����ŵ����У�����˴����׳��쳣 **/
				//queue.notify();
				synchronized (queue) {  //�����
					while (queue.size() == 0) {
						try {
							System.out.println("���пգ��ȴ�����");
							queue.wait();  //�ͷ������ȵ�notify�����Ѻ���notify���ڵ������߳�ͬ�����ڷ���ִ����Ϻ����������������������������Ҫ����queue.size() == 0�Ƿ����㣬���Լ���ִ��
						} catch (InterruptedException e) {
							e.printStackTrace();
							queue.notify();
						}
					}
					queue.poll(); // ÿ�����߶���Ԫ��
					System.out.println("�Ӷ���ȡ��һ��Ԫ�أ�����ʣ��" + queue.size() + "��Ԫ��");
					queue.notify();
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
				synchronized (queue) {
					/** 1����ʹqueue.notify()���ڳ���ʼλ�ã�Ч��Ҳ������һ���ģ�ԭ���������Ļ���ʱ�ڽ������֮��  **/
					//queue.notify();
					
					/** 2�����queue.notify();û��ִ��queue.notify();��consumer����wait()״̬������Զ���ᱻ���ѣ�ִ�н�����£�
					���пգ��ȴ�����
					�����ȡ�в���һ��Ԫ�أ�����ʣ��ռ䣺9
					�����ȡ�в���һ��Ԫ�أ�����ʣ��ռ䣺8
					�����ȡ�в���һ��Ԫ�أ�����ʣ��ռ䣺7
					�����ȡ�в���һ��Ԫ�أ�����ʣ��ռ䣺6
					�����ȡ�в���һ��Ԫ�أ�����ʣ��ռ䣺5
					�����ȡ�в���һ��Ԫ�أ�����ʣ��ռ䣺4
					�����ȡ�в���һ��Ԫ�أ�����ʣ��ռ䣺3
					�����ȡ�в���һ��Ԫ�أ�����ʣ��ռ䣺2
					�����ȡ�в���һ��Ԫ�أ�����ʣ��ռ䣺1
					�����ȡ�в���һ��Ԫ�أ�����ʣ��ռ䣺0
					���������ȴ��п���ռ�
					**/
					//queue.notify();
					while (queue.size() == queueSize) {
						try {
							System.out.println("���������ȴ��п���ռ�");
							queue.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
							queue.notify();
						}
					}
					queue.offer(1); // ÿ�β���һ��Ԫ��
					System.out.println("�����ȡ�в���һ��Ԫ�أ�����ʣ��ռ䣺"
							+ (queueSize - queue.size()));
					queue.notify();
				}
			}
		}
	}

}
