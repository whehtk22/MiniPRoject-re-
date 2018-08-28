package com.mini.Test999;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 서버에서 관리하는 클라이언트의 정보를 보관하는 클래스
 * 
 * @author Administrator
 */
public class ClientDB extends Thread {

	private Socket socket;

	private ObjectInputStream in;

	private ObjectOutputStream out;

	private Server server;
	
	public Client(Server server,Socket socket) throws IOException {
		this.server=server;
		this.socket = socket;
		this.in = new ObjectInputStream(socket.getInputStream());
		this.out = new ObjectOutputStream(socket.getOutputStream());
	}

	@Override
	public void run() {
		// 지속적으로 반복하여 사용자의 메세지를 수신

		try {
			while (true) {
				String text = in.readUTF();
				System.out.println(socket+" : "+text);
				//자기자신에게 회신(에코)
//				out.writeUTF(text);
//				out.flush();
				
//				전체에게 전송하도록 구현
				server.broadcast(text);//서버의 클라이언트 배열에 관련.
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public String toString() {
		return "Client [socket=" + socket + "]";
	}
	
	public void send(String text) throws IOException {//각각의 client에게 보낸다. 
		out.writeUTF(text);
		out.flush();
	}

}
