package com.newidor.learn.socket.tcp.object;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class UserClient {
	
	public void start() {
		try {
			Socket socket = new Socket("127.0.0.1", 7777);
			// 建立输入流
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			User user = new User();
			user.setName("小明");
			user.setPassword("123456");
			// 输入对象, 一定要flush（）
			oos.writeObject(user);
			oos.flush();
			oos.close();
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new UserClient().start();
	}
}