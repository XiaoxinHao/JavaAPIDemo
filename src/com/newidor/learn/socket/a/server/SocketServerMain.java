package com.newidor.learn.socket.a.server;

import static com.newidor.learn.socket.a.Commons.SERVER_SAVE_BASE_PATH;
import static com.newidor.learn.socket.a.Commons.logInfo;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

import com.newidor.learn.socket.a.SocketWrapper;

/**
 * ��ʾ����֧����JDK1.8������!
 * @author Administrator
 */
public class SocketServerMain {
	
	private final static List<Worker> workers = new ArrayList<Worker>();

	public static void main(String []args) throws IOException {
		initPath();
		ServerSocket serverSocket = new ServerSocket(8888);
		logInfo("�˿��Ѿ���Ϊ8888����ʼ׼����������.....");
		try {
			int index = 1;
			while(true) {
				SocketWrapper socketWrapper = new SocketWrapper(serverSocket.accept());
				workers.add(new Worker(socketWrapper , "socket_thread_" + index++));
			}
		}finally {
			serverSocket.close();
			interruptWorkers();
		}
	}
	
	private static void interruptWorkers() {
		for(Worker worker : workers) {
			worker.interrupt();
		}
	}
	
	private static void initPath() {
		File file = new File(SERVER_SAVE_BASE_PATH);
		if(!file.exists()) {
			boolean success = file.mkdirs();
			if(!success) 
				throw new RuntimeException("�޷�����Ŀ¼��" + SERVER_SAVE_BASE_PATH);
		}
	}
}
