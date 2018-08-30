package com.mini.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.mini.client.Client_cennection;
import com.mini.client.JoinGroups;
import com.mini.client.UniClient;

/**
 *	Swing���� ����ϴ� Frame : JFrame 
 */
public class Lobby extends JFrame{
	private Client_cennection cm;
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
			cm.receiveNewRoomPort();//ä�ù� �̸��� �����ϰ� �ش� ä�ù��� port��ȣ�� �޴´�.
			JoinGroups jg =new JoinGroups(cm.getChatPort());//���� ��Ʈ �����Ͽ� ä�ù濡 ����Ǳ� ���� �ʿ��� group������ port��ȣ�� �ο�
//			System.out.println(cm.getChatPort());
			cm.createRoom(jg);//Ŭ���̾�Ʈ�� ä�ù濡 ����ǰԼ���
			this.dispose();//���� â ����
			chatwindow guiMain = new chatwindow(cm);//ä��â ���ε�
		});
		jt2.addActionListener(e->{
			SelectToJoinGroupList stjg = new SelectToJoinGroupList(cm);
		});
	}
	
	public Lobby (Client_cennection cm) {
		this.cm= cm;
		this.display();
		this.event();
		this.setLocationByPlatform(true);
		this.setSize(300, 300);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
