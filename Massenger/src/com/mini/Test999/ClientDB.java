package com.mini.Test999;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * �������� �����ϴ� Ŭ���̾�Ʈ�� ������ �����ϴ� Ŭ����
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
		// ���������� �ݺ��Ͽ� ������� �޼����� ����

		try {
			while (true) {
				String text = in.readUTF();
				System.out.println(socket+" : "+text);
				//�ڱ��ڽſ��� ȸ��(����)
//				out.writeUTF(text);
//				out.flush();
				
//				��ü���� �����ϵ��� ����
				server.broadcast(text);//������ Ŭ���̾�Ʈ �迭�� ����.
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
	
	public void send(String text) throws IOException {//������ client���� ������. 
		out.writeUTF(text);
		out.flush();
	}

}
