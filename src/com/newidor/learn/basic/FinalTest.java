package com.newidor.learn.basic;

import org.junit.Test;

public class FinalTest {
	
	public final Point point; //���ò��ɱ䣬����ɱ� //�̲߳���ȫ
	public final String finalString; //���󲻿ɱ䣬���ò��ɱ� �̰߳�ȫ
	public String genString; //���󲻿ɱ䣨immutable�������ÿɱ�  �̰߳�ȫ
	
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
		
		//���¸�ֵ�������쳣
		//finalTest.point = new Point(); 
		
		//���¸�ֵ�������쳣
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
