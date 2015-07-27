package com.newidor.learn.multithread.lock;

import java.util.UUID;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

/**
 * 代码来源：http://blog.csdn.net/ghsau/article/details/7481142
 * 
 * @author Administrator
 *
 */
public class BoundedBuffer {
	final Lock lock = new ReentrantLock();// 锁对象
	final Condition notFull = lock.newCondition();// 写线程条件
	final Condition notEmpty = lock.newCondition();// 读线程条件

	final Object[] items = new Object[100];// 缓存队列
	int putptr/* 写索引 */, takeptr/* 读索引 */, count/* 队列中存在的数据个数 */;
	
	final BoundedBuffer boundedBuffer = new BoundedBuffer();

	@Test
	public void test() {

		Runnable runnable_write = new Runnable() {
			@Override
			public void run() {
				while (true) {
					String x = UUID.randomUUID().toString();
					try {
						boundedBuffer.put(x);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		};

		Runnable runnable_consume = new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						boundedBuffer.take();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};

		new Thread(runnable_write).start();
		new Thread(runnable_write).start();
		new Thread(runnable_consume).start();
		new Thread(runnable_consume).start();

		try {
			Thread.sleep(1000000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void put(Object x) throws InterruptedException {
		lock.lock();
		try {
			while (count == items.length) {
				// 如果队列满了
				notFull.await();// 阻塞写线程
			}
			items[putptr] = x;// 赋值
			if (++putptr == items.length) {
				putptr = 0;// 如果写索引写到队列的最后一个位置了，那么置为0
			}
			++count;// 个数++
			System.out.println("写位置:" + putptr + "，值:" + x);
			notEmpty.signal();// 唤醒读线程
		} finally {
			lock.unlock();
		}
	}

	public Object take() throws InterruptedException {
		lock.lock();
		try {
			while (count == 0)
				// 如果队列为空
				notEmpty.await();// 阻塞读线程
			Object x = items[takeptr];// 取值
			if (++takeptr == items.length)
				takeptr = 0;// 如果读索引读到队列的最后一个位置了，那么置为0
			--count;// 个数--
			System.out.println("读位置:" + takeptr + "，值:" + x);
			notFull.signal();// 唤醒写线程
			return x;
		} finally {
			lock.unlock();
		}
	}

}
