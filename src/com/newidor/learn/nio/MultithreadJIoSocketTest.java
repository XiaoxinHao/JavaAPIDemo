package com.newidor.learn.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

import org.junit.Test;

public class MultithreadJIoSocketTest {

	@Test
	public void testMultithreadJIoSocket() throws Exception {
		ServerSocket serverSocket = new ServerSocket(10002);
		Thread thread = new Thread(new Accptor(serverSocket));
		thread.start();

		Scanner scanner = new Scanner(System.in);
		scanner.next();
		scanner.close();
	}

	public class Accptor implements Runnable {
		private ServerSocket serverSocket;

		public Accptor(ServerSocket serverSocket) {
			this.serverSocket = serverSocket;
		}

		public void run() {
			while (true) {
				Socket socket = null;
				try {
					//Listens for a connection to be made to this socket and accepts it. The method blocks until a connection is made. 
					//Returns:the new Socket
					socket = serverSocket.accept();
					if (socket != null) {
						System.out.println("收到了socket："
								+ socket.getRemoteSocketAddress().toString());
						Thread thread = new Thread(new Processor(socket));
						thread.start();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public class Processor implements Runnable {
		private Socket socket;

		public Processor(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			BufferedReader in;
			PrintWriter os;
			String readLine;
			try {
				in = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				os = new PrintWriter(socket.getOutputStream());
			} catch (IOException e1) {
				e1.printStackTrace();
				throw new RuntimeException("失败");
			}

			try {
				while (true) {
					readLine = in.readLine();
					System.out.println("收到消息" + readLine);
					
					//模拟期间处理需要5s时间
					Thread.sleep(50);
					
					os.write("Hello" + readLine);
					os.flush();
					if ("end".equals(readLine)) {
						os.close();
						in.close();
						break;
					}
					
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (SocketException se) {
				System.out.println("客户端断开连接");
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					os.close();
					in.close();
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
