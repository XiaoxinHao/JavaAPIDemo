package com.newidor.learn.nio.demo0;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.junit.Test;

public class Program {

	@Test
	public void testSlice() throws Exception {
		ByteBuffer buffer = ByteBuffer.allocate(10);

		// �������е�����0-9
		for (int i = 0; i < buffer.capacity(); ++i) {
			buffer.put((byte) i);
		}

		// �����ӻ�����
		buffer.position(3);
		buffer.limit(7);
		ByteBuffer slice = buffer.slice();

		// �ı��ӻ�����������
		for (int i = 0; i < slice.capacity(); ++i) {
			byte b = slice.get(i);
			b *= 10;
			slice.put(i, b);
		}

		buffer.position(0);
		buffer.limit(buffer.capacity());

		while (buffer.remaining() > 0) {
			System.out.println(buffer.get());
		}
	}

	@Test
	public void testFileChannel() throws Exception {
		File file = new File(Program.class.getResource("/nio.txt").getFile());
		FileInputStream fin = new FileInputStream(file);

		// ��ȡͨ��
		FileChannel fc = fin.getChannel();

		// ����������
		ByteBuffer buffer = ByteBuffer.allocate(1024);

		// ��ȡ���ݵ�������
		fc.read(buffer);

		buffer.flip();

		while (buffer.remaining() > 0) {
			byte b = buffer.get();
			System.out.print(((char) b));
		}

		fin.close();
	}
	
}