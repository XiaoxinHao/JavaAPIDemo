package com.newidor.learn.socket.a.client;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

import com.newidor.learn.socket.a.SocketWrapper;
import com.newidor.learn.socket.a.client.exceptions.DirectNotExistsException;
import com.newidor.learn.socket.a.client.exceptions.ExitException;
import com.newidor.learn.socket.a.client.exceptions.NoOptionException;
import com.newidor.learn.socket.a.client.processor.LineProcessor;

import static com.newidor.learn.socket.a.Commons.*;

public class SocketClientMain {

	public static void main(String []args) throws UnknownHostException, IOException {
		Scanner scanner = new Scanner(System.in);
		SocketWrapper socketWrapper = new SocketWrapper("localhost" , 8888);
		try {
			print("�Ѿ������Ϸ������ˣ����ڿ����������ݿ�ʼͨ����.....\n>");
			String line = scanner.nextLine();
			while(!"bye".equals(line)) {
				if(line != null) {
					try {
						LineProcessor processor = new LineProcessor(line);
						processor.sendContentBySocket(socketWrapper);
						socketWrapper.displayStatus();
					}catch(ExitException e) {
						break;//�˳�ϵͳ
					}catch(NoOptionException e) {
						/*û�����κβ���*/
					}catch(DirectNotExistsException e) {
						System.out.println(e.getMessage());
					}catch(RuntimeException e) {
						System.out.println(e.getMessage());
					}catch(FileNotFoundException e) {
						System.out.println(e.getMessage());
					}catch(SocketException e) {
						socketWrapper.displayStatus();
						System.out.println("Socket�쳣�� " + e.getMessage()  + "��������������Ͽ�����....");
						break;
					}catch(Exception e) {
						e.printStackTrace();
						System.out.println("�����Ϸ������Ͽ����ӣ�");
						break;
					}
				}
				print(">");
				line = scanner.nextLine();
			}
		}finally {
			socketWrapper.close();
		}
	}
}
