package com.newidor.learn.jvm;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SimpleTest {
	
	@Test
	/**
	 * 默认Integer(-127,127)均在常量池中
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
	 * 如果在执行前String就已经存在，则为常量
	 * 如果new 或者临时生成，则在Heap中
	 * 较为特殊的是如果使用intern，则强制从常量区选择
	 * @throws InterruptedException
	 */
	public void testString(){
		String string_const = "test1"; //常量
		String string_const_split = "t" + "e" + "s" + "t" + 1; //常量
		String string_new = new String("test1");	//Heap
		String string_temp = getTempString();	//Heap
		String string_intern = getInternString();	//intern，常量
		
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
	 * -Xmn10m -Xmx20m -XX:+PrintGCDetails，JDK1.7：
	 * "I'm String,我是一个字符串，啦啦啦，啦啦啦" + i，这种形式的字符串在堆中
	 * 执行过程中，新生代不断进入老生代中，但最后依然会内存溢出：java.lang.OutOfMemoryError: Java heap space
	 * ParOldGen       total 10240K, used 10064K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
	 * object space 10240K, 98% used [0x00000000fec00000,0x00000000ff5d4220,0x00000000ff600000)
	 * @throws InterruptedException
	 */
	public void testHeapOOM() throws InterruptedException{
		List<String> stringList = new ArrayList<String>();
		int i = 0;
		while(true){
			stringList.add("I'm String,我是一个字符串，啦啦啦，啦啦啦" + i);
			//Thread.sleep(1);
		}
	}
	
	
	@Test
	/**
	 * -Xmn10m -Xmx20m -XX:PermSize=10m -XX:MaxPermSize=20m -XX:+PrintGCDetails
	 * 注：本示例需要在JDK1.6版本以下运行，原因是JDK1.7以后字符串不再放在PermGen区了
	 * !!通过JDK1.5、JDK1.7测试，均报Heap内存不足，与预期不符
	 */
	public void testPermGenOOM() throws InterruptedException{
		List<String> stringList = new ArrayList<String>();
		int i = 0;
		while(true){
			stringList.add(("I'm String,我是一个字符串，啦啦啦，啦啦啦" + i).intern());
			//Thread.sleep(1);
		}
	}
	
	

}
