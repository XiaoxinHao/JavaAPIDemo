package com.newidor.learn.socket.a.client.exceptions;

public class DownloadNotExistsFileException extends RuntimeException {

	private static final long serialVersionUID = 2969567696674112542L;
	
	public DownloadNotExistsFileException(String path) {
		super("�޷������ļ���" + path + "����Ϊ�������˲���������ļ�....");
	}

}
