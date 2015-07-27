package com.newidor.learn.socket.a.client.sender;

import java.io.IOException;

import com.newidor.learn.socket.a.Commons;
import com.newidor.learn.socket.a.SocketWrapper;

import static com.newidor.learn.socket.a.Commons.*;

public class FileSender extends BFileSender {

	private byte charsetByte;
	
	protected int minLength = 3;

	public FileSender(String[] tokens) throws IOException {
		super(tokens);
		this.charsetByte = Commons
			.getCharsetByteByName(getCharset(tokens[2]));
	}
	
	private String getCharset(String token) {
		token = token.toLowerCase();
		if (token.startsWith(CHARSET_START)) {
			return token.substring(CHARSET_START.length());
		} else {
			throw new RuntimeException("�ַ������ֲ����Ϲ淶.");
		}
	}
	
	protected void sendCharset(SocketWrapper socketWrapper) throws IOException {
		socketWrapper.write(charsetByte);// �ַ���
	}
	
	@Override
	public byte getSendType() {
		return SEND_FILE;
	}
}
