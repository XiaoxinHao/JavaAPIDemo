package com.newidor.learn.basic;

import org.junit.Test;

public class FinalTest {
	
	public final Point point; //引用不可变，对象可变 //线程不安全
	public final String finalString; //对象不可变，引用不可变 线程安全
	public String genString; //对象不可变（immutable），引用可变  线程安全
	
	public FinalTest(){
		point = new Point();
		point.x = 1;
		point.y = 2;
		
		finalString = "finalString";
		genString = "genString";
	}
	
	@Test
	public void testFinal(){
		FinalTest finalTest = new FinalTest();
		finalTest.point.x =3;
		finalTest.point.y =3;
		System.out.println(finalTest.point);
		
		//如下赋值将编译异常
		//finalTest.point = new Point(); 
		
		//如下赋值将编译异常
		//finalTest.finalString = "finalString2";
		
		finalTest.genString = "genString2";
		
	}
	
	
	
	class Point{
		int x ;
		int y;
		
		public String toString(){
			return "x:" + x + ";y:" + y;
		}
	}

}
