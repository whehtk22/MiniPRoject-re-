package com.mini.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Connection {
	private InetAddress inet;
	private int port =20000;
	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	public static String id;

	public static String getId() {
		return id;
	}

	public static void setId(String id) {
		Connection.id = id;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public ObjectOutputStream getOut() {
		return out;
	}

	public void setOut(ObjectOutputStream out) {
		this.out = out;
	}

	public ObjectInputStream getIn() {
		return in;
	}

	public void setIn(ObjectInputStream in) {
		this.in = in;
	}

	public Connection()  throws IOException{
		this.inet = InetAddress.getByName("localhost");
		this.socket= new Socket(inet, port);
		out= new ObjectOutputStream(this.socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());
	}
	
	public Connection(String id) throws IOException {
		Connection.id=id;
		this.inet = InetAddress.getByName("localhost");
		this.socket= new Socket(inet, port);
		out= new ObjectOutputStream(this.socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());
	}
	
//	public void send() throws IOException {
//		String id=JOptionPane.showInputDialog("메세지를 입력하세요.");
//		this.id=id;
//		out.writeUTF(id);
//		out.flush();
//	}
	
	public void send(String str){
		try {
			String message="";
			if(str!=null) {
				message=str; 
			}
			out.writeUTF(message);
			out.flush();
		}catch(Exception e) {
			System.err.println("클라이언트측 이상하게종료됨");
		}
	}
	public String receive() {
		String str="";
		try {
			str = in.readUTF();
			
			System.out.println(str);
		} catch (Exception e) {
			return "123123";
		}
		return str;
	}
	public void Connetion_close() throws IOException {
		this.socket.close();
		System.out.println("close를 했는데도 "+socket.isConnected());
	}
}
