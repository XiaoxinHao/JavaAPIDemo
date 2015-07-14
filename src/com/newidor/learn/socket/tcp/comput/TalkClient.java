package com.newidor.learn.socket.tcp.comput;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TalkClient {

	public static void main(String args[]) {
		try {
			// �򱾻���4700�˿ڷ����ͻ�����
			Socket socket = new Socket("127.0.0.1", 4700);
			// ��Socket����õ�����������������Ӧ��BufferedReader����
			BufferedReader is = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			// ��Socket����õ��������������PrintWriter����
			PrintWriter os = new PrintWriter(socket.getOutputStream());

			// ��ϵͳ��׼�����豸����BufferedReader����
			BufferedReader sin = new BufferedReader(new InputStreamReader(
					System.in));

			while (true) {
				String readline = sin.readLine();
				// ����ϵͳ��׼���������ַ��������Server
				os.println(readline);
				// ˢ���������ʹServer�����յ����ַ���
				os.flush();
				// ��ϵͳ��׼����ϴ�ӡ������ַ���
				System.out.println(is.readLine());
				if (readline.equals("bye"))
					break;
			} // ����ѭ��
				// �ر�Socket�����
			os.close();
			// �ر�Socket������
			is.close();
			// �ر�Socket
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
