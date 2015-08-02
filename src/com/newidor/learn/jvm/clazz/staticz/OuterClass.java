package com.newidor.learn.jvm.clazz.staticz;

public class OuterClass {

	private static String msg = "GeeksForGeeks";

	// ��̬�ڲ���
	public static class NestedStaticClass {

		private String msg_nestedstatic;

		public void setMsg_nestedstatic(String msg_nestedstatic) {
			this.msg_nestedstatic = msg_nestedstatic;
		}

		// ��̬�ڲ���ֻ�ܷ����ⲿ��ľ�̬��Ա
		public void printMessage() {

			// ���Ž�msg�ĳɷǾ�̬�ģ��⽫���±������ ������̬����ֻ�ܻ�ȡ��̬����
			System.out.println("Message from nested static class: " + msg);
			System.out.println("Local Message from nested static class: "
					+ msg_nestedstatic);
		}
	}

	// �Ǿ�̬�ڲ���
	public class InnerClass {

		// �����Ǿ�̬�������ǷǾ�̬�����������ڷǾ�̬�ڲ����з���
		public void display() {
			System.out.println("Message from non-static nested class: " + msg);
		}
	}

}
