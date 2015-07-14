package com.newidor.learn.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import org.junit.Test;

public class JIoSocketTest {

	@Test
	public void testJIoSocket() throws Exception {
		ServerSocket serverSocket = new ServerSocket(10002);
		Socket socket = null;
		try {
			while (true) {
				socket = serverSocket.accept();
				System.out.println("socket���ӣ�"
						+ socket.getRemoteSocketAddress().toString());
				BufferedReader in = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				while (true) {
					String readLine = in.readLine();
					System.out.println("�յ���Ϣ" + readLine);
					if ("end".equals(readLine)) {
						break;
					}
					// �ͻ��˶Ͽ�����
					//socket.sendUrgentData(0xFF);
				}
			}
		} catch (SocketException se) {
			System.out.println("�ͻ��˶Ͽ�����");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("socket�رգ�"
					+ socket.getRemoteSocketAddress().toString());
			socket.close();
		}
	}
}
