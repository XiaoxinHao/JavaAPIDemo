package com.newidor.learn.socket.tcp.comput;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TalkServer {

	
	public static void main(String args[]) {
		try {

			ServerSocket server = null;
			try {
				// 创建一个ServerSocket在端口4700监听客户请求
				server = new ServerSocket(4700);
			} catch (Exception e) {
				e.printStackTrace();
			}

			Socket socket = null;
			try {
				// 使用accept()阻塞等待客户请求，有客户
				// 请求到来则产生一个Socket对象，并继续执行
				socket = server.accept();
			} catch (Exception e) {
				e.printStackTrace();
			}

			BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			// 由Socket对象得到输入流，并构造相应的BufferedReader对象
			PrintWriter os = new PrintWriter(socket.getOutputStream());
			// 由Socket对象得到输出流，并构造PrintWriter对象
			
			// 从标准输入读入一字符串
			String inputString = is.readLine();
			// 如果该字符串为 "bye"，则停止循环
			while (!inputString.equals("bye")) { 
				System.out.println("Input String: " + inputString);
				// 向客户端输出该字符串
				os.println(compute(inputString)); 
				// 刷新输出流，使Client马上收到该字符串
				os.flush(); 
				// 从系统标准输入读入一字符串
				inputString = is.readLine(); 
			} // 继续循环
			// 关闭Socket输出流
			os.close(); 
			// 关闭Socket输入流
			is.close(); 
			// 关闭Socket
			socket.close(); 
			// 关闭ServerSocket
			server.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	public static String compute(String inputString){
		return "Hello, " + inputString;
	}
	
	
	

}
