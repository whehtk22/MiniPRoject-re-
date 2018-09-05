package com.mini.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import com.mini.db.ClientUserDb;
import com.mini.db.Client_Private;
import com.mini.db.DataInfoOpen;

public class Client extends Thread{
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private Server server;

	public Client(Server server,Socket socket) throws IOException {
		this.socket= socket;
		this.in= new ObjectInputStream(socket.getInputStream());
		this.out = new ObjectOutputStream(socket.getOutputStream());
		this.server = server;
	}

	public void run(){
		while(true) {
			try {
				
				switch(in.readInt()) {
				case 1:
					while(true){
					ClientUserDb user = (ClientUserDb)in.readObject();
//					if(user == null) {
//						break;
//					}
					DataInfoOpen searchId = new DataInfoOpen();
					System.out.println("user = "+user);
					System.out.println(searchId);
					
					
					
					boolean bool = searchId.searchId(user.getId());
					System.out.println("search = "+bool);
					out.writeBoolean(bool);
					out.flush();
					System.out.println(bool);
					if(bool == false) {
						Client_Private userSave = new Client_Private();
						userSave.clientUserSave(user, user.getId());
						System.out.println("저장됨");
						break;
						}
					}
						break;			
				case 2:
					System.out.println("일단 케이스 2");
					Map<String,String> idCheck =(HashMap<String, String>) in.readObject();
//					System.out.println(idCheck);
					DataInfoOpen open = new DataInfoOpen();
					
					for(Map.Entry<String, String> asd : idCheck.entrySet()) {
						 out.writeBoolean(open.login(asd.getKey(), asd.getValue()));
						 out.flush();
					}
					break;
				case 3:
					System.out.println("케이스3");
					while(true) {
//						System.out.println("3의 while 문");
//						System.out.println("읽음"+in.readUTF());
						String str=in.readUTF();
						System.out.println("받은 문자열"+str);						
						server.broadcast(str);
					}
				case 4:
					System.out.println("케이스 4");
					String str=in.readUTF();
					System.out.println(str);
					server.addChatRoom(str,this);
					break;
				case 5:
					System.out.println("케이스5");
					String RoomName=in.readUTF();
					System.out.println(RoomName+":확인용");
					while(true) {
						String str2=in.readUTF();
						System.out.println("받은 문자열"+str2);
						server.RoomChat(RoomName,str2);
					}
				case 6:
					System.out.println("케이스 6");
					String str3=in.readUTF();
					System.out.println(str3);
					server.SearchRoom(str3,this);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
				System.out.println("밖입니다.");
			
			
			}
		}
	public void send(String text) throws IOException {
		out.writeUTF(text);
		out.flush();
	}


}
