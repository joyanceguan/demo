package com.joyance.demo.remote;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NIOServer {
	
	ServerSocketChannel serverChannel;
	Selector selector;
	SocketChannel clientChannel;
	ExecutorService executorService;
	
	
	public NIOServer() throws Exception {
		executorService = Executors.newFixedThreadPool(10);
		serverChannel = ServerSocketChannel.open();
		serverChannel.configureBlocking(false);
		serverChannel.bind(new InetSocketAddress("localhost", 8081));
	    selector = Selector.open();
	    serverChannel.register(selector, SelectionKey.OP_ACCEPT);
		System.out.println("服务器启动....");
		clientHandle();
	}
	
	public void clientHandle() throws IOException{
		int keySelect = 0;
		while((keySelect = selector.select()) > 0){
			System.out.println("2========"+keySelect);
			Set<SelectionKey> set = selector.selectedKeys();
			Iterator<SelectionKey> iterator = set.iterator();
			while(iterator.hasNext()){
				SelectionKey selectionKey = iterator.next();
				System.out.println("3========"+selectionKey.toString());
				if(selectionKey.isAcceptable()){
					System.out.println("4========");
					clientChannel = serverChannel.accept();
					executorService.submit(new SocketClientChannel(clientChannel));
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		NIOServer server = new NIOServer();
	}
	
	class SocketClientChannel implements Runnable{
		
		private SocketChannel clientChannle;
		private boolean flag = true;//循环处理的标记
		
		public SocketClientChannel(SocketChannel socketChannel){
			this.clientChannle = socketChannel;
		}
		
		public void run() {
			ByteBuffer buffer = ByteBuffer.allocate(50);
			try {
			    while(this.flag){
			    	System.out.println("5========");
			    	buffer.clear();
			    	int readCount = this.clientChannle.read(buffer);
			    	System.out.println("6========");
			    	String readMessage = new String(buffer.array(),0,readCount).trim();
			    	System.out.println("服务端接收消息:"+readMessage);
			    	String writeMessage = "client got "+readMessage;
			    	if("exit".equalsIgnoreCase(readMessage)){
			    		writeMessage = "bye bye!";
			    		this.flag = false;
			    	}
			    	buffer.clear();
			    	buffer.put(writeMessage.getBytes());
			    	buffer.flip();
			    	this.clientChannle.write(buffer);
			    } 
			    this.clientChannle.close();
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
