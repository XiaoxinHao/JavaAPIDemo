package com.newidor.learn.multithread.lock;

import java.util.UUID;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

/**
 * ������Դ��http://blog.csdn.net/ghsau/article/details/7481142
 * 
 * @author Administrator
 *
 */
public class BoundedBuffer {
	final Lock lock = new ReentrantLock();// ������
	final Condition notFull = lock.newCondition();// д�߳�����
	final Condition notEmpty = lock.newCondition();// ���߳�����

	final Object[] items = new Object[100];// �������
	int putptr/* д���� */, takeptr/* ������ */, count/* �����д��ڵ����ݸ��� */;
	
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
				// �����������
				notFull.await();// ����д�߳�
			}
			items[putptr] = x;// ��ֵ
			if (++putptr == items.length) {
				putptr = 0;// ���д����д�����е����һ��λ���ˣ���ô��Ϊ0
			}
			++count;// ����++
			System.out.println("дλ��:" + putptr + "��ֵ:" + x);
			notEmpty.signal();// ���Ѷ��߳�
		} finally {
			lock.unlock();
		}
	}

	public Object take() throws InterruptedException {
		lock.lock();
		try {
			while (count == 0)
				// �������Ϊ��
				notEmpty.await();// �������߳�
			Object x = items[takeptr];// ȡֵ
			if (++takeptr == items.length)
				takeptr = 0;// ����������������е����һ��λ���ˣ���ô��Ϊ0
			--count;// ����--
			System.out.println("��λ��:" + takeptr + "��ֵ:" + x);
			notFull.signal();// ����д�߳�
			return x;
		} finally {
			lock.unlock();
		}
	}

}
