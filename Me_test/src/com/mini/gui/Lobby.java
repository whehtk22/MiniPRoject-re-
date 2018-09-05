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
	private JButton btnNewButton = new JButton("ä�����α׷� ������");
	private JLabel lblNewLabel = new JLabel("ä�ù� ���");
	/**
	 * ȭ�� ���� �޼ҵ�
	 */
	public void display() {
		this.setContentPane(con); // con�� conponent ������������ ���

		con.setLayout(null);


		jl.setBounds(12, 10, 260, 72);
		con.add(jl);
		jl.setFont(new Font("����",Font.BOLD,20));


		jt1.setBounds(12, 76, 110, 72);
		con.add(jt1);


		jt2.setBounds(162, 76, 110, 72);
		con.add(jt2);
		
		btnNewButton.setBounds(162, 186, 97, 23);
		con.add(btnNewButton);
		
		lblNewLabel.setBounds(12, 190, 57, 15);
		con.add(lblNewLabel);
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
				Chatting_Frame g = new Chatting_Frame(serverCon,RoomName);
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
					if(RoomName==null||RoomName.equals("")) {
						String str = "123123";
						serverCon.getOut().writeUTF(str);
						serverCon.getOut().flush();
					}else {
						serverCon.getOut().writeUTF(RoomName);
						serverCon.getOut().flush();
					}
					if(RoomName==null) {
						display();
						break;
					}
					else {
						String str=serverCon.receive();
						System.out.println(str);
						serverCon.getOut().writeInt(Selection.ROOM_CHAT);
						serverCon.getOut().flush();
						serverCon.getOut().writeUTF(RoomName);
						serverCon.getOut().flush();
						Chatting_Frame g = new Chatting_Frame(serverCon,RoomName);
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
