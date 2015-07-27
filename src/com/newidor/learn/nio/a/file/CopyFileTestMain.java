package com.newidor.learn.nio.a.file;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CopyFileTestMain {
	
	/**
	 * 以68M文件，100次调用为例：
	 * copyFileByStreamTest总耗时：12,184
	 * copyFileByDirectByteBuffer总耗时：6,178
	 * copyFileByHeapByteBuffer总耗时：7,410
	 * copyFileByMappedByteBuffer总耗时，3,867
	 * @param args
	 * @throws IOException
	 */
	public static void main(String []args) throws IOException {
		long start = System.currentTimeMillis();
		for(int i = 0 ; i < 100; i++){
			copyFileByMappedByteBuffer();
		}
		System.out.println(System.currentTimeMillis() - start);
	}
	
	
	public static void copyFileByStreamTest() throws IOException {
		FileUtils.copyFile("E:/Project/JavaAPIDemo/tmp/javaA/a.zip", "E:/Project/JavaAPIDemo/tmp/javaA/a2.zip");
	}
	
	public static void copyFileByDirectByteBuffer() throws IOException {
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024 * 1024);
		FileUtils.copyFileByByteBuffer("E:/Project/JavaAPIDemo/tmp/javaA/a.zip", "E:/Project/JavaAPIDemo/tmp/javaA/a2.zip", byteBuffer, false);
	}
	
	public static void copyFileByHeapByteBuffer() throws IOException {
		ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 1024);
		FileUtils.copyFileByByteBuffer("E:/Project/JavaAPIDemo/tmp/javaA/a.zip", "E:/Project/JavaAPIDemo/tmp/javaA/a2.zip", byteBuffer, false);
	}
	
	public static void copyFileByMappedByteBuffer() throws IOException {
		FileUtils.copyFileByMappedByteBuffer("E:/Project/JavaAPIDemo/tmp/javaA/a.zip", "E:/Project/JavaAPIDemo/tmp/javaA/a2.zip");
	}

}
