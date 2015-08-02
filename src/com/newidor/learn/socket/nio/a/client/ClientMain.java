package com.newidor.learn.socket.nio.a.client;

import java.io.FileOutputStream;
import java.io.IOException;

import static com.newidor.learn.socket.a.Commons.*;

import com.newidor.learn.socket.a.SocketWrapper;
import com.newidor.learn.socket.nio.a.file.FileUtils;

public class ClientMain {

	public static void main(String[] args) throws IOException {
		
		for (int i = 0; i < 50; i++) {
			final String num = String.valueOf(i);
			new Thread(String.valueOf(i)) {
				public void run() {
					SocketWrapper socketWrapper = null;
					FileOutputStream stream = null;
					try {
						stream = new FileOutputStream(
								"E:\\Project\\JavaAPIDemo\\tmp\\a\\" + num
										+ ".zip");
						socketWrapper = new SocketWrapper("localhost", 8888);

						println("�Ѿ������Ϸ�������.....");
						socketWrapper.write("�����̣߳�" + num,
								DEFAULT_MESSAGE_CHARSET);
						byte[] buffer = new byte[8192];
						int len = socketWrapper.read(buffer);
						while (len > 0) {
							println("�����̣߳�" + num + " �ҽ��ܵ����ݣ�����Ϊ��" + len);
							stream.write(buffer, 0, len);
							len = socketWrapper.read(buffer);
						}
						// System.in.read();
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						FileUtils.closeStream(stream, socketWrapper);
					}
				}
			}.start();
		}

	}
}
