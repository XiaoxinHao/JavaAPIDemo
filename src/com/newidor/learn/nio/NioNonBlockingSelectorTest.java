package com.newidor.learn.nio;

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
	private ByteBuffer receivebuffer = ByteBuffer.allocate(1024);
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
			selector.select();
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
		SocketChannel client = null;
		if (selectionKey.isAcceptable()) {
			server = (ServerSocketChannel) selectionKey.channel();
			client = server.accept();
			System.out.println("客户端： "
					+ client.socket().getRemoteSocketAddress().toString());
			client.configureBlocking(false);
			client.register(selector, SelectionKey.OP_READ);
		}
		if (selectionKey.isReadable()) {
			client = (SocketChannel) selectionKey.channel();
			receivebuffer.clear();
			int count = client.read(receivebuffer);
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

				client.register(selector, SelectionKey.OP_READ);
			}
		}
	}

}