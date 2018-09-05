package com.mini.gui;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;

import com.mini.client.Connection;
import com.mini.db.Selection;

/**
 *	Swing���� ����ϴ� Frame : JFrame 
 */
public class Lobby extends JFrame{
	private Connection serverCon;
	//������Ʈ�� ��ġ�� ������ JPanel�� ����
	private JPanel con = new JPanel();
	private JLabel jl = new JLabel("�޽��� �׽�Ʈ");
	private JButton jt1 = new  JButton("ä��â ����");
	private JButton jt2 = new JButton("ä�ù� ã��");
	/**
	 * ȭ�� ���� �޼ҵ�
	 */
	public void display() {
		this.setContentPane(con); // con�� conponent ������������ ���

		con.setLayout(null);


		jl.setBounds(12, 10, 260, 72);
		con.add(jl);
		jl.setFont(new Font("����",Font.BOLD,20));


		jt1.setBounds(12, 123, 110, 72);
		con.add(jt1);


		jt2.setBounds(162, 123, 110, 72);
		con.add(jt2);
	}
	public void event() {
		jt1.addActionListener(e->{
			try {
				serverCon.getOut().writeInt(Selection.CREATE_ROOM);
				serverCon.getOut().flush();
				String RoomName =JOptionPane.showInputDialog("ä�ù��̸��� �Է��ϼ�����");
				serverCon.getOut().writeUTF(RoomName);
				serverCon.getOut().flush();
				serverCon.getOut().writeInt(Selection.ROOM_CHAT);
				serverCon.getOut().flush();
				serverCon.getOut().writeUTF(RoomName);
				serverCon.getOut().flush();
				Chatting_Frame g = new Chatting_Frame(serverCon);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});
		jt2.addActionListener(e->{
			boolean ox=true;
			try {
				while(ox) {
					serverCon.getOut().writeInt(Selection.SEARCH_ROOM);
					serverCon.getOut().flush();
					String RoomName =JOptionPane.showInputDialog(con, "ä�ù��̸��� �Է��ϼ���", "�˻�", JOptionPane.OK_CANCEL_OPTION);
					System.out.println(RoomName);
					if(RoomName==null) {
						dispose();
						Lobby lobby = new Lobby(serverCon);
					}
					else {
						serverCon.getOut().writeUTF(RoomName);
						serverCon.getOut().flush();
						String str=serverCon.receive();
						System.out.println(str);
						serverCon.getOut().writeInt(Selection.ROOM_CHAT);
						serverCon.getOut().flush();
						serverCon.getOut().writeUTF(RoomName);
						serverCon.getOut().flush();
						Chatting_Frame g = new Chatting_Frame(serverCon);
						break;
					}
				}
			}catch(Exception e1) {
				e1.printStackTrace();
			}
		});
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public Lobby (Connection serverCon) {
		this.serverCon =serverCon;
		this.display();
		this.event();
		this.setLocationByPlatform(true);
		this.setSize(300, 300);
		this.setResizable(false);
		this.setVisible(true);
	}
}
