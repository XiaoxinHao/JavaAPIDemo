package com.newidor.learn.socket.tcp.comput;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TalkClient {

	public static void main(String args[]) {
		try {
			// 向本机的4700端口发出客户请求
			Socket socket = new Socket("127.0.0.1", 4700);
			// 由Socket对象得到输入流，并构造相应的BufferedReader对象
			BufferedReader is = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			// 由Socket对象得到输出流，并构造PrintWriter对象
			PrintWriter os = new PrintWriter(socket.getOutputStream());

			// 由系统标准输入设备构造BufferedReader对象
			BufferedReader sin = new BufferedReader(new InputStreamReader(
					System.in));

			while (true) {
				String readline = sin.readLine();
				// 将从系统标准输入读入的字符串输出到Server
				os.println(readline);
				// 刷新输出流，使Server马上收到该字符串
				os.flush();
				// 在系统标准输出上打印读入的字符串
				System.out.println(is.readLine());
				if (readline.equals("bye"))
					break;
			} // 继续循环
				// 关闭Socket输出流
			os.close();
			// 关闭Socket输入流
			is.close();
			// 关闭Socket
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
