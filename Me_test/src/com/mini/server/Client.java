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
					
					ClientUserDb user = (ClientUserDb)in.readObject();
					Client_Private userSave = new Client_Private();
					userSave.clientUserSave(user, user.getId());
					break;
					
				case 2:
					System.out.println("일단 케이스 2");
					Map<String,String> idCheck =(HashMap<String, String>) in.readObject();
					System.out.println(idCheck);
					DataInfoOpen open = new DataInfoOpen();
					
					for(Map.Entry<String, String> asd : idCheck.entrySet()) {
						 out.writeBoolean(open.login(asd.getKey(), asd.getValue()));
						 out.flush();
					}
					break;
//					while(true) {
//						String str=in.readUTF();
//						System.out.println(str);
//						server.broadcast(str);
//					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
				
			
			
			}
		}
	public void send(String text) throws IOException {
		out.writeUTF(text);
		out.flush();
	}


}
