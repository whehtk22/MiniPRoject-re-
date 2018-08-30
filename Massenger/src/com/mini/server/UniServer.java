package com.mini.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import javax.swing.JScrollPane;

/**
 * 클라이언트가 채팅방을 만들기 위해 포트번호를 전송하고 채팅방의 정보를 처리하는 클래스
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
	 * 사용자에게 필요한 정보를 주고 받는 서버접속
	 * 
	 * @throws IOException
	 */
	void setConnection() throws IOException {
		server = new ServerSocket(port);
		socket = server.accept();
		System.out.println("사용자 접속 완료");
	}

	public Integer getChatPort() {
		return chatPort;
	}

	public void setChatPort(Integer chatPort) {
		this.chatPort = chatPort;
	}

	/**
	 * 채팅방의 포트번호를 전송하는 메소드
	 * 
	 * @throws IOException
	 */
	void sendNewPort() throws IOException {
		while (true) {

			chatPort = ((int) (Math.random() * 30000) + 20000);
			if (chatList.containsValue(chatPort)) // 이미 생성된 채팅방 포트와 동일한지를 검사하는 코드
				continue;
			else
				break;
		}
		System.out.println(chatPort);
		dataout = new DataOutputStream(socket.getOutputStream());
		dataout.writeInt(chatPort);
	}

	/**
	 * 클라이언트로부터 채팅방의 이름을 넘겨받는 메소드
	 * 
	 * @throws IOException
	 */
	public void rcvRoomName() throws IOException {// 채팅방 이름 받아서 채팅방 정보 저장하는 메소드
		byte[] name = new byte[1024];
		System.out.println("바이트 생성");
		datain = new DataInputStream(socket.getInputStream());
		System.out.println("자료받음");
		name = datain.readAllBytes();
		String chat = new String(name, 0, name.length);
		System.out.println(chat);
		chatList.put(chat, chatPort);// 채팅방생성후 채팅방 저장
	}

	public void deleteChatRoom() throws IOException {// 채팅방을 지워주는 메소드
		setConnection();
		byte[] delByte = new byte[1024];
		datain = new DataInputStream(socket.getInputStream());
		delByte = datain.readAllBytes();
		String delName = new String(delByte, 0, delByte.length);
		chatList.remove(chatList.get(delName));
		System.out.println("채팅방 삭제 완료");
		closeConnection();
	}

	public Integer sendPort(String roomName) {// 사용자가 채팅방에 들어가기 위해서 서버가 포트번호를 주는 것.
		return chatList.get(roomName);
	}

	void makeChatRoom() throws Exception {// 채팅방 생성해주는데 관여하는 메소드
		setConnection();
		sendNewPort();
		rcvRoomName();
		// closeConnection();
	}

	/**
	 * 연결종료
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
