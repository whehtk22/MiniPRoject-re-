package com.mini.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import javax.swing.JScrollPane;

/**
 * Ŭ���̾�Ʈ�� ä�ù��� ����� ���� ��Ʈ��ȣ�� �����ϰ� ä�ù��� ������ ó���ϴ� Ŭ����
 * 
 * @author user
 *
 */
public class UniServer {
	private static final int port = 41000;
	private static final String ip = "192.168.0.3";
	private Integer chatPort;
	private ServerSocket server;
	private Socket socket;
	private DataOutputStream dataout;
	private DataInputStream datain;
	private HashMap<String, Integer> chatList = new HashMap<>();

	/**
	 * ����ڿ��� �ʿ��� ������ �ְ� �޴� ��������
	 * 
	 * @throws IOException
	 */
	void setConnection() throws IOException {
		server = new ServerSocket(port);
		socket = server.accept();
		System.out.println("����� ���� �Ϸ�");
	}

	public Integer getChatPort() {
		return chatPort;
	}

	public void setChatPort(Integer chatPort) {
		this.chatPort = chatPort;
	}

	/**
	 * ä�ù��� ��Ʈ��ȣ�� �����ϴ� �޼ҵ�
	 * 
	 * @throws IOException
	 */
	void sendNewPort() throws IOException {
		while (true) {

			chatPort = ((int) (Math.random() * 30000) + 20000);
			if (chatList.containsValue(chatPort)) // �̹� ������ ä�ù� ��Ʈ�� ���������� �˻��ϴ� �ڵ�
				continue;
			else
				break;
		}
		System.out.println(chatPort);
		dataout = new DataOutputStream(socket.getOutputStream());
		dataout.writeInt(chatPort);
	}

	/**
	 * Ŭ���̾�Ʈ�κ��� ä�ù��� �̸��� �Ѱܹ޴� �޼ҵ�
	 * 
	 * @throws IOException
	 */
	public void rcvRoomName() throws IOException {// ä�ù� �̸� �޾Ƽ� ä�ù� ���� �����ϴ� �޼ҵ�
		byte[] name = new byte[1024];
		System.out.println("����Ʈ ����");
		datain = new DataInputStream(socket.getInputStream());
		System.out.println("�ڷ����");
		name = datain.readAllBytes();
		String chat = new String(name, 0, name.length);
		System.out.println(chat);
		chatList.put(chat, chatPort);// ä�ù������ ä�ù� ����
	}

	public void deleteChatRoom() throws IOException {// ä�ù��� �����ִ� �޼ҵ�
		setConnection();
		byte[] delByte = new byte[1024];
		datain = new DataInputStream(socket.getInputStream());
		delByte = datain.readAllBytes();
		String delName = new String(delByte, 0, delByte.length);
		chatList.remove(chatList.get(delName));
		System.out.println("ä�ù� ���� �Ϸ�");
		closeConnection();
	}

	public Integer sendPort(String roomName) {// ����ڰ� ä�ù濡 ���� ���ؼ� ������ ��Ʈ��ȣ�� �ִ� ��.
		return chatList.get(roomName);
	}

	void makeChatRoom() throws Exception {// ä�ù� �������ִµ� �����ϴ� �޼ҵ�
		setConnection();
		sendNewPort();
		rcvRoomName();
		// closeConnection();
	}

	/**
	 * ��������
	 * 
	 * @throws IOException
	 */
	void closeConnection() throws IOException {
		datain.close();
		dataout.close();
		socket.close();
		server.close();
	}

	void deleteAll() {
		chatList.clear();
	}

}
