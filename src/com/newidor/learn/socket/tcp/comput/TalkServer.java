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
				// ����һ��ServerSocket�ڶ˿�4700�����ͻ�����
				server = new ServerSocket(4700);
			} catch (Exception e) {
				e.printStackTrace();
			}

			Socket socket = null;
			try {
				// ʹ��accept()�����ȴ��ͻ������пͻ�
				// �����������һ��Socket���󣬲�����ִ��
				socket = server.accept();
			} catch (Exception e) {
				e.printStackTrace();
			}

			BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			// ��Socket����õ�����������������Ӧ��BufferedReader����
			PrintWriter os = new PrintWriter(socket.getOutputStream());
			// ��Socket����õ��������������PrintWriter����
			
			// �ӱ�׼�������һ�ַ���
			String inputString = is.readLine();
			// ������ַ���Ϊ "bye"����ֹͣѭ��
			while (!inputString.equals("bye")) { 
				System.out.println("Input String: " + inputString);
				// ��ͻ���������ַ���
				os.println(compute(inputString)); 
				// ˢ���������ʹClient�����յ����ַ���
				os.flush(); 
				// ��ϵͳ��׼�������һ�ַ���
				inputString = is.readLine(); 
			} // ����ѭ��
			// �ر�Socket�����
			os.close(); 
			// �ر�Socket������
			is.close(); 
			// �ر�Socket
			socket.close(); 
			// �ر�ServerSocket
			server.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	public static String compute(String inputString){
		return "Hello, " + inputString;
	}
	
	
	

}
