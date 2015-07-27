package com.newidor.learn.serialization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

/**
 * 测试java的transient等关键字
 * transient:Java语言的关键字，用来表示一个域不是该对象串行化的一部分。
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
		//可以看到password没有被赋值，即在LoggingInfo序列化时pwd属性没有被序列化
		System.out.println(logInfo.toString());
	}

}
