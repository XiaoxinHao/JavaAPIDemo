package com.newidor.learn.jvm.clazz.staticz;

public class Main {

	// ��ô������̬�ڲ���ͷǾ�̬�ڲ����ʵ��
	public static void main(String args[]) {

		// ������̬�ڲ����ʵ��
		OuterClass.NestedStaticClass printer = new OuterClass.NestedStaticClass();
		// ������̬�ڲ����ʵ��
		OuterClass.NestedStaticClass printer2 = new OuterClass.NestedStaticClass();
		
		// ���þ�̬��ĳ�Ա����Ϊ��ֵͬ
		// ���Խ����printer��ʾa��printer2��ʾb
		printer.setMsg_nestedstatic("a");
		printer2.setMsg_nestedstatic("b");
		
		// ������̬�ڲ���ķǾ�̬����
		printer.printMessage();
		printer2.printMessage();
		

		// Ϊ�˴����Ǿ�̬�ڲ��࣬������Ҫ�ⲿ���ʵ��
		OuterClass outer = new OuterClass();
		OuterClass.InnerClass inner = outer.new InnerClass();

		// ���÷Ǿ�̬�ڲ���ķǾ�̬����
		inner.display();

		// ����Ҳ���Խ�����ϲ��裬һ���������ڲ���ʵ��
		OuterClass.InnerClass innerObject = new OuterClass().new InnerClass();

		// ͬ���������ڿ��Ե����ڲ��෽��
		innerObject.display();
	}

}
