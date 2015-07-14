package com.newidor.learn.javavm;

import org.junit.Test;

public class SimpleTest {
	
	@Test
	public void testInteger(){
		Integer a = 1;
		Integer b = 1;
		Integer c = 2000;
		Integer d = 2000;
		System.out.println(a == b);
		System.out.println(c == d);
		
	}

}
