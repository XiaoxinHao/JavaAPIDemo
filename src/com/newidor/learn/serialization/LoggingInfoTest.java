package com.newidor.learn.serialization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

/**
 * ����java��transient�ȹؼ���
 * transient:Java���ԵĹؼ��֣�������ʾһ�����Ǹö����л���һ���֡�
 * @author Administrator
 *
 */
public class LoggingInfoTest {

	@Test
	public void testTransient1() throws FileNotFoundException, IOException {
		LoggingInfo logInfo = new LoggingInfo("MIKE", "MECHANICS");
		System.out.println(logInfo.toString());
		ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(
				"tmp/logInfo.out"));
		o.writeObject(logInfo);
		o.close();
	}

	@Test
	public void testTransient2() throws FileNotFoundException, IOException,
			ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(
				"tmp/logInfo.out"));
		LoggingInfo logInfo = (LoggingInfo) in.readObject();
		in.close();
		//���Կ���passwordû�б���ֵ������LoggingInfo���л�ʱpwd����û�б����л�
		System.out.println(logInfo.toString());
	}

}
