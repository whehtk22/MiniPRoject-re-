package com.mini.gui;


import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

import com.mini.client.ChatSend;
import com.mini.client.Client_cennection;
import com.mini.client.JoinGroups;
import com.mini.client.UniClient;

/**
 *	Swing���� ����ϴ� Frame : JFrame 
 */
public class SelectToJoinGroupList extends JDialog{

	private Client_cennection cm;

	private JLabel showStatus = new JLabel("ä�ù� ã�� â");
	private JTextField inputChatName = new JTextField();
	private JButton surchingChat = new JButton("ã��");
	private JLabel showChatList = new JLabel("������ ä�ù� ����");

	/**
	 * ȭ�� ���� �޼ҵ�
	 */
	public void display() {
		setLayout(null);
		this.add(showStatus);
		showStatus.setBounds(29, 34, 129, 15);
		this.add(inputChatName);
		inputChatName.setBounds(23, 92, 116, 21);
		this.add(surchingChat);
		surchingChat.setBounds(170, 91, 79, 23);
		this.add(showChatList);
		showChatList.setBounds(29, 160, 220, 15);

	}
	public void event() {

		surchingChat.addActionListener(e->{
			String inputData = inputChatName.getText();
			int receiveport=cm.receiveSurchRoomPort(inputData);
			showChatList.setText(receiveport+"");
		});
		if(!showChatList.getText().equals("")) {
			showChatList.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					// TODO Auto-generated method stub
					super.mouseClicked(arg0);
					JoinGroups jg =new JoinGroups(cm.getChatPort());
					cm.createRoom(jg);
					dispose();//���� â ����
					chatwindow guiMain = new chatwindow(cm);
				}
			});
		}
	}

	public SelectToJoinGroupList (Client_cennection cm) {
		this.cm = cm;
		this.display();
		this.event();
		this.setLocationByPlatform(true);
		this.setSize(300, 300);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}