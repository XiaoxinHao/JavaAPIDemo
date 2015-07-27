package com.newidor.learn.nio.a.client;

import java.io.FileOutputStream;
import java.io.IOException;

import static com.newidor.learn.socket.a.Commons.*;

import com.newidor.learn.nio.a.file.FileUtils;
import com.newidor.learn.socket.a.SocketWrapper;

public class ClientMain {
	
	public static void main(String []args) throws IOException {
		for(int i = 0 ; i < 50 ; i ++) {
			final String num = String.valueOf(i);
			new Thread(String.valueOf(i)) {
				public void run() {
					SocketWrapper socketWrapper = null;
					FileOutputStream stream = null;
					try {
						stream = new FileOutputStream("E:\\Project\\JavaAPIDemo\\tmp\\javaA\\" + num +".zip");
						socketWrapper = new SocketWrapper("localhost" , 8888);
					
						println("已经连接上服务器端.....");
						socketWrapper.write("我是线程：" + num , DEFAULT_MESSAGE_CHARSET);
						byte []buffer = new byte[8192];
						int len = socketWrapper.read(buffer);
						while(len > 0) {
							println("我是线程：" + num + " 我接受到数据，长度为：" + len);
							stream.write(buffer , 0 , len);
							len = socketWrapper.read(buffer);
						}
						//System.in.read();
					}catch(Exception e) {
						e.printStackTrace();
					}finally {
						FileUtils.closeStream(stream , socketWrapper);
					}
				}
			}.start();
		}
		
	}
}
