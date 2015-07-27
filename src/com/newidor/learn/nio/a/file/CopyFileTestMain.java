package com.newidor.learn.nio.a.file;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CopyFileTestMain {
	
	/**
	 * ��68M�ļ���100�ε���Ϊ����
	 * copyFileByStreamTest�ܺ�ʱ��12,184
	 * copyFileByDirectByteBuffer�ܺ�ʱ��6,178
	 * copyFileByHeapByteBuffer�ܺ�ʱ��7,410
	 * copyFileByMappedByteBuffer�ܺ�ʱ��3,867
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
