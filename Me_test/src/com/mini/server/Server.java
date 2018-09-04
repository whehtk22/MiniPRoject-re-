package com.mini.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Server {
	
	private List<Client>list = new ArrayList<>();
	private ServerSocket server;
	private int port=40000;
	
	public Server() throws IOException {
		this.server = new ServerSocket(port);
	}
	
	public void work() throws IOException, ClassNotFoundException, InterruptedException {
		while(true) {
			Socket socket = server.accept();
			Client client = new Client(this,socket);
			
			client.setDaemon(true);
			client.start();
			list.add(client);
			System.out.println(list.toString());
			System.out.println("하나의 클라이언트가 들어옴");
			
		}
	}
	public void broadcast(String text) throws IOException {
		for(Client c :list) {
			c.send(text);
		}
	}

	public static void main(String[]args) throws IOException, ClassNotFoundException, InterruptedException {
		Server server = new Server();
		server.work();
	}
	

}
