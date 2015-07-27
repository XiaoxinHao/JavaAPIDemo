package com.newidor.learn.basic;

import org.junit.Test;

public class IntegerTest {
	
	@Test
	public void testInt(){
		int a = 19999999;
		System.out.println( a >>> 24);
		System.out.println( a >>> 16);
		System.out.println( a >>> 8);
		System.out.println( a >>> 0);
		
		System.out.println( (a >>> 24) & 0xFF);
		System.out.println( (a >>> 16) & 0xFF);
		System.out.println( (a >>> 8) & 0xFF);
		System.out.println( (a >>> 0) & 0xFF);
		
		int b = -1;
		String hex = Integer.toHexString(b);
		System.out.println(hex);
		
		int c = Integer.MAX_VALUE + 1;
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Integer.toHexString(Integer.MAX_VALUE));
		System.out.println(c);
		System.out.println(Integer.toHexString(c));
		
		byte byte_max = Byte.MAX_VALUE;
		byte byte_plus1 = (byte)(byte_max + 1);
		byte byte_min = Byte.MIN_VALUE;
		System.out.println(byte_max);
		System.out.println(byte_max + 1);
		System.out.println(byte_plus1);
		System.out.println(byte_min);
		
		
	}

}
