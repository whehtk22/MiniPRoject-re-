package com.mini.sample;
import java.net.*;
import java.util.StringTokenizer;
import java.io.*;

public class Guest extends Thread{
	Socket s;
	Server server;
	BufferedReader br;
	BufferedWriter bw;
	String id, roomtitle, say, receivename, sendname, whisper;

	public Guest(Socket s, Server server){
		this.s = s;
		this.server = server;
		try {
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run(){
		String line="";
		try {
			while((line=br.readLine())!=null){
				System.out.println(line);
				String[] arr = parseMsg(line.substring(line.indexOf("/")+1));
				if(line.startsWith("in/")){       // ���� ����
					id = arr[0];
					server.broadcastRoomList();
					server.broadcastGuestList();
				}else if(line.startsWith("out/")){     // ���� ����
					id = arr[0];
					server.removeGuest(this);
					server.broadcastRoomList();
					server.broadcastGuestList();
					terminate();     
				}else if(line.startsWith("makeroom/")){  // ��ȭ�� �����
					roomtitle = arr[0];
					id = arr[1]+"(king)";
					server.addRoom(roomtitle);
					server.addRoomGuest(this);
					server.removeGuest(this);
					server.broadcastRoomList();
					server.broadcastGuestList();
					server.broadcastRoomGuestList(roomtitle);
				}else if(line.startsWith("removeroom/")){  // ��ȭ�� ����
					server.broadcastRoomList();
					server.broadcastGuestList();    
				}else if(line.startsWith("enterroom/")){   // ��ȭ�� ����
					roomtitle = arr[0];
					id = arr[1];
					server.addRoomGuest(this);
					server.removeGuest(this);
					server.broadcastRoomList();
					server.broadcastGuestList();
					server.broadcastRoomGuestList(roomtitle);
					server.broadcastRoom(roomtitle, line);
				}else if(line.startsWith("exitroom/")){   // ��ȭ�� ����
					roomtitle=arr[0];
					id = arr[1];
					server.addGuest(this);
					server.removeRoomGuest(this);
					server.broadcastRoomList();
					server.broadcastGuestList();
					server.broadcastRoomGuestList(roomtitle);
					server.broadcastRoom(roomtitle, line);
				}else if(line.startsWith("say/")){     // ��ȭ�� ��ȭ
					roomtitle = arr[0];
					id = arr[1];
					say = arr[2];
					server.broadcastRoom(roomtitle, line);
				}else if(line.startsWith("whisper/")){   // ��ȭ�� �ӼӸ�
					roomtitle = arr[0];
					receivename = arr[1];
					sendname = arr[2];
					whisper = arr[3];
					server.whisper(roomtitle, receivename, sendname, whisper);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// �޽��� ������
	public void sendMsg(String msg){
		try {
			bw.write(msg+"\n");
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// �޽��� �Ľ�
	public String[] parseMsg(String msg){
		StringTokenizer st = new StringTokenizer(msg, "/");
		String[] arr = new String[st.countTokens()];
		for(int i=0; st.hasMoreTokens(); i++){
			arr[i] = st.nextToken();
		}
		return arr;
	}
	// �ڿ� ����
	public void terminate(){
		try {
			br.close();
			bw.close();
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

