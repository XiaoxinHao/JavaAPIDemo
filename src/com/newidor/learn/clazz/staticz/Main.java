package com.newidor.learn.clazz.staticz;

public class Main {

	// 怎么创建静态内部类和非静态内部类的实例
	public static void main(String args[]) {

		// 创建静态内部类的实例
		OuterClass.NestedStaticClass printer = new OuterClass.NestedStaticClass();
		// 创建静态内部类的实例
		OuterClass.NestedStaticClass printer2 = new OuterClass.NestedStaticClass();
		
		// 设置静态类的成员变量为不同值
		// 测试结果：printer显示a，printer2显示b
		printer.setMsg_nestedstatic("a");
		printer2.setMsg_nestedstatic("b");
		
		// 创建静态内部类的非静态方法
		printer.printMessage();
		printer2.printMessage();
		

		// 为了创建非静态内部类，我们需要外部类的实例
		OuterClass outer = new OuterClass();
		OuterClass.InnerClass inner = outer.new InnerClass();

		// 调用非静态内部类的非静态方法
		inner.display();

		// 我们也可以结合以上步骤，一步创建的内部类实例
		OuterClass.InnerClass innerObject = new OuterClass().new InnerClass();

		// 同样我们现在可以调用内部类方法
		innerObject.display();
	}

}
