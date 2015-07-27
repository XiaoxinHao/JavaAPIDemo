package com.newidor.learn.nio.demo1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.junit.Test;

public class NioNonBlockingSelectorTest {
	Selector selector;
	private ByteBuffer receivebuffer = ByteBuffer.allocate(16);
	Map<SelectionKey, String> selectkeyResultMap = new HashMap<SelectionKey, String>();

	@Test
	public void testNioNonBlockingSelector() throws Exception {
		selector = Selector.open();
		SocketAddress address = new InetSocketAddress(10002);
		ServerSocketChannel channel = ServerSocketChannel.open();
		channel.socket().bind(address);
		channel.configureBlocking(false);
		channel.register(selector, SelectionKey.OP_ACCEPT);

		while (true) {
			//select为阻塞状态，eclipse中显状态为Stepping
			selector.select();
			//即使在执行如下代码时，如果来了2个TCP连接，在重新进入selector.select()时依然可以获取第一个请求-执行，然后selector.select()获取第二个请求-执行
			Iterator<SelectionKey> iterator = selector.selectedKeys()
					.iterator();
			while (iterator.hasNext()) {
				SelectionKey selectionKey = iterator.next();
				iterator.remove();
				handleKey(selectionKey);
			}
		}
	}

	private void handleKey(SelectionKey selectionKey) throws IOException {
		ServerSocketChannel server = null;
		SocketChannel channel = null;
		if (selectionKey.isAcceptable()) {
			server = (ServerSocketChannel) selectionKey.channel();
			channel = server.accept();
			System.out.println("客户端： "
					+ channel.socket().getRemoteSocketAddress().toString());
			channel.configureBlocking(false);
			channel.register(selector, SelectionKey.OP_READ);
		}
		if (selectionKey.isReadable()) {
			channel = (SocketChannel) selectionKey.channel();
			receivebuffer.clear();
			int count = channel.read(receivebuffer);
			if (count > 0) {
				String receiveText = new String(receivebuffer.array(), 0, count);
				// System.out.println("服务器端接受客户端数据--:" + receiveText);

				if (selectkeyResultMap.get(selectionKey) == null) {
					selectkeyResultMap.put(selectionKey, receiveText);
				} else {
					receiveText = selectkeyResultMap.get(selectionKey)
							+ receiveText;
					selectkeyResultMap.put(selectionKey, receiveText);
				}

				//receivebuffer初始化为1024，如果文件上传的大小大于1024，则会多次进入handleKey方法内部
				if (receivebuffer.hasRemaining()) {
					String resultText = selectkeyResultMap.get(selectionKey);
					//让处理线程如Action进行处理，此处模拟将其打印出来
					System.out.println("收到的结果：" + resultText.length() + "," + resultText);
					selectkeyResultMap.remove(selectionKey);
				}

				//如果没有注册，执行结果不会变化，但如果是一个长字符串，则每次都必须selector.select()后才能执行
				channel.register(selector, SelectionKey.OP_READ);
			}
		}
	}

}