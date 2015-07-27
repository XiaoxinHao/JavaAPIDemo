package com.newidor.learn.socket.a.client.sender;

import java.io.IOException;

import com.newidor.learn.socket.a.SocketWrapper;

public interface Sendable {
	
	public byte getSendType();

	public void sendContent(SocketWrapper socketWrapper) throws IOException;
}
