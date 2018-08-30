package com.mini.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;
/*
 * 
 */
public class Client_cennection {
	private int port;
	private String ip;
	private InetAddress inet;
	private Socket socket;
	private JoinGroups jg;
	private DataInputStream datain;
	private DataOutputStream dataout;
	private int chatPort;

	public JoinGroups getJg() {
		return jg;
	}
	public void setJg(JoinGroups jg) {
		this.jg = jg;
	}
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public void createRoom(JoinGroups jg) {
		this.jg=jg;
		try {
			jg.multicast.joinGroup(jg.group);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Client_cennection() {

	}
	public Client_cennection(int port, String ip) {
		this.port = port;
		this.ip = ip;
		try {
			this.inet = InetAddress.getByName(ip);
			socket = new Socket(inet, port);
		} catch (IOException e) {
			System.err.println("���������ϴµ� ������ �߻�");
			e.printStackTrace();
		}
	}
	public int getChatPort() {
		return chatPort;
	}
	void receivePort() throws IOException {
		datain = new DataInputStream(socket.getInputStream());
//		System.out.println(datain.readInt());
		this.chatPort =datain.readInt();
//		System.out.println(chatPort);
	}
	void sendRoomName() throws IOException {
		dataout = new DataOutputStream(socket.getOutputStream());
//		System.out.println("�����������غ�");
		String chatName = JOptionPane.showInputDialog("ä�ù� �̸��� �Է��ϼ���.");
		byte[] name = chatName.getBytes();
		dataout.write(name);
	}
	void closeConnection() throws IOException {
		socket.close();
	}
	public void receiveRoomPort() {
		try {
			receivePort();
			sendRoomName();
//			System.out.println("��");
			closeConnection();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}


}