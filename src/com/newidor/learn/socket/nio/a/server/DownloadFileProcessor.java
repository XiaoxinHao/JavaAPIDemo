package com.newidor.learn.socket.nio.a.server;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class DownloadFileProcessor implements Closeable {

	private final static String FILE_PTH = "E:\\Project\\JavaAPIDemo\\tmp\\a\\a.zip";
	
	private FileChannel fileChannel;
	
	protected ByteBuffer fileByteBuffer = ByteBuffer.allocate(8192); //缓存从filepath读取的文件流，不断clear、flip，该数据流最终由channel发送给客户端
	
	public DownloadFileProcessor() throws FileNotFoundException {
		fileChannel = new FileInputStream(FILE_PTH).getChannel();
	}
	
	public int read() throws IOException {
		fileByteBuffer.clear();	
		int count = fileChannel.read(fileByteBuffer);
		fileByteBuffer.flip();
		return count;
	}

	@Override
	public void close() throws IOException {
		fileChannel.close();
	}

	public ByteBuffer getFileByteBuffer() {
		return fileByteBuffer;
	}
}
