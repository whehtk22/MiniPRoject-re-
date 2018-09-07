package com.mini.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Server {

	private List<Client>list = new ArrayList<>();
	private ServerSocket server;
	private int port=20000;
	private List<String> chatarr = new
			ArrayList<>();
	/*
	 * 추가 사항 클라이언트가 추가한 채팅방
	 */
	private HashMap<String, List<Client>> chatList = new HashMap<>();

	public Server() throws IOException {
		this.server = new ServerSocket(port);
	}

	public HashMap<String, List<Client>>  getChatList() {
		return chatList;
	}

	public void work() throws IOException, ClassNotFoundException, InterruptedException {
		Collections.synchronizedList(list);
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

	/*
	 * 추가 메소드
	 */
	public void addChatRoom(String name,Client this_) {
		List<Client> firstList = new ArrayList<>();
		firstList.add(this_);
		chatList.put(name, firstList);
		chatarr.add(name);
		System.out.println(chatList);
	}
	
	public List<String> getChatArr() {
		return chatarr;
	}

	public void RoomChat(String RoomName,String str) {
		List<Client> list = new ArrayList<>();
		for(Map.Entry<String, List<Client>>asd:chatList.entrySet()) {
			if(asd.getKey().equals(RoomName))
				list= asd.getValue();
		}
		for(Client c :list) {
			try {
				c.send(str);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public void RoomChat(String RoomName,List list) {
		List<Client> clist = new ArrayList<>();
		for(Map.Entry<String, List<Client>>asd:chatList.entrySet()) {
			if(asd.getKey().equals(RoomName))
				list= asd.getValue();
		}
		for(Client c :clist) {
			try {
				c.send(list);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public void SearchRoom(String RoomName,Client this_) {
		List<Client> list = new ArrayList<>();
		for(Map.Entry<String, List<Client>>asd:chatList.entrySet()) {
			if(asd.getKey().equals(RoomName)) {
				list= asd.getValue();
				list.add(this_);
				chatList.put(RoomName, list);
				System.out.println(list);
				break;
			}
		}
		System.out.println(chatList+"확인용"+list);
		String returM = RoomName+"방이 존재합니다";
		try {
			this_.send(returM);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static void main(String[]args) throws IOException, ClassNotFoundException, InterruptedException {
		Server server = new Server();
		server.work();
	}



}
