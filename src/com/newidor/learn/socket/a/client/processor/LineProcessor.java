package com.newidor.learn.socket.a.client.processor;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import com.newidor.learn.socket.a.SocketWrapper;
import com.newidor.learn.socket.a.client.exceptions.NoOptionException;
import com.newidor.learn.socket.a.client.sender.Sendable;

import static com.newidor.learn.socket.a.Commons.*;

/**
 * ��������ĵ�һ�����������ɲ�ͬSender����
 * @author Administrator
 *
 */
public class LineProcessor {
	
	private String []tokens;
	
	private Sendable sendable;
	
	public LineProcessor(String line) throws Exception {
		line = preLine(line).trim();
		if(line.trim().length() == 0) {//û���κβ���
			throw new NoOptionException();
		}
		tokens = line.trim().split("\\s+");
		String firstToken = tokens[0];
		Class <?>clazz = findSendableClassByOrder(firstToken);
		try {
			sendable = (Sendable)clazz.getConstructor(String[].class)
				.newInstance(new Object[] {tokens});
		}catch(InvocationTargetException e) {
			throw (Exception)e.getCause();
		}
	}
	
	public void sendContentBySocket(SocketWrapper socketWrapper) throws IOException {
		if(sendable != null && sendable.getSendType() > 0) {
			socketWrapper.write(sendable.getSendType());//��������
			sendable.sendContent(socketWrapper);
		}
	}
	
	private String preLine(String line) {
		if(line == null) return "";
		if(line.startsWith(">")) return line.substring(1);
		return line;
	}
}
