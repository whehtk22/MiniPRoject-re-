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
 * Swing���� ����ϴ� Frame : JFrame
 */
public class Lobby extends JFrame {
	private Connection serverCon;
	// ������Ʈ�� ��ġ�� ������ JPanel�� ����
	private JPanel con = new JPanel();
	private JPanel chatpan = new JPanel();
	private JPanel btpan = new JPanel();
	private JLabel jl = new JLabel("�޽��� �׽�Ʈ");
	private JButton jt1 = new JButton("ä��â ����");
	private JButton jt2 = new JButton("ä�ù� ã��");
	private JButton btnNewButton = new JButton("ä�����α׷� ������");
	private JLabel lblNewLabel = new JLabel("ä�ù� ���");
	private ArrayList<String> arrlist;
	private JList jlist;

	/**
	 * ȭ�� ���� �޼ҵ�
	 */
	public void display() {
		this.setContentPane(con); // con�� conponent ������������ ���

		con.setLayout(new BorderLayout());

		btpan.add(jt1);
		jl.setFont(new Font("����", Font.BOLD, 20));
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
					String RoomName = JOptionPane.showInputDialog("ä�ù��̸��� �Է��ϼ�����");
					if (RoomName == null || RoomName.equals("")) {
						String str = "123123";
						serverCon.getOut().writeUTF(str);
						serverCon.getOut().flush();
					} else {
						serverCon.getOut().writeUTF(RoomName);
						serverCon.getOut().flush();
						System.out.println("ũ������Ʈ��");
					}
					
					if (RoomName == null) {
						display();
						break;
					} else {
							serverCon.getOut().writeInt(Selection.ROOM_CHAT);
							serverCon.getOut().flush();
							serverCon.getOut().writeUTF(RoomName);
							System.out.println(RoomName);
							System.out.println("���������");
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
					String RoomName = JOptionPane.showInputDialog(con, "ä�ù��̸��� �Է��ϼ���", "�˻�",
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
						if (!str.equals("ã�¹��̾���")) {
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
		jlist.addMouseListener(    // �� ����Ʈ �߿��� Ŭ������ ��(�� ����) 
				new MouseAdapter(){
					public void mouseClicked(MouseEvent me){
						if(me.getButton()==1){
							if((String)jlist.getSelectedValue()!=null){
								String selectroom = (String)jlist.getSelectedValue();
								System.out.println("������ ���̸�"+selectroom);
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
