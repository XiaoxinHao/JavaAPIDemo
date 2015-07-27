package com.newidor.learn.jvm;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SimpleTest {
	
	@Test
	/**
	 * Ĭ��Integer(-127,127)���ڳ�������
	 */
	public void testInteger(){
		Integer a = 127;
		Integer b = 127;
		Integer c = 128;
		Integer d = 128;
		System.out.println(a == b); 
		System.out.println(c == d);
	}
	
	
	
	@Test
	/**
	 * �����ִ��ǰString���Ѿ����ڣ���Ϊ����
	 * ���new ������ʱ���ɣ�����Heap��
	 * ��Ϊ����������ʹ��intern����ǿ�ƴӳ�����ѡ��
	 * @throws InterruptedException
	 */
	public void testString(){
		String string_const = "test1"; //����
		String string_const_split = "t" + "e" + "s" + "t" + 1; //����
		String string_new = new String("test1");	//Heap
		String string_temp = getTempString();	//Heap
		String string_intern = getInternString();	//intern������
		
		System.out.println(string_const == string_const_split); //true
		System.out.println(string_const == string_new); //false
		System.out.println(string_const == string_temp); //false
		System.out.println(string_const == string_intern); //false
		
	}

	
	private String getTempString(){
		int i = 1;
		return "test" + i;
	}
	
	private String getInternString(){
		int i = 1;
		return ("test" + i).intern();
	}
	
	

	
	
	
	@Test
	/**
	 * -Xmn10m -Xmx20m -XX:+PrintGCDetails��JDK1.7��
	 * "I'm String,����һ���ַ�������������������" + i��������ʽ���ַ����ڶ���
	 * ִ�й����У����������Ͻ����������У��������Ȼ���ڴ������java.lang.OutOfMemoryError: Java heap space
	 * ParOldGen       total 10240K, used 10064K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
	 * object space 10240K, 98% used [0x00000000fec00000,0x00000000ff5d4220,0x00000000ff600000)
	 * @throws InterruptedException
	 */
	public void testHeapOOM() throws InterruptedException{
		List<String> stringList = new ArrayList<String>();
		int i = 0;
		while(true){
			stringList.add("I'm String,����һ���ַ�������������������" + i);
			//Thread.sleep(1);
		}
	}
	
	
	@Test
	/**
	 * -Xmn10m -Xmx20m -XX:PermSize=10m -XX:MaxPermSize=20m -XX:+PrintGCDetails
	 * ע����ʾ����Ҫ��JDK1.6�汾�������У�ԭ����JDK1.7�Ժ��ַ������ٷ���PermGen����
	 * !!ͨ��JDK1.5��JDK1.7���ԣ�����Heap�ڴ治�㣬��Ԥ�ڲ���
	 */
	public void testPermGenOOM() throws InterruptedException{
		List<String> stringList = new ArrayList<String>();
		int i = 0;
		while(true){
			stringList.add(("I'm String,����һ���ַ�������������������" + i).intern());
			//Thread.sleep(1);
		}
	}
	
	

}
