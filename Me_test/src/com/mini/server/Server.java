package com.mini.server;

import java.io.IOException;
import java.io.ObjectInputStream;
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

	/*
	 * 추가 사항 클라이언트가 추가한 채팅방
	 */
	private Map<String, List<Client>> chatList = new HashMap<>();

	public Server() throws IOException {
		this.server = new ServerSocket(port);
		chatList.put("start", new ArrayList<>());
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
	public boolean addChatRoom(String name,Client this_) {
		boolean makeOk =false;
		List<Client> firstList = new ArrayList<>();
		for(Map.Entry<String, List<Client>>asd:chatList.entrySet()) {
			if(!asd.getKey().equals(name))
				makeOk=true;
			else {
				makeOk=false;
				break;
			}
		}
		System.out.println("중복검사결과"+makeOk);
		if(makeOk) {
			firstList.add(this_);
			chatList.put(name, firstList);
		}
		System.out.println(chatList);
		try {
			if(makeOk==false) {
				this_.send("YouCan'tCreateRoom");
			}else {
				this_.send("됨");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return makeOk;
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
	public void SearchRoom(String RoomName,Client this_) {
		String returM="";
		List<Client> list = new ArrayList<>();
		for(Map.Entry<String, List<Client>>asd:chatList.entrySet()) {
			if(asd.getKey().equals(RoomName)) {
				list= asd.getValue();
				list.add(this_);
				chatList.put(RoomName, list);
				returM = RoomName+"방이 존재합니다";
				System.out.println(list);
				break;
			}else {
				returM ="찾는방이없음";
			}
		}
		System.out.println(returM);
		System.out.println(chatList+"확인용"+list);
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
