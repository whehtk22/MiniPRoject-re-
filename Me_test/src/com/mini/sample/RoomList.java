package com.mini.sample;
import java.net.*;
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class RoomList extends JFrame implements ActionListener{
	JList li1, li2;
	JTextField tf;
	JButton b;
	JScrollPane sp1, sp2;

	Socket s;
	BufferedReader br;
	BufferedWriter bw;
	String id, roomtitle; 
	Room r;

	public RoomList(String id){
		super(" "+id+" 's chat");
		this.id = id;
		JPanel pa1 = new JPanel();
		JPanel pa2 = new JPanel();
		pa1.setLayout(new BorderLayout());
		pa2.setLayout(new BorderLayout());
		li1 = new JList();
		li1.addMouseListener(    // 방 리스트 중에서 클릭했을 때(방 입장) 
				new MouseAdapter(){
					public void mouseClicked(MouseEvent me){
						if(me.getButton()==1){
							if((String)li1.getSelectedValue()!=null){
								String selectroom = (String)li1.getSelectedValue();
								selectroom = selectroom.substring(0, selectroom.indexOf("/"));
								RoomList.this.selectRoom(selectroom);
							}
						}
					}
				}
				);
		li2 = new JList();
		tf = new JTextField(30);
		tf.addActionListener(this);
		b = new JButton("make room");
		b.addActionListener(this);
		pa1.add(li1, "Center");
		pa1.add(li2, "East");
		pa2.add(tf, "Center");
		pa2.add(b, "East");

		Container container = getContentPane();
		container.add(pa1, "Center");
		container.add(pa2, "South");

		try {
			s = new Socket("localhost", 9999);
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sendMsg("in/"+id);

		addWindowListener(     // 대기실에서 나갈 때(종료)
				new WindowAdapter(){
					public void windowClosing(WindowEvent e){
						sendMsg("out/"+RoomList.this.id);
						terminate();
						System.exit(1);
					}
				}
				);
	}
	// 이벤트 처리
	public void actionPerformed(ActionEvent ae){
		// 방 만들기
		r = new Room(this, tf.getText());
		roomtitle = tf.getText();
		sendMsg("makeroom/"+tf.getText()+"/"+id);
		setVisible(false);
		tf.setText("");
	}
	// 방 리스트에서 선택
	public void selectRoom(String selectroom){
		// 방 입장
		r = new Room(this, selectroom);
		roomtitle = selectroom;
		sendMsg("enterroom/"+roomtitle+"/"+id);
		setVisible(false);  
	}
	// 메시지 보내기
	public void sendMsg(String msg){
		try {
			bw.write(msg+"\n");
			bw.flush(); 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// 메시지 받기
	public void readMsg(){
		String line="";
		try {
			while((line=br.readLine())!=null){
				String[] arr = parseMsg(line.substring(line.indexOf("/")+1));
				if(line.startsWith("roomlist/")){      // 방 목록 뿌리기
					li1.removeAll();
					String[] room = new String[arr.length];
					for(int i=0, j=1; i<arr.length; i+=2, j+=2){
						room[i] = arr[i]+"/"+arr[j];
					}
					li1.setListData(room);
				}else if(line.startsWith("guestlist/")){    // 대기자 목록 뿌리기
					li2.removeAll();
					li2.setListData(arr);
				}else if(line.startsWith("roomguestlist/")){  // 방 사용자 목록 뿌리기
					r.li.removeAll();
					r.li.setListData(arr);
				}else if(line.startsWith("enterroom/")){    // 방 입장 알리기
					r.ta.append("["+arr[1]+"] IN..."+"\n");     
				}else if(line.startsWith("exitroom/")){    // 방 퇴장 알리기
					r.ta.append("["+arr[1]+"] OUT..."+"\n");
				}else if(line.startsWith("say/")){      // 방 대화 뿌리기
					r.ta.append("["+arr[1]+"] : "+arr[2]+"\n");      
				}else if(line.startsWith("whisper/")){    // 귓속말
					r.ta.append("[[["+arr[0]+"]]]"+arr[1]+"\n");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// 메시지 파싱
	public String[] parseMsg(String msg){
		StringTokenizer st = new StringTokenizer(msg, "/");
		String[] arr = new String[st.countTokens()];
		for(int i=0; st.hasMoreTokens(); i++){
			arr[i] = st.nextToken();
		}
		return arr;
	}
	// 자원 정리
	public void terminate(){
		try {
			br.close();
			bw.close();
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String args[]){
		RoomList rl = new RoomList("1번방");
		rl.setBounds(200, 200, 400, 300);
		rl.setVisible(true);
		rl.readMsg();
	}
}