package com.mini.server;

import java.io.IOException;
import java
.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mini.db.ClientUserDb;
import com.mini.db.Client_Private;
import com.mini.db.DataInfoOpen;
import com.mini.db.Selection;

public class Client extends Thread{
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private Server server;
	private String room;
	public Client(Server server,Socket socket) throws IOException {
		this.socket= socket;
		this.in= new ObjectInputStream(socket.getInputStream());
		this.out = new ObjectOutputStream(socket.getOutputStream());
		this.server = server;
	}

	public void run(){
		int num = 0;
		while(true) {
			try {
				int read =in.readInt();
				//				System.out.println(read+"의 num:"+num);
				switch(read) {
				case 1:
					//					num++;
					//					System.out.println("케이스 "+read+"의"+num);
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
					//					num++;
					//					System.out.println("케이스 "+read+"의"+num);
					System.out.println("일단 케이스 2");
					Map<String,String> idCheck =(HashMap<String, String>) in.readObject();
					//					System.out.println(idCheck);
					DataInfoOpen open = new DataInfoOpen();

					for(Map.Entry<String, String> asd : idCheck.entrySet()) {
						out.writeBoolean(open.login(asd.getKey(), asd.getValue()));
						out.flush();
					}
					System.out.println("채팅방 리스트 "+server.getChatList());
					out.writeObject(server.getChatArr());
					out.flush();
					break;
				case 3:
					//					num++;
					//					System.out.println("케이스 "+read+"의"+num);
					System.out.println("케이스3");
					while(true) {
						String str=in.readUTF();
						System.out.println("받은 문자열"+str);						
						server.broadcast(str);
						
					}
				case 4:
					//					num++;
					//					System.out.println("케이스 "+read+"의"+num);
					System.out.println("케이스 4");
					String str=in.readUTF();
					if(str.equals("123123")) {
						break;
					}
					System.out.println(str);
					server.addChatRoom(str,this);
					//out.writeUTF("됨");
					System.out.println("됨");
					break;
				case 5:
					//					num++;
					//					System.out.println("케이스 "+read+"의"+num);
					System.out.println("케이스5");
					String RoomName=in.readUTF();
					System.out.println(RoomName+":확인용");
					while(true) {
						if(in!=null) {
							String str2=in.readUTF();
							System.out.println("야이:"+str2);
							if(str2.equals("종료종료")) {
								break;
							}
							else if(str2.equals("")||str2==null) {
								//채팅방이 띄어져있는 경우에 채팅방생성을 누를때
								//이거는 그냥 로비를 dispose해서 누르지못하게한다
								System.out.println("채팅방생성을 누름");
								break;
							}
							System.out.println("받은 문자열"+str2);
							//채팅을 한번 입력할 때마다 갱신이 된다.
							server.RoomChat(RoomName,str2);
							System.out.println(server.getChatList().get(RoomName).getClass());
							System.out.println(server.getChatList().get(RoomName));
							server.RoomChat(RoomName,server.getChatList().get(RoomName));//친구목록을 넘겨주는 것.
						}
					}
					break;
				case 6:
					System.out.println("케이스 6");

					String str3=in.readUTF();

					System.out.println(str3);
					if(str3.equals("123123")) {
						break;
					}
					server.SearchRoom(str3,this);
					break;
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
	public void send(List list) throws IOException {
		out.writeObject(list);
		out.flush();
	}


}
