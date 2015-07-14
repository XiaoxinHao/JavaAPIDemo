package com.newidor.learn.socket.tcp.object;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class UserServer {

	public void start() {
		try {
			ServerSocket serverSocket = new ServerSocket(7777);
			System.out.println("start to accept...");
			Socket socket = serverSocket.accept();

			// 建立输入流
			ObjectInputStream inputStream = new ObjectInputStream(
					new BufferedInputStream(socket.getInputStream()));
			Object obj = inputStream.readObject();
			if (obj != null) {
				User user = (User) obj;// 把接收到的对象转化为user
				System.out.println("user: " + user.getName());
				System.out.println("password: " + user.getPassword());
			}
			inputStream.close();
			socket.close();
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new UserServer().start();
	}

}
