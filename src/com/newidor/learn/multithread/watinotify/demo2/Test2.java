package com.newidor.learn.multithread.watinotify.demo2;

import java.util.PriorityQueue;


/**
 * 代码来源：http://www.cnblogs.com/dolphin0520/p/3920385.html
 */
public class Test2 {
	private int queueSize = 10;
	private PriorityQueue<Integer> queue = new PriorityQueue<Integer>(queueSize);

	public static void main(String[] args) {
		Test2 test2 = new Test2();
		Producer producer = test2.new Producer();
		Consumer consumer = test2.new Consumer();

		consumer.start();
		/** 2.测试如果没有producer唤醒情况下，consumer是否可以继续执行 **/
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
				/** 0、queue.notify()必须放到锁中，否则此处会抛出异常 **/
				//queue.notify();
				synchronized (queue) {  //获得锁
					while (queue.size() == 0) {
						try {
							System.out.println("队列空，等待数据");
							queue.wait();  //释放锁，等到notify被唤醒后且notify所在的其他线程同步块内方法执行完毕后，立即争抢锁，如果争抢到，则不需要考虑queue.size() == 0是否满足，可以继续执行
						} catch (InterruptedException e) {
							e.printStackTrace();
							queue.notify();
						}
					}
					queue.poll(); // 每次移走队首元素
					System.out.println("从队列取走一个元素，队列剩余" + queue.size() + "个元素");
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
					/** 1、即使queue.notify()放在程序开始位置，效果也基本是一样的，原因是真正的唤醒时在解除锁定之后  **/
					//queue.notify();
					
					/** 2、如果queue.notify();没有执行queue.notify();而consumer处于wait()状态，则永远不会被唤醒，执行结果如下：
					队列空，等待数据
					向队列取中插入一个元素，队列剩余空间：9
					向队列取中插入一个元素，队列剩余空间：8
					向队列取中插入一个元素，队列剩余空间：7
					向队列取中插入一个元素，队列剩余空间：6
					向队列取中插入一个元素，队列剩余空间：5
					向队列取中插入一个元素，队列剩余空间：4
					向队列取中插入一个元素，队列剩余空间：3
					向队列取中插入一个元素，队列剩余空间：2
					向队列取中插入一个元素，队列剩余空间：1
					向队列取中插入一个元素，队列剩余空间：0
					队列满，等待有空余空间
					**/
					//queue.notify();
					while (queue.size() == queueSize) {
						try {
							System.out.println("队列满，等待有空余空间");
							queue.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
							queue.notify();
						}
					}
					queue.offer(1); // 每次插入一个元素
					System.out.println("向队列取中插入一个元素，队列剩余空间："
							+ (queueSize - queue.size()));
					queue.notify();
				}
			}
		}
	}

}
