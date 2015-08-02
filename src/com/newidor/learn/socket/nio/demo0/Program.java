package com.newidor.learn.socket.nio.demo0;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.junit.Test;

public class Program {

	@Test
	public void testSlice() throws Exception {
		ByteBuffer buffer = ByteBuffer.allocate(10);

		// 缓冲区中的数据0-9
		for (int i = 0; i < buffer.capacity(); ++i) {
			buffer.put((byte) i);
		}

		// 创建子缓冲区
		buffer.position(3);
		buffer.limit(7);
		ByteBuffer slice = buffer.slice();

		// 改变子缓冲区的内容
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

		// 获取通道
		FileChannel fc = fin.getChannel();

		// 创建缓冲区
		ByteBuffer buffer = ByteBuffer.allocate(1024);

		// 读取数据到缓冲区
		fc.read(buffer);

		buffer.flip();

		while (buffer.remaining() > 0) {
			byte b = buffer.get();
			System.out.print(((char) b));
		}

		fin.close();
	}
	
}