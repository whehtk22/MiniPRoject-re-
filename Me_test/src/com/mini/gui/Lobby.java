package com.mini.gui;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import com.mini.client.Connection;
import com.mini.db.Selection;
import com.mini.sample.RoomList;
import com.mini.server.Client;

/**
 * Swing에서 사용하는 Frame : JFrame
 */
public class Lobby extends JFrame {
	private Connection serverCon;
	// 컴포넌트를 배치할 영역을 JPanel로 구현
	private JPanel con = new JPanel();
	private JPanel chatpan = new JPanel();
	private JPanel btpan = new JPanel();
	private JLabel jl = new JLabel("메신저 테스트");
	private JButton jt1 = new JButton("채팅창 생성");
	private JButton jt2 = new JButton("채팅방 찾기");
	private JButton btnNewButton = new JButton("채팅프로그램 나가기");
	private JLabel lblNewLabel = new JLabel("채팅방 목록");
	private ArrayList<String> arrlist;
	private JList jlist;

	/**
	 * 화면 구현 메소드
	 */
	public void display() {
		this.setContentPane(con); // con을 conponent 설정영역으로 등록

		con.setLayout(new BorderLayout());

		btpan.add(jt1);
		jl.setFont(new Font("굴림", Font.BOLD, 20));
		btpan.add(jt2);
		jlist = new JList();
		jlist.setFont(new Font("ms mincho",Font.BOLD, 20));
		jlist.setForeground(new Color(111, 197, 238));
		jlist.setBackground(null);
		jlist.setLocation(3, 8);
		if(arrlist.size()!=0) {
			String[] room = new String[arrlist.size()];
			int i = 0;
			for (String roomName1 : arrlist) {
				room[i] = roomName1;
				i++;
			}
			jlist.setListData(room);
		}
		chatpan.add(jlist);
		con.add(lblNewLabel,BorderLayout.NORTH);
		con.add(btpan, BorderLayout.SOUTH);
		con.add(chatpan, BorderLayout.CENTER);
		//btnNewButton.setBounds(162, 186, 97, 23);
		//con.add(btnNewButton);

		//lblNewLabel.setBounds(12, 190, 57, 15);
		//con.add(lblNewLabel);
	}

	public void event() {
		jt1.addActionListener(e -> {
			try {
				while (true) {
					serverCon.getOut().writeInt(Selection.CREATE_ROOM);
					serverCon.getOut().flush();
					String RoomName = JOptionPane.showInputDialog("채팅방이름을 입력하세세요");
					if (RoomName == null || RoomName.equals("")) {
						String str = "123123";
						serverCon.getOut().writeUTF(str);
						serverCon.getOut().flush();
					} else {
						serverCon.getOut().writeUTF(RoomName);
						serverCon.getOut().flush();
						System.out.println("크리에이트룸");
					}
					
					if (RoomName == null) {
						display();
						break;
					} else {
							serverCon.getOut().writeInt(Selection.ROOM_CHAT);
							serverCon.getOut().flush();
							serverCon.getOut().writeUTF(RoomName);
							System.out.println(RoomName);
							System.out.println("만들어졌나");
							serverCon.getOut().flush();
							Chatting_Frame g = new Chatting_Frame(serverCon, RoomName);
					}
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});
		jt2.addActionListener(e -> {
			boolean ox = true;
			try {
				while (ox) {
					serverCon.getOut().writeInt(Selection.SEARCH_ROOM);
					serverCon.getOut().flush();
					String RoomName = JOptionPane.showInputDialog(con, "채팅방이름을 입력하세요", "검색",
							JOptionPane.OK_CANCEL_OPTION);
					System.out.println(RoomName);
					if (RoomName == null || RoomName.equals("")) {
						String str = "123123";
						serverCon.getOut().writeUTF(str);
						serverCon.getOut().flush();
					} else {
						serverCon.getOut().writeUTF(RoomName);
						serverCon.getOut().flush();
					}
					if (RoomName == null) {
						display();
						break;
					} else {
						String str = serverCon.receive();
						System.out.println(str);
						if (!str.equals("찾는방이없음")) {
							serverCon.getOut().writeInt(Selection.ROOM_CHAT);
							serverCon.getOut().flush();
							serverCon.getOut().writeUTF(RoomName);
							serverCon.getOut().flush();
							Chatting_Frame g = new Chatting_Frame(serverCon, RoomName);
						}
						break;
					}
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		jlist.addMouseListener(    // 방 리스트 중에서 클릭했을 때(방 입장) 
				new MouseAdapter(){
					public void mouseClicked(MouseEvent me){
						if(me.getButton()==1){
							if((String)jlist.getSelectedValue()!=null){
								String selectroom = (String)jlist.getSelectedValue();
								System.out.println("선택한 방이름"+selectroom);
								try {
									serverCon.getOut().writeInt(Selection.ROOM_CHAT);
									serverCon.getOut().flush();
									serverCon.getOut().writeUTF(selectroom);
									serverCon.getOut().flush();
									Chatting_Frame g = new Chatting_Frame(serverCon, selectroom);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
					}
				}
				);
	}

	public Lobby(Connection serverCon, ArrayList list) {
		arrlist = list;
		System.out.println("list"+list);
		this.serverCon = serverCon;
		this.display();
		this.event();
		this.setLocationByPlatform(true);
		this.setSize(300, 300);
		this.setResizable(false);
		this.setVisible(true);
	}
}
